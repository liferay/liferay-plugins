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

import com.liferay.knowledgebase.NoSuchFolderException;
import com.liferay.knowledgebase.model.KBFolder;
import com.liferay.knowledgebase.model.impl.KBFolderImpl;
import com.liferay.knowledgebase.model.impl.KBFolderModelImpl;
import com.liferay.knowledgebase.service.persistence.KBFolderPersistence;

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
 * The persistence implementation for the k b folder service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBFolderPersistence
 * @see com.liferay.knowledgebase.service.persistence.KBFolderUtil
 * @generated
 */
@ProviderType
public class KBFolderPersistenceImpl extends BasePersistenceImpl<KBFolder>
	implements KBFolderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link KBFolderUtil} to access the k b folder persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = KBFolderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			KBFolderModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the k b folders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching k b folders
	 */
	@Override
	public List<KBFolder> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b folders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @return the range of matching k b folders
	 */
	@Override
	public List<KBFolder> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b folders where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b folders
	 */
	@Override
	public List<KBFolder> findByUuid(String uuid, int start, int end,
		OrderByComparator<KBFolder> orderByComparator) {
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

		List<KBFolder> list = (List<KBFolder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBFolder kbFolder : list) {
				if (!Validator.equals(uuid, kbFolder.getUuid())) {
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

			query.append(_SQL_SELECT_KBFOLDER_WHERE);

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
				query.append(KBFolderModelImpl.ORDER_BY_JPQL);
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
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByUuid_First(String uuid,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByUuid_First(uuid, orderByComparator);

		if (kbFolder != null) {
			return kbFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first k b folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByUuid_First(String uuid,
		OrderByComparator<KBFolder> orderByComparator) {
		List<KBFolder> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByUuid_Last(String uuid,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByUuid_Last(uuid, orderByComparator);

		if (kbFolder != null) {
			return kbFolder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last k b folder in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByUuid_Last(String uuid,
		OrderByComparator<KBFolder> orderByComparator) {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<KBFolder> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b folders before and after the current k b folder in the ordered set where uuid = &#63;.
	 *
	 * @param kbFolderId the primary key of the current k b folder
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b folder
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder[] findByUuid_PrevAndNext(long kbFolderId, String uuid,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = findByPrimaryKey(kbFolderId);

		Session session = null;

		try {
			session = openSession();

			KBFolder[] array = new KBFolderImpl[3];

			array[0] = getByUuid_PrevAndNext(session, kbFolder, uuid,
					orderByComparator, true);

			array[1] = kbFolder;

			array[2] = getByUuid_PrevAndNext(session, kbFolder, uuid,
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

	protected KBFolder getByUuid_PrevAndNext(Session session,
		KBFolder kbFolder, String uuid,
		OrderByComparator<KBFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBFOLDER_WHERE);

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
			query.append(KBFolderModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b folders where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (KBFolder kbFolder : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(kbFolder);
		}
	}

	/**
	 * Returns the number of k b folders where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching k b folders
	 */
	@Override
	public int countByUuid(String uuid) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID;

		Object[] finderArgs = new Object[] { uuid };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_KBFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "kbFolder.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "kbFolder.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(kbFolder.uuid IS NULL OR kbFolder.uuid = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_UUID_G = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() },
			KBFolderModelImpl.UUID_COLUMN_BITMASK |
			KBFolderModelImpl.GROUPID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_G = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns the k b folder where uuid = &#63; and groupId = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByUUID_G(String uuid, long groupId)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByUUID_G(uuid, groupId);

		if (kbFolder == null) {
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

			throw new NoSuchFolderException(msg.toString());
		}

		return kbFolder;
	}

	/**
	 * Returns the k b folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the k b folder where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByUUID_G(String uuid, long groupId,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { uuid, groupId };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_UUID_G,
					finderArgs, this);
		}

		if (result instanceof KBFolder) {
			KBFolder kbFolder = (KBFolder)result;

			if (!Validator.equals(uuid, kbFolder.getUuid()) ||
					(groupId != kbFolder.getGroupId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_KBFOLDER_WHERE);

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

				List<KBFolder> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
						finderArgs, list);
				}
				else {
					KBFolder kbFolder = list.get(0);

					result = kbFolder;

					cacheResult(kbFolder);

					if ((kbFolder.getUuid() == null) ||
							!kbFolder.getUuid().equals(uuid) ||
							(kbFolder.getGroupId() != groupId)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
							finderArgs, kbFolder);
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
			return (KBFolder)result;
		}
	}

	/**
	 * Removes the k b folder where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the k b folder that was removed
	 */
	@Override
	public KBFolder removeByUUID_G(String uuid, long groupId)
		throws NoSuchFolderException {
		KBFolder kbFolder = findByUUID_G(uuid, groupId);

		return remove(kbFolder);
	}

	/**
	 * Returns the number of k b folders where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching k b folders
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_G;

		Object[] finderArgs = new Object[] { uuid, groupId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_G_UUID_1 = "kbFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_2 = "kbFolder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_G_UUID_3 = "(kbFolder.uuid IS NULL OR kbFolder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 = "kbFolder.groupId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			KBFolderModelImpl.UUID_COLUMN_BITMASK |
			KBFolderModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the k b folders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching k b folders
	 */
	@Override
	public List<KBFolder> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b folders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @return the range of matching k b folders
	 */
	@Override
	public List<KBFolder> findByUuid_C(String uuid, long companyId, int start,
		int end) {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b folders where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b folders
	 */
	@Override
	public List<KBFolder> findByUuid_C(String uuid, long companyId, int start,
		int end, OrderByComparator<KBFolder> orderByComparator) {
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

		List<KBFolder> list = (List<KBFolder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBFolder kbFolder : list) {
				if (!Validator.equals(uuid, kbFolder.getUuid()) ||
						(companyId != kbFolder.getCompanyId())) {
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

			query.append(_SQL_SELECT_KBFOLDER_WHERE);

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
				query.append(KBFolderModelImpl.ORDER_BY_JPQL);
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
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByUuid_C_First(String uuid, long companyId,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (kbFolder != null) {
			return kbFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator<KBFolder> orderByComparator) {
		List<KBFolder> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (kbFolder != null) {
			return kbFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator<KBFolder> orderByComparator) {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<KBFolder> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b folders before and after the current k b folder in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param kbFolderId the primary key of the current k b folder
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b folder
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder[] findByUuid_C_PrevAndNext(long kbFolderId, String uuid,
		long companyId, OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = findByPrimaryKey(kbFolderId);

		Session session = null;

		try {
			session = openSession();

			KBFolder[] array = new KBFolderImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, kbFolder, uuid,
					companyId, orderByComparator, true);

			array[1] = kbFolder;

			array[2] = getByUuid_C_PrevAndNext(session, kbFolder, uuid,
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

	protected KBFolder getByUuid_C_PrevAndNext(Session session,
		KBFolder kbFolder, String uuid, long companyId,
		OrderByComparator<KBFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBFOLDER_WHERE);

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
			query.append(KBFolderModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(kbFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b folders where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (KBFolder kbFolder : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbFolder);
		}
	}

	/**
	 * Returns the number of k b folders where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching k b folders
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_UUID_C;

		Object[] finderArgs = new Object[] { uuid, companyId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBFOLDER_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "kbFolder.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "kbFolder.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(kbFolder.uuid IS NULL OR kbFolder.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "kbFolder.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByG_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByG_P",
			new String[] { Long.class.getName(), Long.class.getName() },
			KBFolderModelImpl.GROUPID_COLUMN_BITMASK |
			KBFolderModelImpl.PARENTKBFOLDERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @return the matching k b folders
	 */
	@Override
	public List<KBFolder> findByG_P(long groupId, long parentKBFolderId) {
		return findByG_P(groupId, parentKBFolderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @return the range of matching k b folders
	 */
	@Override
	public List<KBFolder> findByG_P(long groupId, long parentKBFolderId,
		int start, int end) {
		return findByG_P(groupId, parentKBFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b folders
	 */
	@Override
	public List<KBFolder> findByG_P(long groupId, long parentKBFolderId,
		int start, int end, OrderByComparator<KBFolder> orderByComparator) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] { groupId, parentKBFolderId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_G_P;
			finderArgs = new Object[] {
					groupId, parentKBFolderId,
					
					start, end, orderByComparator
				};
		}

		List<KBFolder> list = (List<KBFolder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (KBFolder kbFolder : list) {
				if ((groupId != kbFolder.getGroupId()) ||
						(parentKBFolderId != kbFolder.getParentKBFolderId())) {
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

			query.append(_SQL_SELECT_KBFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTKBFOLDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(KBFolderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentKBFolderId);

				if (!pagination) {
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByG_P_First(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByG_P_First(groupId, parentKBFolderId,
				orderByComparator);

		if (kbFolder != null) {
			return kbFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentKBFolderId=");
		msg.append(parentKBFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the first k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByG_P_First(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator) {
		List<KBFolder> list = findByG_P(groupId, parentKBFolderId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByG_P_Last(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByG_P_Last(groupId, parentKBFolderId,
				orderByComparator);

		if (kbFolder != null) {
			return kbFolder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("groupId=");
		msg.append(groupId);

		msg.append(", parentKBFolderId=");
		msg.append(parentKBFolderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchFolderException(msg.toString());
	}

	/**
	 * Returns the last k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByG_P_Last(long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator) {
		int count = countByG_P(groupId, parentKBFolderId);

		if (count == 0) {
			return null;
		}

		List<KBFolder> list = findByG_P(groupId, parentKBFolderId, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the k b folders before and after the current k b folder in the ordered set where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param kbFolderId the primary key of the current k b folder
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b folder
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder[] findByG_P_PrevAndNext(long kbFolderId, long groupId,
		long parentKBFolderId, OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		KBFolder kbFolder = findByPrimaryKey(kbFolderId);

		Session session = null;

		try {
			session = openSession();

			KBFolder[] array = new KBFolderImpl[3];

			array[0] = getByG_P_PrevAndNext(session, kbFolder, groupId,
					parentKBFolderId, orderByComparator, true);

			array[1] = kbFolder;

			array[2] = getByG_P_PrevAndNext(session, kbFolder, groupId,
					parentKBFolderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBFolder getByG_P_PrevAndNext(Session session, KBFolder kbFolder,
		long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_KBFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTKBFOLDERID_2);

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
			query.append(KBFolderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentKBFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @return the matching k b folders that the user has permission to view
	 */
	@Override
	public List<KBFolder> filterFindByG_P(long groupId, long parentKBFolderId) {
		return filterFindByG_P(groupId, parentKBFolderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @return the range of matching k b folders that the user has permission to view
	 */
	@Override
	public List<KBFolder> filterFindByG_P(long groupId, long parentKBFolderId,
		int start, int end) {
		return filterFindByG_P(groupId, parentKBFolderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b folders that the user has permissions to view where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching k b folders that the user has permission to view
	 */
	@Override
	public List<KBFolder> filterFindByG_P(long groupId, long parentKBFolderId,
		int start, int end, OrderByComparator<KBFolder> orderByComparator) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P(groupId, parentKBFolderId, start, end,
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
			query.append(_FILTER_SQL_SELECT_KBFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTKBFOLDERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBFOLDER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				q.addEntity(_FILTER_ENTITY_ALIAS, KBFolderImpl.class);
			}
			else {
				q.addEntity(_FILTER_ENTITY_TABLE, KBFolderImpl.class);
			}

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentKBFolderId);

			return (List<KBFolder>)QueryUtil.list(q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the k b folders before and after the current k b folder in the ordered set of k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param kbFolderId the primary key of the current k b folder
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next k b folder
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder[] filterFindByG_P_PrevAndNext(long kbFolderId,
		long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator)
		throws NoSuchFolderException {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return findByG_P_PrevAndNext(kbFolderId, groupId, parentKBFolderId,
				orderByComparator);
		}

		KBFolder kbFolder = findByPrimaryKey(kbFolderId);

		Session session = null;

		try {
			session = openSession();

			KBFolder[] array = new KBFolderImpl[3];

			array[0] = filterGetByG_P_PrevAndNext(session, kbFolder, groupId,
					parentKBFolderId, orderByComparator, true);

			array[1] = kbFolder;

			array[2] = filterGetByG_P_PrevAndNext(session, kbFolder, groupId,
					parentKBFolderId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected KBFolder filterGetByG_P_PrevAndNext(Session session,
		KBFolder kbFolder, long groupId, long parentKBFolderId,
		OrderByComparator<KBFolder> orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		if (getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBFOLDER_WHERE);
		}
		else {
			query.append(_FILTER_SQL_SELECT_KBFOLDER_NO_INLINE_DISTINCT_WHERE_1);
		}

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTKBFOLDERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			query.append(_FILTER_SQL_SELECT_KBFOLDER_NO_INLINE_DISTINCT_WHERE_2);
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
				query.append(KBFolderModelImpl.ORDER_BY_JPQL);
			}
			else {
				query.append(KBFolderModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		SQLQuery q = session.createSynchronizedSQLQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			q.addEntity(_FILTER_ENTITY_ALIAS, KBFolderImpl.class);
		}
		else {
			q.addEntity(_FILTER_ENTITY_TABLE, KBFolderImpl.class);
		}

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(groupId);

		qPos.add(parentKBFolderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(kbFolder);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<KBFolder> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the k b folders where groupId = &#63; and parentKBFolderId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 */
	@Override
	public void removeByG_P(long groupId, long parentKBFolderId) {
		for (KBFolder kbFolder : findByG_P(groupId, parentKBFolderId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(kbFolder);
		}
	}

	/**
	 * Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @return the number of matching k b folders
	 */
	@Override
	public int countByG_P(long groupId, long parentKBFolderId) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P;

		Object[] finderArgs = new Object[] { groupId, parentKBFolderId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_KBFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_PARENTKBFOLDERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentKBFolderId);

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
	 * Returns the number of k b folders that the user has permission to view where groupId = &#63; and parentKBFolderId = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @return the number of matching k b folders that the user has permission to view
	 */
	@Override
	public int filterCountByG_P(long groupId, long parentKBFolderId) {
		if (!InlineSQLHelperUtil.isEnabled(groupId)) {
			return countByG_P(groupId, parentKBFolderId);
		}

		StringBundler query = new StringBundler(3);

		query.append(_FILTER_SQL_COUNT_KBFOLDER_WHERE);

		query.append(_FINDER_COLUMN_G_P_GROUPID_2);

		query.append(_FINDER_COLUMN_G_P_PARENTKBFOLDERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(query.toString(),
				KBFolder.class.getName(),
				_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN, groupId);

		Session session = null;

		try {
			session = openSession();

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME,
				com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);

			qPos.add(parentKBFolderId);

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

	private static final String _FINDER_COLUMN_G_P_GROUPID_2 = "kbFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_PARENTKBFOLDERID_2 = "kbFolder.parentKBFolderId = ?";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_P_N = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KBFolderModelImpl.GROUPID_COLUMN_BITMASK |
			KBFolderModelImpl.PARENTKBFOLDERID_COLUMN_BITMASK |
			KBFolderModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_N = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_N",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param name the name
	 * @return the matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByG_P_N(long groupId, long parentKBFolderId, String name)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByG_P_N(groupId, parentKBFolderId, name);

		if (kbFolder == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentKBFolderId=");
			msg.append(parentKBFolderId);

			msg.append(", name=");
			msg.append(name);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFolderException(msg.toString());
		}

		return kbFolder;
	}

	/**
	 * Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param name the name
	 * @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByG_P_N(long groupId, long parentKBFolderId,
		String name) {
		return fetchByG_P_N(groupId, parentKBFolderId, name, true);
	}

	/**
	 * Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param name the name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByG_P_N(long groupId, long parentKBFolderId,
		String name, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, parentKBFolderId, name };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_P_N,
					finderArgs, this);
		}

		if (result instanceof KBFolder) {
			KBFolder kbFolder = (KBFolder)result;

			if ((groupId != kbFolder.getGroupId()) ||
					(parentKBFolderId != kbFolder.getParentKBFolderId()) ||
					!Validator.equals(name, kbFolder.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_KBFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_PARENTKBFOLDERID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_P_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_P_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentKBFolderId);

				if (bindName) {
					qPos.add(name);
				}

				List<KBFolder> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_N,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KBFolderPersistenceImpl.fetchByG_P_N(long, long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KBFolder kbFolder = list.get(0);

					result = kbFolder;

					cacheResult(kbFolder);

					if ((kbFolder.getGroupId() != groupId) ||
							(kbFolder.getParentKBFolderId() != parentKBFolderId) ||
							(kbFolder.getName() == null) ||
							!kbFolder.getName().equals(name)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_N,
							finderArgs, kbFolder);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P_N,
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
			return (KBFolder)result;
		}
	}

	/**
	 * Removes the k b folder where groupId = &#63; and parentKBFolderId = &#63; and name = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param name the name
	 * @return the k b folder that was removed
	 */
	@Override
	public KBFolder removeByG_P_N(long groupId, long parentKBFolderId,
		String name) throws NoSuchFolderException {
		KBFolder kbFolder = findByG_P_N(groupId, parentKBFolderId, name);

		return remove(kbFolder);
	}

	/**
	 * Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63; and name = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param name the name
	 * @return the number of matching k b folders
	 */
	@Override
	public int countByG_P_N(long groupId, long parentKBFolderId, String name) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_N;

		Object[] finderArgs = new Object[] { groupId, parentKBFolderId, name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_N_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_N_PARENTKBFOLDERID_2);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_G_P_N_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_N_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_G_P_N_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentKBFolderId);

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

	private static final String _FINDER_COLUMN_G_P_N_GROUPID_2 = "kbFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_PARENTKBFOLDERID_2 = "kbFolder.parentKBFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_N_NAME_1 = "kbFolder.name IS NULL";
	private static final String _FINDER_COLUMN_G_P_N_NAME_2 = "kbFolder.name = ?";
	private static final String _FINDER_COLUMN_G_P_N_NAME_3 = "(kbFolder.name IS NULL OR kbFolder.name = '')";
	public static final FinderPath FINDER_PATH_FETCH_BY_G_P_UT = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, KBFolderImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByG_P_UT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			KBFolderModelImpl.GROUPID_COLUMN_BITMASK |
			KBFolderModelImpl.PARENTKBFOLDERID_COLUMN_BITMASK |
			KBFolderModelImpl.URLTITLE_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_G_P_UT = new FinderPath(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByG_P_UT",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});

	/**
	 * Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param urlTitle the url title
	 * @return the matching k b folder
	 * @throws NoSuchFolderException if a matching k b folder could not be found
	 */
	@Override
	public KBFolder findByG_P_UT(long groupId, long parentKBFolderId,
		String urlTitle) throws NoSuchFolderException {
		KBFolder kbFolder = fetchByG_P_UT(groupId, parentKBFolderId, urlTitle);

		if (kbFolder == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append(", parentKBFolderId=");
			msg.append(parentKBFolderId);

			msg.append(", urlTitle=");
			msg.append(urlTitle);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchFolderException(msg.toString());
		}

		return kbFolder;
	}

	/**
	 * Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param urlTitle the url title
	 * @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByG_P_UT(long groupId, long parentKBFolderId,
		String urlTitle) {
		return fetchByG_P_UT(groupId, parentKBFolderId, urlTitle, true);
	}

	/**
	 * Returns the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param urlTitle the url title
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching k b folder, or <code>null</code> if a matching k b folder could not be found
	 */
	@Override
	public KBFolder fetchByG_P_UT(long groupId, long parentKBFolderId,
		String urlTitle, boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { groupId, parentKBFolderId, urlTitle };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_G_P_UT,
					finderArgs, this);
		}

		if (result instanceof KBFolder) {
			KBFolder kbFolder = (KBFolder)result;

			if ((groupId != kbFolder.getGroupId()) ||
					(parentKBFolderId != kbFolder.getParentKBFolderId()) ||
					!Validator.equals(urlTitle, kbFolder.getUrlTitle())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_KBFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_UT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_UT_PARENTKBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_P_UT_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_UT_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_P_UT_URLTITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentKBFolderId);

				if (bindUrlTitle) {
					qPos.add(urlTitle);
				}

				List<KBFolder> list = q.list();

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_UT,
						finderArgs, list);
				}
				else {
					if ((list.size() > 1) && _log.isWarnEnabled()) {
						_log.warn(
							"KBFolderPersistenceImpl.fetchByG_P_UT(long, long, String, boolean) with parameters (" +
							StringUtil.merge(finderArgs) +
							") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
					}

					KBFolder kbFolder = list.get(0);

					result = kbFolder;

					cacheResult(kbFolder);

					if ((kbFolder.getGroupId() != groupId) ||
							(kbFolder.getParentKBFolderId() != parentKBFolderId) ||
							(kbFolder.getUrlTitle() == null) ||
							!kbFolder.getUrlTitle().equals(urlTitle)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_UT,
							finderArgs, kbFolder);
					}
				}
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P_UT,
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
			return (KBFolder)result;
		}
	}

	/**
	 * Removes the k b folder where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param urlTitle the url title
	 * @return the k b folder that was removed
	 */
	@Override
	public KBFolder removeByG_P_UT(long groupId, long parentKBFolderId,
		String urlTitle) throws NoSuchFolderException {
		KBFolder kbFolder = findByG_P_UT(groupId, parentKBFolderId, urlTitle);

		return remove(kbFolder);
	}

	/**
	 * Returns the number of k b folders where groupId = &#63; and parentKBFolderId = &#63; and urlTitle = &#63;.
	 *
	 * @param groupId the group ID
	 * @param parentKBFolderId the parent k b folder ID
	 * @param urlTitle the url title
	 * @return the number of matching k b folders
	 */
	@Override
	public int countByG_P_UT(long groupId, long parentKBFolderId,
		String urlTitle) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_G_P_UT;

		Object[] finderArgs = new Object[] { groupId, parentKBFolderId, urlTitle };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_KBFOLDER_WHERE);

			query.append(_FINDER_COLUMN_G_P_UT_GROUPID_2);

			query.append(_FINDER_COLUMN_G_P_UT_PARENTKBFOLDERID_2);

			boolean bindUrlTitle = false;

			if (urlTitle == null) {
				query.append(_FINDER_COLUMN_G_P_UT_URLTITLE_1);
			}
			else if (urlTitle.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_G_P_UT_URLTITLE_3);
			}
			else {
				bindUrlTitle = true;

				query.append(_FINDER_COLUMN_G_P_UT_URLTITLE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				qPos.add(parentKBFolderId);

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

	private static final String _FINDER_COLUMN_G_P_UT_GROUPID_2 = "kbFolder.groupId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_UT_PARENTKBFOLDERID_2 = "kbFolder.parentKBFolderId = ? AND ";
	private static final String _FINDER_COLUMN_G_P_UT_URLTITLE_1 = "kbFolder.urlTitle IS NULL";
	private static final String _FINDER_COLUMN_G_P_UT_URLTITLE_2 = "kbFolder.urlTitle = ?";
	private static final String _FINDER_COLUMN_G_P_UT_URLTITLE_3 = "(kbFolder.urlTitle IS NULL OR kbFolder.urlTitle = '')";

	public KBFolderPersistenceImpl() {
		setModelClass(KBFolder.class);
	}

	/**
	 * Caches the k b folder in the entity cache if it is enabled.
	 *
	 * @param kbFolder the k b folder
	 */
	@Override
	public void cacheResult(KBFolder kbFolder) {
		EntityCacheUtil.putResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderImpl.class, kbFolder.getPrimaryKey(), kbFolder);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G,
			new Object[] { kbFolder.getUuid(), kbFolder.getGroupId() }, kbFolder);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_N,
			new Object[] {
				kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
				kbFolder.getName()
			}, kbFolder);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_UT,
			new Object[] {
				kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
				kbFolder.getUrlTitle()
			}, kbFolder);

		kbFolder.resetOriginalValues();
	}

	/**
	 * Caches the k b folders in the entity cache if it is enabled.
	 *
	 * @param kbFolders the k b folders
	 */
	@Override
	public void cacheResult(List<KBFolder> kbFolders) {
		for (KBFolder kbFolder : kbFolders) {
			if (EntityCacheUtil.getResult(
						KBFolderModelImpl.ENTITY_CACHE_ENABLED,
						KBFolderImpl.class, kbFolder.getPrimaryKey()) == null) {
				cacheResult(kbFolder);
			}
			else {
				kbFolder.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all k b folders.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		EntityCacheUtil.clearCache(KBFolderImpl.class);

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the k b folder.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(KBFolder kbFolder) {
		EntityCacheUtil.removeResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderImpl.class, kbFolder.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(kbFolder);
	}

	@Override
	public void clearCache(List<KBFolder> kbFolders) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (KBFolder kbFolder : kbFolders) {
			EntityCacheUtil.removeResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
				KBFolderImpl.class, kbFolder.getPrimaryKey());

			clearUniqueFindersCache(kbFolder);
		}
	}

	protected void cacheUniqueFindersCache(KBFolder kbFolder) {
		if (kbFolder.isNew()) {
			Object[] args = new Object[] {
					kbFolder.getUuid(), kbFolder.getGroupId()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
				kbFolder);

			args = new Object[] {
					kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
					kbFolder.getName()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_N, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_N, args, kbFolder);

			args = new Object[] {
					kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
					kbFolder.getUrlTitle()
				};

			FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_UT, args,
				Long.valueOf(1));
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_UT, args,
				kbFolder);
		}
		else {
			KBFolderModelImpl kbFolderModelImpl = (KBFolderModelImpl)kbFolder;

			if ((kbFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbFolder.getUuid(), kbFolder.getGroupId()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_UUID_G, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_UUID_G, args,
					kbFolder);
			}

			if ((kbFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_P_N.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
						kbFolder.getName()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_N, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_N, args,
					kbFolder);
			}

			if ((kbFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_G_P_UT.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
						kbFolder.getUrlTitle()
					};

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_G_P_UT, args,
					Long.valueOf(1));
				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_G_P_UT, args,
					kbFolder);
			}
		}
	}

	protected void clearUniqueFindersCache(KBFolder kbFolder) {
		KBFolderModelImpl kbFolderModelImpl = (KBFolderModelImpl)kbFolder;

		Object[] args = new Object[] { kbFolder.getUuid(), kbFolder.getGroupId() };

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);

		if ((kbFolderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_UUID_G.getColumnBitmask()) != 0) {
			args = new Object[] {
					kbFolderModelImpl.getOriginalUuid(),
					kbFolderModelImpl.getOriginalGroupId()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_G, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_UUID_G, args);
		}

		args = new Object[] {
				kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
				kbFolder.getName()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_N, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P_N, args);

		if ((kbFolderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_P_N.getColumnBitmask()) != 0) {
			args = new Object[] {
					kbFolderModelImpl.getOriginalGroupId(),
					kbFolderModelImpl.getOriginalParentKBFolderId(),
					kbFolderModelImpl.getOriginalName()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_N, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P_N, args);
		}

		args = new Object[] {
				kbFolder.getGroupId(), kbFolder.getParentKBFolderId(),
				kbFolder.getUrlTitle()
			};

		FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_UT, args);
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P_UT, args);

		if ((kbFolderModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_G_P_UT.getColumnBitmask()) != 0) {
			args = new Object[] {
					kbFolderModelImpl.getOriginalGroupId(),
					kbFolderModelImpl.getOriginalParentKBFolderId(),
					kbFolderModelImpl.getOriginalUrlTitle()
				};

			FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P_UT, args);
			FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_G_P_UT, args);
		}
	}

	/**
	 * Creates a new k b folder with the primary key. Does not add the k b folder to the database.
	 *
	 * @param kbFolderId the primary key for the new k b folder
	 * @return the new k b folder
	 */
	@Override
	public KBFolder create(long kbFolderId) {
		KBFolder kbFolder = new KBFolderImpl();

		kbFolder.setNew(true);
		kbFolder.setPrimaryKey(kbFolderId);

		String uuid = PortalUUIDUtil.generate();

		kbFolder.setUuid(uuid);

		return kbFolder;
	}

	/**
	 * Removes the k b folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param kbFolderId the primary key of the k b folder
	 * @return the k b folder that was removed
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder remove(long kbFolderId) throws NoSuchFolderException {
		return remove((Serializable)kbFolderId);
	}

	/**
	 * Removes the k b folder with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the k b folder
	 * @return the k b folder that was removed
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder remove(Serializable primaryKey)
		throws NoSuchFolderException {
		Session session = null;

		try {
			session = openSession();

			KBFolder kbFolder = (KBFolder)session.get(KBFolderImpl.class,
					primaryKey);

			if (kbFolder == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(kbFolder);
		}
		catch (NoSuchFolderException nsee) {
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
	protected KBFolder removeImpl(KBFolder kbFolder) {
		kbFolder = toUnwrappedModel(kbFolder);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(kbFolder)) {
				kbFolder = (KBFolder)session.get(KBFolderImpl.class,
						kbFolder.getPrimaryKeyObj());
			}

			if (kbFolder != null) {
				session.delete(kbFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (kbFolder != null) {
			clearCache(kbFolder);
		}

		return kbFolder;
	}

	@Override
	public KBFolder updateImpl(KBFolder kbFolder) {
		kbFolder = toUnwrappedModel(kbFolder);

		boolean isNew = kbFolder.isNew();

		KBFolderModelImpl kbFolderModelImpl = (KBFolderModelImpl)kbFolder;

		if (Validator.isNull(kbFolder.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			kbFolder.setUuid(uuid);
		}

		ServiceContext serviceContext = ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (kbFolder.getCreateDate() == null)) {
			if (serviceContext == null) {
				kbFolder.setCreateDate(now);
			}
			else {
				kbFolder.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!kbFolderModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				kbFolder.setModifiedDate(now);
			}
			else {
				kbFolder.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (kbFolder.isNew()) {
				session.save(kbFolder);

				kbFolder.setNew(false);
			}
			else {
				session.merge(kbFolder);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !KBFolderModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((kbFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] { kbFolderModelImpl.getOriginalUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { kbFolderModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((kbFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbFolderModelImpl.getOriginalUuid(),
						kbFolderModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						kbFolderModelImpl.getUuid(),
						kbFolderModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}

			if ((kbFolderModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						kbFolderModelImpl.getOriginalGroupId(),
						kbFolderModelImpl.getOriginalParentKBFolderId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);

				args = new Object[] {
						kbFolderModelImpl.getGroupId(),
						kbFolderModelImpl.getParentKBFolderId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_G_P, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_G_P,
					args);
			}
		}

		EntityCacheUtil.putResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
			KBFolderImpl.class, kbFolder.getPrimaryKey(), kbFolder, false);

		clearUniqueFindersCache(kbFolder);
		cacheUniqueFindersCache(kbFolder);

		kbFolder.resetOriginalValues();

		return kbFolder;
	}

	protected KBFolder toUnwrappedModel(KBFolder kbFolder) {
		if (kbFolder instanceof KBFolderImpl) {
			return kbFolder;
		}

		KBFolderImpl kbFolderImpl = new KBFolderImpl();

		kbFolderImpl.setNew(kbFolder.isNew());
		kbFolderImpl.setPrimaryKey(kbFolder.getPrimaryKey());

		kbFolderImpl.setUuid(kbFolder.getUuid());
		kbFolderImpl.setKbFolderId(kbFolder.getKbFolderId());
		kbFolderImpl.setGroupId(kbFolder.getGroupId());
		kbFolderImpl.setCompanyId(kbFolder.getCompanyId());
		kbFolderImpl.setUserId(kbFolder.getUserId());
		kbFolderImpl.setUserName(kbFolder.getUserName());
		kbFolderImpl.setCreateDate(kbFolder.getCreateDate());
		kbFolderImpl.setModifiedDate(kbFolder.getModifiedDate());
		kbFolderImpl.setParentKBFolderId(kbFolder.getParentKBFolderId());
		kbFolderImpl.setName(kbFolder.getName());
		kbFolderImpl.setUrlTitle(kbFolder.getUrlTitle());
		kbFolderImpl.setDescription(kbFolder.getDescription());

		return kbFolderImpl;
	}

	/**
	 * Returns the k b folder with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b folder
	 * @return the k b folder
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFolderException {
		KBFolder kbFolder = fetchByPrimaryKey(primaryKey);

		if (kbFolder == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFolderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return kbFolder;
	}

	/**
	 * Returns the k b folder with the primary key or throws a {@link NoSuchFolderException} if it could not be found.
	 *
	 * @param kbFolderId the primary key of the k b folder
	 * @return the k b folder
	 * @throws NoSuchFolderException if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder findByPrimaryKey(long kbFolderId)
		throws NoSuchFolderException {
		return findByPrimaryKey((Serializable)kbFolderId);
	}

	/**
	 * Returns the k b folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the k b folder
	 * @return the k b folder, or <code>null</code> if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder fetchByPrimaryKey(Serializable primaryKey) {
		KBFolder kbFolder = (KBFolder)EntityCacheUtil.getResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
				KBFolderImpl.class, primaryKey);

		if (kbFolder == _nullKBFolder) {
			return null;
		}

		if (kbFolder == null) {
			Session session = null;

			try {
				session = openSession();

				kbFolder = (KBFolder)session.get(KBFolderImpl.class, primaryKey);

				if (kbFolder != null) {
					cacheResult(kbFolder);
				}
				else {
					EntityCacheUtil.putResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
						KBFolderImpl.class, primaryKey, _nullKBFolder);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
					KBFolderImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return kbFolder;
	}

	/**
	 * Returns the k b folder with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param kbFolderId the primary key of the k b folder
	 * @return the k b folder, or <code>null</code> if a k b folder with the primary key could not be found
	 */
	@Override
	public KBFolder fetchByPrimaryKey(long kbFolderId) {
		return fetchByPrimaryKey((Serializable)kbFolderId);
	}

	@Override
	public Map<Serializable, KBFolder> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, KBFolder> map = new HashMap<Serializable, KBFolder>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			KBFolder kbFolder = fetchByPrimaryKey(primaryKey);

			if (kbFolder != null) {
				map.put(primaryKey, kbFolder);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			KBFolder kbFolder = (KBFolder)EntityCacheUtil.getResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
					KBFolderImpl.class, primaryKey);

			if (kbFolder == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, kbFolder);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_KBFOLDER_WHERE_PKS_IN);

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

			for (KBFolder kbFolder : (List<KBFolder>)q.list()) {
				map.put(kbFolder.getPrimaryKeyObj(), kbFolder);

				cacheResult(kbFolder);

				uncachedPrimaryKeys.remove(kbFolder.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				EntityCacheUtil.putResult(KBFolderModelImpl.ENTITY_CACHE_ENABLED,
					KBFolderImpl.class, primaryKey, _nullKBFolder);
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
	 * Returns all the k b folders.
	 *
	 * @return the k b folders
	 */
	@Override
	public List<KBFolder> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the k b folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @return the range of k b folders
	 */
	@Override
	public List<KBFolder> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the k b folders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link KBFolderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of k b folders
	 * @param end the upper bound of the range of k b folders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of k b folders
	 */
	@Override
	public List<KBFolder> findAll(int start, int end,
		OrderByComparator<KBFolder> orderByComparator) {
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

		List<KBFolder> list = (List<KBFolder>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_KBFOLDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_KBFOLDER;

				if (pagination) {
					sql = sql.concat(KBFolderModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<KBFolder>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the k b folders from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (KBFolder kbFolder : findAll()) {
			remove(kbFolder);
		}
	}

	/**
	 * Returns the number of k b folders.
	 *
	 * @return the number of k b folders
	 */
	@Override
	public int countAll() {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_KBFOLDER);

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
		return KBFolderModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the k b folder persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		EntityCacheUtil.removeCache(KBFolderImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_KBFOLDER = "SELECT kbFolder FROM KBFolder kbFolder";
	private static final String _SQL_SELECT_KBFOLDER_WHERE_PKS_IN = "SELECT kbFolder FROM KBFolder kbFolder WHERE kbFolderId IN (";
	private static final String _SQL_SELECT_KBFOLDER_WHERE = "SELECT kbFolder FROM KBFolder kbFolder WHERE ";
	private static final String _SQL_COUNT_KBFOLDER = "SELECT COUNT(kbFolder) FROM KBFolder kbFolder";
	private static final String _SQL_COUNT_KBFOLDER_WHERE = "SELECT COUNT(kbFolder) FROM KBFolder kbFolder WHERE ";
	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN = "kbFolder.kbFolderId";
	private static final String _FILTER_SQL_SELECT_KBFOLDER_WHERE = "SELECT DISTINCT {kbFolder.*} FROM KBFolder kbFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_KBFOLDER_NO_INLINE_DISTINCT_WHERE_1 =
		"SELECT {KBFolder.*} FROM (SELECT DISTINCT kbFolder.kbFolderId FROM KBFolder kbFolder WHERE ";
	private static final String _FILTER_SQL_SELECT_KBFOLDER_NO_INLINE_DISTINCT_WHERE_2 =
		") TEMP_TABLE INNER JOIN KBFolder ON TEMP_TABLE.kbFolderId = KBFolder.kbFolderId";
	private static final String _FILTER_SQL_COUNT_KBFOLDER_WHERE = "SELECT COUNT(DISTINCT kbFolder.kbFolderId) AS COUNT_VALUE FROM KBFolder kbFolder WHERE ";
	private static final String _FILTER_ENTITY_ALIAS = "kbFolder";
	private static final String _FILTER_ENTITY_TABLE = "KBFolder";
	private static final String _ORDER_BY_ENTITY_ALIAS = "kbFolder.";
	private static final String _ORDER_BY_ENTITY_TABLE = "KBFolder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No KBFolder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No KBFolder exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(KBFolderPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid"
			});
	private static final KBFolder _nullKBFolder = new KBFolderImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<KBFolder> toCacheModel() {
				return _nullKBFolderCacheModel;
			}
		};

	private static final CacheModel<KBFolder> _nullKBFolderCacheModel = new CacheModel<KBFolder>() {
			@Override
			public KBFolder toEntityModel() {
				return _nullKBFolder;
			}
		};
}