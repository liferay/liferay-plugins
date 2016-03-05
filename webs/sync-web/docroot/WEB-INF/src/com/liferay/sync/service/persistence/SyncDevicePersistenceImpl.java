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

package com.liferay.sync.service.persistence;

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
import com.liferay.portal.kernel.util.CharPool;
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

import com.liferay.sync.NoSuchDeviceException;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.model.impl.SyncDeviceImpl;
import com.liferay.sync.model.impl.SyncDeviceModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the sync device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDevicePersistence
 * @see SyncDeviceUtil
 * @generated
 */
public class SyncDevicePersistenceImpl extends BasePersistenceImpl<SyncDevice>
	implements SyncDevicePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SyncDeviceUtil} to access the sync device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SyncDeviceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] { String.class.getName() },
			SyncDeviceModelImpl.UUID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] { String.class.getName() });

	/**
	 * Returns all the sync devices where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync devices where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @return the range of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByUuid(String uuid, int start, int end)
		throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync devices where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByUuid(String uuid, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
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

		List<SyncDevice> list = (List<SyncDevice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SyncDevice syncDevice : list) {
				if (!Validator.equals(uuid, syncDevice.getUuid())) {
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

			query.append(_SQL_SELECT_SYNCDEVICE_WHERE);

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
				query.append(SyncDeviceModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SyncDevice>(list);
				}
				else {
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync device in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByUuid_First(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByUuid_First(uuid, orderByComparator);

		if (syncDevice != null) {
			return syncDevice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the first sync device in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync device, or <code>null</code> if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByUuid_First(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		List<SyncDevice> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync device in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByUuid_Last(String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByUuid_Last(uuid, orderByComparator);

		if (syncDevice != null) {
			return syncDevice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the last sync device in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync device, or <code>null</code> if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByUuid_Last(String uuid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<SyncDevice> list = findByUuid(uuid, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync devices before and after the current sync device in the ordered set where uuid = &#63;.
	 *
	 * @param syncDeviceId the primary key of the current sync device
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice[] findByUuid_PrevAndNext(long syncDeviceId, String uuid,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = findByPrimaryKey(syncDeviceId);

		Session session = null;

		try {
			session = openSession();

			SyncDevice[] array = new SyncDeviceImpl[3];

			array[0] = getByUuid_PrevAndNext(session, syncDevice, uuid,
					orderByComparator, true);

			array[1] = syncDevice;

			array[2] = getByUuid_PrevAndNext(session, syncDevice, uuid,
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

	protected SyncDevice getByUuid_PrevAndNext(Session session,
		SyncDevice syncDevice, String uuid,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDEVICE_WHERE);

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
			query.append(SyncDeviceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncDevice);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDevice> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync devices where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid(String uuid) throws SystemException {
		for (SyncDevice syncDevice : findByUuid(uuid, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(syncDevice);
		}
	}

	/**
	 * Returns the number of sync devices where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching sync devices
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

			query.append(_SQL_COUNT_SYNCDEVICE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_UUID_1 = "syncDevice.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "syncDevice.uuid = ?";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(syncDevice.uuid IS NULL OR syncDevice.uuid = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_UUID_C = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C =
		new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() },
			SyncDeviceModelImpl.UUID_COLUMN_BITMASK |
			SyncDeviceModelImpl.COMPANYID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_UUID_C = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] { String.class.getName(), Long.class.getName() });

	/**
	 * Returns all the sync devices where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByUuid_C(String uuid, long companyId)
		throws SystemException {
		return findByUuid_C(uuid, companyId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync devices where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @return the range of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByUuid_C(String uuid, long companyId,
		int start, int end) throws SystemException {
		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync devices where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByUuid_C(String uuid, long companyId,
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

		List<SyncDevice> list = (List<SyncDevice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SyncDevice syncDevice : list) {
				if (!Validator.equals(uuid, syncDevice.getUuid()) ||
						(companyId != syncDevice.getCompanyId())) {
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

			query.append(_SQL_SELECT_SYNCDEVICE_WHERE);

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
				query.append(SyncDeviceModelImpl.ORDER_BY_JPQL);
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
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SyncDevice>(list);
				}
				else {
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByUuid_C_First(uuid, companyId,
				orderByComparator);

		if (syncDevice != null) {
			return syncDevice;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the first sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync device, or <code>null</code> if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByUuid_C_First(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		List<SyncDevice> list = findByUuid_C(uuid, companyId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByUuid_C_Last(uuid, companyId,
				orderByComparator);

		if (syncDevice != null) {
			return syncDevice;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the last sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync device, or <code>null</code> if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByUuid_C_Last(String uuid, long companyId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<SyncDevice> list = findByUuid_C(uuid, companyId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync devices before and after the current sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param syncDeviceId the primary key of the current sync device
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice[] findByUuid_C_PrevAndNext(long syncDeviceId,
		String uuid, long companyId, OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = findByPrimaryKey(syncDeviceId);

		Session session = null;

		try {
			session = openSession();

			SyncDevice[] array = new SyncDeviceImpl[3];

			array[0] = getByUuid_C_PrevAndNext(session, syncDevice, uuid,
					companyId, orderByComparator, true);

			array[1] = syncDevice;

			array[2] = getByUuid_C_PrevAndNext(session, syncDevice, uuid,
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

	protected SyncDevice getByUuid_C_PrevAndNext(Session session,
		SyncDevice syncDevice, String uuid, long companyId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDEVICE_WHERE);

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
			query.append(SyncDeviceModelImpl.ORDER_BY_JPQL);
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
			Object[] values = orderByComparator.getOrderByConditionValues(syncDevice);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDevice> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync devices where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId)
		throws SystemException {
		for (SyncDevice syncDevice : findByUuid_C(uuid, companyId,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDevice);
		}
	}

	/**
	 * Returns the number of sync devices where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching sync devices
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

			query.append(_SQL_COUNT_SYNCDEVICE_WHERE);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_1 = "syncDevice.uuid IS NULL AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_2 = "syncDevice.uuid = ? AND ";
	private static final String _FINDER_COLUMN_UUID_C_UUID_3 = "(syncDevice.uuid IS NULL OR syncDevice.uuid = '') AND ";
	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 = "syncDevice.companyId = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_C_U = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, SyncDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByC_U",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_U = new FinderPath(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByC_U",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the sync devices where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @return the matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByC_U(long companyId, String userName)
		throws SystemException {
		return findByC_U(companyId, userName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync devices where companyId = &#63; and userName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @return the range of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByC_U(long companyId, String userName,
		int start, int end) throws SystemException {
		return findByC_U(companyId, userName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync devices where companyId = &#63; and userName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findByC_U(long companyId, String userName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_C_U;
		finderArgs = new Object[] {
				companyId, userName,
				
				start, end, orderByComparator
			};

		List<SyncDevice> list = (List<SyncDevice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (SyncDevice syncDevice : list) {
				if ((companyId != syncDevice.getCompanyId()) ||
						!StringUtil.wildcardMatches(syncDevice.getUserName(),
							userName, CharPool.UNDERLINE, CharPool.PERCENT,
							CharPool.BACK_SLASH, false)) {
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

			query.append(_SQL_SELECT_SYNCDEVICE_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			boolean bindUserName = false;

			if (userName == null) {
				query.append(_FINDER_COLUMN_C_U_USERNAME_1);
			}
			else if (userName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_U_USERNAME_3);
			}
			else {
				bindUserName = true;

				query.append(_FINDER_COLUMN_C_U_USERNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(SyncDeviceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindUserName) {
					qPos.add(userName.toLowerCase());
				}

				if (!pagination) {
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SyncDevice>(list);
				}
				else {
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
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
	 * Returns the first sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByC_U_First(long companyId, String userName,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByC_U_First(companyId, userName,
				orderByComparator);

		if (syncDevice != null) {
			return syncDevice;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userName=");
		msg.append(userName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the first sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching sync device, or <code>null</code> if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByC_U_First(long companyId, String userName,
		OrderByComparator orderByComparator) throws SystemException {
		List<SyncDevice> list = findByC_U(companyId, userName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByC_U_Last(long companyId, String userName,
		OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByC_U_Last(companyId, userName,
				orderByComparator);

		if (syncDevice != null) {
			return syncDevice;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", userName=");
		msg.append(userName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the last sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching sync device, or <code>null</code> if a matching sync device could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByC_U_Last(long companyId, String userName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByC_U(companyId, userName);

		if (count == 0) {
			return null;
		}

		List<SyncDevice> list = findByC_U(companyId, userName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the sync devices before and after the current sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param syncDeviceId the primary key of the current sync device
	 * @param companyId the company ID
	 * @param userName the user name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice[] findByC_U_PrevAndNext(long syncDeviceId,
		long companyId, String userName, OrderByComparator orderByComparator)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = findByPrimaryKey(syncDeviceId);

		Session session = null;

		try {
			session = openSession();

			SyncDevice[] array = new SyncDeviceImpl[3];

			array[0] = getByC_U_PrevAndNext(session, syncDevice, companyId,
					userName, orderByComparator, true);

			array[1] = syncDevice;

			array[2] = getByC_U_PrevAndNext(session, syncDevice, companyId,
					userName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected SyncDevice getByC_U_PrevAndNext(Session session,
		SyncDevice syncDevice, long companyId, String userName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_SYNCDEVICE_WHERE);

		query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

		boolean bindUserName = false;

		if (userName == null) {
			query.append(_FINDER_COLUMN_C_U_USERNAME_1);
		}
		else if (userName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_C_U_USERNAME_3);
		}
		else {
			bindUserName = true;

			query.append(_FINDER_COLUMN_C_U_USERNAME_2);
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
			query.append(SyncDeviceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		if (bindUserName) {
			qPos.add(userName.toLowerCase());
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(syncDevice);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<SyncDevice> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the sync devices where companyId = &#63; and userName LIKE &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByC_U(long companyId, String userName)
		throws SystemException {
		for (SyncDevice syncDevice : findByC_U(companyId, userName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(syncDevice);
		}
	}

	/**
	 * Returns the number of sync devices where companyId = &#63; and userName LIKE &#63;.
	 *
	 * @param companyId the company ID
	 * @param userName the user name
	 * @return the number of matching sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByC_U(long companyId, String userName)
		throws SystemException {
		FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_C_U;

		Object[] finderArgs = new Object[] { companyId, userName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_SYNCDEVICE_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			boolean bindUserName = false;

			if (userName == null) {
				query.append(_FINDER_COLUMN_C_U_USERNAME_1);
			}
			else if (userName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_C_U_USERNAME_3);
			}
			else {
				bindUserName = true;

				query.append(_FINDER_COLUMN_C_U_USERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				if (bindUserName) {
					qPos.add(userName.toLowerCase());
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

	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "syncDevice.companyId = ? AND ";
	private static final String _FINDER_COLUMN_C_U_USERNAME_1 = "syncDevice.userName LIKE NULL";
	private static final String _FINDER_COLUMN_C_U_USERNAME_2 = "lower(syncDevice.userName) LIKE ?";
	private static final String _FINDER_COLUMN_C_U_USERNAME_3 = "(syncDevice.userName IS NULL OR syncDevice.userName LIKE '')";

	public SyncDevicePersistenceImpl() {
		setModelClass(SyncDevice.class);
	}

	/**
	 * Caches the sync device in the entity cache if it is enabled.
	 *
	 * @param syncDevice the sync device
	 */
	@Override
	public void cacheResult(SyncDevice syncDevice) {
		EntityCacheUtil.putResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceImpl.class, syncDevice.getPrimaryKey(), syncDevice);

		syncDevice.resetOriginalValues();
	}

	/**
	 * Caches the sync devices in the entity cache if it is enabled.
	 *
	 * @param syncDevices the sync devices
	 */
	@Override
	public void cacheResult(List<SyncDevice> syncDevices) {
		for (SyncDevice syncDevice : syncDevices) {
			if (EntityCacheUtil.getResult(
						SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
						SyncDeviceImpl.class, syncDevice.getPrimaryKey()) == null) {
				cacheResult(syncDevice);
			}
			else {
				syncDevice.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sync devices.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(SyncDeviceImpl.class.getName());
		}

		EntityCacheUtil.clearCache(SyncDeviceImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sync device.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SyncDevice syncDevice) {
		EntityCacheUtil.removeResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceImpl.class, syncDevice.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SyncDevice> syncDevices) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SyncDevice syncDevice : syncDevices) {
			EntityCacheUtil.removeResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
				SyncDeviceImpl.class, syncDevice.getPrimaryKey());
		}
	}

	/**
	 * Creates a new sync device with the primary key. Does not add the sync device to the database.
	 *
	 * @param syncDeviceId the primary key for the new sync device
	 * @return the new sync device
	 */
	@Override
	public SyncDevice create(long syncDeviceId) {
		SyncDevice syncDevice = new SyncDeviceImpl();

		syncDevice.setNew(true);
		syncDevice.setPrimaryKey(syncDeviceId);

		String uuid = PortalUUIDUtil.generate();

		syncDevice.setUuid(uuid);

		return syncDevice;
	}

	/**
	 * Removes the sync device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param syncDeviceId the primary key of the sync device
	 * @return the sync device that was removed
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice remove(long syncDeviceId)
		throws NoSuchDeviceException, SystemException {
		return remove((Serializable)syncDeviceId);
	}

	/**
	 * Removes the sync device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sync device
	 * @return the sync device that was removed
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice remove(Serializable primaryKey)
		throws NoSuchDeviceException, SystemException {
		Session session = null;

		try {
			session = openSession();

			SyncDevice syncDevice = (SyncDevice)session.get(SyncDeviceImpl.class,
					primaryKey);

			if (syncDevice == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeviceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(syncDevice);
		}
		catch (NoSuchDeviceException nsee) {
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
	protected SyncDevice removeImpl(SyncDevice syncDevice)
		throws SystemException {
		syncDevice = toUnwrappedModel(syncDevice);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(syncDevice)) {
				syncDevice = (SyncDevice)session.get(SyncDeviceImpl.class,
						syncDevice.getPrimaryKeyObj());
			}

			if (syncDevice != null) {
				session.delete(syncDevice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (syncDevice != null) {
			clearCache(syncDevice);
		}

		return syncDevice;
	}

	@Override
	public SyncDevice updateImpl(com.liferay.sync.model.SyncDevice syncDevice)
		throws SystemException {
		syncDevice = toUnwrappedModel(syncDevice);

		boolean isNew = syncDevice.isNew();

		SyncDeviceModelImpl syncDeviceModelImpl = (SyncDeviceModelImpl)syncDevice;

		if (Validator.isNull(syncDevice.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			syncDevice.setUuid(uuid);
		}

		Session session = null;

		try {
			session = openSession();

			if (syncDevice.isNew()) {
				session.save(syncDevice);

				syncDevice.setNew(false);
			}
			else {
				session.merge(syncDevice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !SyncDeviceModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((syncDeviceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDeviceModelImpl.getOriginalUuid()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);

				args = new Object[] { syncDeviceModelImpl.getUuid() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID,
					args);
			}

			if ((syncDeviceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						syncDeviceModelImpl.getOriginalUuid(),
						syncDeviceModelImpl.getOriginalCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);

				args = new Object[] {
						syncDeviceModelImpl.getUuid(),
						syncDeviceModelImpl.getCompanyId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_UUID_C, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_UUID_C,
					args);
			}
		}

		EntityCacheUtil.putResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
			SyncDeviceImpl.class, syncDevice.getPrimaryKey(), syncDevice);

		return syncDevice;
	}

	protected SyncDevice toUnwrappedModel(SyncDevice syncDevice) {
		if (syncDevice instanceof SyncDeviceImpl) {
			return syncDevice;
		}

		SyncDeviceImpl syncDeviceImpl = new SyncDeviceImpl();

		syncDeviceImpl.setNew(syncDevice.isNew());
		syncDeviceImpl.setPrimaryKey(syncDevice.getPrimaryKey());

		syncDeviceImpl.setUuid(syncDevice.getUuid());
		syncDeviceImpl.setSyncDeviceId(syncDevice.getSyncDeviceId());
		syncDeviceImpl.setCompanyId(syncDevice.getCompanyId());
		syncDeviceImpl.setUserId(syncDevice.getUserId());
		syncDeviceImpl.setUserName(syncDevice.getUserName());
		syncDeviceImpl.setCreateDate(syncDevice.getCreateDate());
		syncDeviceImpl.setModifiedDate(syncDevice.getModifiedDate());
		syncDeviceImpl.setType(syncDevice.getType());
		syncDeviceImpl.setBuildNumber(syncDevice.getBuildNumber());
		syncDeviceImpl.setFeatureSet(syncDevice.getFeatureSet());
		syncDeviceImpl.setStatus(syncDevice.getStatus());

		return syncDeviceImpl;
	}

	/**
	 * Returns the sync device with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync device
	 * @return the sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeviceException, SystemException {
		SyncDevice syncDevice = fetchByPrimaryKey(primaryKey);

		if (syncDevice == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeviceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return syncDevice;
	}

	/**
	 * Returns the sync device with the primary key or throws a {@link com.liferay.sync.NoSuchDeviceException} if it could not be found.
	 *
	 * @param syncDeviceId the primary key of the sync device
	 * @return the sync device
	 * @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice findByPrimaryKey(long syncDeviceId)
		throws NoSuchDeviceException, SystemException {
		return findByPrimaryKey((Serializable)syncDeviceId);
	}

	/**
	 * Returns the sync device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sync device
	 * @return the sync device, or <code>null</code> if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		SyncDevice syncDevice = (SyncDevice)EntityCacheUtil.getResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
				SyncDeviceImpl.class, primaryKey);

		if (syncDevice == _nullSyncDevice) {
			return null;
		}

		if (syncDevice == null) {
			Session session = null;

			try {
				session = openSession();

				syncDevice = (SyncDevice)session.get(SyncDeviceImpl.class,
						primaryKey);

				if (syncDevice != null) {
					cacheResult(syncDevice);
				}
				else {
					EntityCacheUtil.putResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
						SyncDeviceImpl.class, primaryKey, _nullSyncDevice);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(SyncDeviceModelImpl.ENTITY_CACHE_ENABLED,
					SyncDeviceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return syncDevice;
	}

	/**
	 * Returns the sync device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param syncDeviceId the primary key of the sync device
	 * @return the sync device, or <code>null</code> if a sync device with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public SyncDevice fetchByPrimaryKey(long syncDeviceId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)syncDeviceId);
	}

	/**
	 * Returns all the sync devices.
	 *
	 * @return the sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sync devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @return the range of sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sync devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.sync.model.impl.SyncDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sync devices
	 * @param end the upper bound of the range of sync devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sync devices
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<SyncDevice> findAll(int start, int end,
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

		List<SyncDevice> list = (List<SyncDevice>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_SYNCDEVICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SYNCDEVICE;

				if (pagination) {
					sql = sql.concat(SyncDeviceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<SyncDevice>(list);
				}
				else {
					list = (List<SyncDevice>)QueryUtil.list(q, getDialect(),
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
	 * Removes all the sync devices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (SyncDevice syncDevice : findAll()) {
			remove(syncDevice);
		}
	}

	/**
	 * Returns the number of sync devices.
	 *
	 * @return the number of sync devices
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

				Query q = session.createQuery(_SQL_COUNT_SYNCDEVICE);

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
	 * Initializes the sync device persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.liferay.sync.model.SyncDevice")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<SyncDevice>> listenersList = new ArrayList<ModelListener<SyncDevice>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<SyncDevice>)InstanceFactory.newInstance(
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
		EntityCacheUtil.removeCache(SyncDeviceImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_SYNCDEVICE = "SELECT syncDevice FROM SyncDevice syncDevice";
	private static final String _SQL_SELECT_SYNCDEVICE_WHERE = "SELECT syncDevice FROM SyncDevice syncDevice WHERE ";
	private static final String _SQL_COUNT_SYNCDEVICE = "SELECT COUNT(syncDevice) FROM SyncDevice syncDevice";
	private static final String _SQL_COUNT_SYNCDEVICE_WHERE = "SELECT COUNT(syncDevice) FROM SyncDevice syncDevice WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "syncDevice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SyncDevice exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No SyncDevice exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(SyncDevicePersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"uuid", "type"
			});
	private static SyncDevice _nullSyncDevice = new SyncDeviceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<SyncDevice> toCacheModel() {
				return _nullSyncDeviceCacheModel;
			}
		};

	private static CacheModel<SyncDevice> _nullSyncDeviceCacheModel = new CacheModel<SyncDevice>() {
			@Override
			public SyncDevice toEntityModel() {
				return _nullSyncDevice;
			}
		};
}