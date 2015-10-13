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

package com.liferay.pushnotifications.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

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
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.liferay.pushnotifications.NoSuchDeviceException;
import com.liferay.pushnotifications.model.PushNotificationsDevice;
import com.liferay.pushnotifications.model.impl.PushNotificationsDeviceImpl;
import com.liferay.pushnotifications.model.impl.PushNotificationsDeviceModelImpl;
import com.liferay.pushnotifications.service.persistence.PushNotificationsDevicePersistence;

import java.io.Serializable;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the push notifications device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see PushNotificationsDevicePersistence
 * @see com.liferay.pushnotifications.service.persistence.PushNotificationsDeviceUtil
 * @generated
 */
@ProviderType
public class PushNotificationsDevicePersistenceImpl extends BasePersistenceImpl<PushNotificationsDevice>
	implements PushNotificationsDevicePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PushNotificationsDeviceUtil} to access the push notifications device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PushNotificationsDeviceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_FETCH_BY_TOKEN = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByToken", new String[] { String.class.getName() },
			PushNotificationsDeviceModelImpl.TOKEN_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TOKEN = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByToken",
			new String[] { String.class.getName() });

	/**
	 * Returns the push notifications device where token = &#63; or throws a {@link NoSuchDeviceException} if it could not be found.
	 *
	 * @param token the token
	 * @return the matching push notifications device
	 * @throws NoSuchDeviceException if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice findByToken(String token)
		throws NoSuchDeviceException {
		PushNotificationsDevice pushNotificationsDevice = fetchByToken(token);

		if (pushNotificationsDevice == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("token=");
			msg.append(token);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchDeviceException(msg.toString());
		}

		return pushNotificationsDevice;
	}

	/**
	 * Returns the push notifications device where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param token the token
	 * @return the matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice fetchByToken(String token) {
		return fetchByToken(token, true);
	}

	/**
	 * Returns the push notifications device where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param token the token
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice fetchByToken(String token,
		boolean retrieveFromCache) {
		Object[] finderArgs = new Object[] { token };

		Object result = null;

		if (retrieveFromCache) {
			result = finderCache.getResult(FINDER_PATH_FETCH_BY_TOKEN,
					finderArgs, this);
		}

		if (result instanceof PushNotificationsDevice) {
			PushNotificationsDevice pushNotificationsDevice = (PushNotificationsDevice)result;

			if (!Validator.equals(token, pushNotificationsDevice.getToken())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE);

			boolean bindToken = false;

			if (token == null) {
				query.append(_FINDER_COLUMN_TOKEN_TOKEN_1);
			}
			else if (token.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TOKEN_TOKEN_3);
			}
			else {
				bindToken = true;

				query.append(_FINDER_COLUMN_TOKEN_TOKEN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindToken) {
					qPos.add(token);
				}

				List<PushNotificationsDevice> list = q.list();

				if (list.isEmpty()) {
					finderCache.putResult(FINDER_PATH_FETCH_BY_TOKEN,
						finderArgs, list);
				}
				else {
					PushNotificationsDevice pushNotificationsDevice = list.get(0);

					result = pushNotificationsDevice;

					cacheResult(pushNotificationsDevice);

					if ((pushNotificationsDevice.getToken() == null) ||
							!pushNotificationsDevice.getToken().equals(token)) {
						finderCache.putResult(FINDER_PATH_FETCH_BY_TOKEN,
							finderArgs, pushNotificationsDevice);
					}
				}
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_FETCH_BY_TOKEN, finderArgs);

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
			return (PushNotificationsDevice)result;
		}
	}

	/**
	 * Removes the push notifications device where token = &#63; from the database.
	 *
	 * @param token the token
	 * @return the push notifications device that was removed
	 */
	@Override
	public PushNotificationsDevice removeByToken(String token)
		throws NoSuchDeviceException {
		PushNotificationsDevice pushNotificationsDevice = findByToken(token);

		return remove(pushNotificationsDevice);
	}

	/**
	 * Returns the number of push notifications devices where token = &#63;.
	 *
	 * @param token the token
	 * @return the number of matching push notifications devices
	 */
	@Override
	public int countByToken(String token) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TOKEN;

		Object[] finderArgs = new Object[] { token };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PUSHNOTIFICATIONSDEVICE_WHERE);

			boolean bindToken = false;

			if (token == null) {
				query.append(_FINDER_COLUMN_TOKEN_TOKEN_1);
			}
			else if (token.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TOKEN_TOKEN_3);
			}
			else {
				bindToken = true;

				query.append(_FINDER_COLUMN_TOKEN_TOKEN_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindToken) {
					qPos.add(token);
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

	private static final String _FINDER_COLUMN_TOKEN_TOKEN_1 = "pushNotificationsDevice.token IS NULL";
	private static final String _FINDER_COLUMN_TOKEN_TOKEN_2 = "pushNotificationsDevice.token = ?";
	private static final String _FINDER_COLUMN_TOKEN_TOKEN_3 = "(pushNotificationsDevice.token IS NULL OR pushNotificationsDevice.token = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_U_P = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_P",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_P = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_P",
			new String[] { Long.class.getName(), String.class.getName() },
			PushNotificationsDeviceModelImpl.USERID_COLUMN_BITMASK |
			PushNotificationsDeviceModelImpl.PLATFORM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_U_P = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_P",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_P = new FinderPath(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByU_P",
			new String[] { Long.class.getName(), String.class.getName() });

	/**
	 * Returns all the push notifications devices where userId = &#63; and platform = &#63;.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @return the matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long userId, String platform) {
		return findByU_P(userId, platform, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications devices where userId = &#63; and platform = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @return the range of matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long userId,
		String platform, int start, int end) {
		return findByU_P(userId, platform, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications devices where userId = &#63; and platform = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long userId,
		String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return findByU_P(userId, platform, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push notifications devices where userId = &#63; and platform = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long userId,
		String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_P;
			finderArgs = new Object[] { userId, platform };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_U_P;
			finderArgs = new Object[] {
					userId, platform,
					
					start, end, orderByComparator
				};
		}

		List<PushNotificationsDevice> list = null;

		if (retrieveFromCache) {
			list = (List<PushNotificationsDevice>)finderCache.getResult(finderPath,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushNotificationsDevice pushNotificationsDevice : list) {
					if ((userId != pushNotificationsDevice.getUserId()) ||
							!Validator.equals(platform,
								pushNotificationsDevice.getPlatform())) {
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

			query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE);

			query.append(_FINDER_COLUMN_U_P_USERID_2);

			boolean bindPlatform = false;

			if (platform == null) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_1);
			}
			else if (platform.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_3);
			}
			else {
				bindPlatform = true;

				query.append(_FINDER_COLUMN_U_P_PLATFORM_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PushNotificationsDeviceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindPlatform) {
					qPos.add(platform);
				}

				if (!pagination) {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Returns the first push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push notifications device
	 * @throws NoSuchDeviceException if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice findByU_P_First(long userId,
		String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws NoSuchDeviceException {
		PushNotificationsDevice pushNotificationsDevice = fetchByU_P_First(userId,
				platform, orderByComparator);

		if (pushNotificationsDevice != null) {
			return pushNotificationsDevice;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", platform=");
		msg.append(platform);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the first push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice fetchByU_P_First(long userId,
		String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		List<PushNotificationsDevice> list = findByU_P(userId, platform, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push notifications device
	 * @throws NoSuchDeviceException if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice findByU_P_Last(long userId, String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws NoSuchDeviceException {
		PushNotificationsDevice pushNotificationsDevice = fetchByU_P_Last(userId,
				platform, orderByComparator);

		if (pushNotificationsDevice != null) {
			return pushNotificationsDevice;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("userId=");
		msg.append(userId);

		msg.append(", platform=");
		msg.append(platform);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDeviceException(msg.toString());
	}

	/**
	 * Returns the last push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	 */
	@Override
	public PushNotificationsDevice fetchByU_P_Last(long userId,
		String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		int count = countByU_P(userId, platform);

		if (count == 0) {
			return null;
		}

		List<PushNotificationsDevice> list = findByU_P(userId, platform,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the push notifications devices before and after the current push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	 *
	 * @param pushNotificationsDeviceId the primary key of the current push notifications device
	 * @param userId the user ID
	 * @param platform the platform
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next push notifications device
	 * @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice[] findByU_P_PrevAndNext(
		long pushNotificationsDeviceId, long userId, String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws NoSuchDeviceException {
		PushNotificationsDevice pushNotificationsDevice = findByPrimaryKey(pushNotificationsDeviceId);

		Session session = null;

		try {
			session = openSession();

			PushNotificationsDevice[] array = new PushNotificationsDeviceImpl[3];

			array[0] = getByU_P_PrevAndNext(session, pushNotificationsDevice,
					userId, platform, orderByComparator, true);

			array[1] = pushNotificationsDevice;

			array[2] = getByU_P_PrevAndNext(session, pushNotificationsDevice,
					userId, platform, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PushNotificationsDevice getByU_P_PrevAndNext(Session session,
		PushNotificationsDevice pushNotificationsDevice, long userId,
		String platform,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE);

		query.append(_FINDER_COLUMN_U_P_USERID_2);

		boolean bindPlatform = false;

		if (platform == null) {
			query.append(_FINDER_COLUMN_U_P_PLATFORM_1);
		}
		else if (platform.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_U_P_PLATFORM_3);
		}
		else {
			bindPlatform = true;

			query.append(_FINDER_COLUMN_U_P_PLATFORM_2);
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
			query.append(PushNotificationsDeviceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(userId);

		if (bindPlatform) {
			qPos.add(platform);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pushNotificationsDevice);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PushNotificationsDevice> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the push notifications devices where userId = any &#63; and platform = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param platform the platform
	 * @return the matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long[] userIds,
		String platform) {
		return findByU_P(userIds, platform, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications devices where userId = any &#63; and platform = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param platform the platform
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @return the range of matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long[] userIds,
		String platform, int start, int end) {
		return findByU_P(userIds, platform, start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications devices where userId = any &#63; and platform = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userIds the user IDs
	 * @param platform the platform
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long[] userIds,
		String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return findByU_P(userIds, platform, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push notifications devices where userId = &#63; and platform = &#63;, optionally using the finder cache.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of matching push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findByU_P(long[] userIds,
		String platform, int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache) {
		if (userIds == null) {
			userIds = new long[0];
		}
		else if (userIds.length > 1) {
			userIds = ArrayUtil.unique(userIds);

			Arrays.sort(userIds);
		}

		if (userIds.length == 1) {
			return findByU_P(userIds[0], platform, start, end, orderByComparator);
		}

		boolean pagination = true;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderArgs = new Object[] { StringUtil.merge(userIds), platform };
		}
		else {
			finderArgs = new Object[] {
					StringUtil.merge(userIds), platform,
					
					start, end, orderByComparator
				};
		}

		List<PushNotificationsDevice> list = null;

		if (retrieveFromCache) {
			list = (List<PushNotificationsDevice>)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_P,
					finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (PushNotificationsDevice pushNotificationsDevice : list) {
					if (!ArrayUtil.contains(userIds,
								pushNotificationsDevice.getUserId()) ||
							!Validator.equals(platform,
								pushNotificationsDevice.getPlatform())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE);

			if (userIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_P_USERID_7);

				query.append(StringUtil.merge(userIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			boolean bindPlatform = false;

			if (platform == null) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_1);
			}
			else if (platform.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_3);
			}
			else {
				bindPlatform = true;

				query.append(_FINDER_COLUMN_U_P_PLATFORM_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(PushNotificationsDeviceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPlatform) {
					qPos.add(platform);
				}

				if (!pagination) {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_P,
					finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_FIND_BY_U_P,
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
	 * Removes all the push notifications devices where userId = &#63; and platform = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 */
	@Override
	public void removeByU_P(long userId, String platform) {
		for (PushNotificationsDevice pushNotificationsDevice : findByU_P(
				userId, platform, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(pushNotificationsDevice);
		}
	}

	/**
	 * Returns the number of push notifications devices where userId = &#63; and platform = &#63;.
	 *
	 * @param userId the user ID
	 * @param platform the platform
	 * @return the number of matching push notifications devices
	 */
	@Override
	public int countByU_P(long userId, String platform) {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_U_P;

		Object[] finderArgs = new Object[] { userId, platform };

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_PUSHNOTIFICATIONSDEVICE_WHERE);

			query.append(_FINDER_COLUMN_U_P_USERID_2);

			boolean bindPlatform = false;

			if (platform == null) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_1);
			}
			else if (platform.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_3);
			}
			else {
				bindPlatform = true;

				query.append(_FINDER_COLUMN_U_P_PLATFORM_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(userId);

				if (bindPlatform) {
					qPos.add(platform);
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

	/**
	 * Returns the number of push notifications devices where userId = any &#63; and platform = &#63;.
	 *
	 * @param userIds the user IDs
	 * @param platform the platform
	 * @return the number of matching push notifications devices
	 */
	@Override
	public int countByU_P(long[] userIds, String platform) {
		if (userIds == null) {
			userIds = new long[0];
		}
		else if (userIds.length > 1) {
			userIds = ArrayUtil.unique(userIds);

			Arrays.sort(userIds);
		}

		Object[] finderArgs = new Object[] { StringUtil.merge(userIds), platform };

		Long count = (Long)finderCache.getResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_P,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_PUSHNOTIFICATIONSDEVICE_WHERE);

			if (userIds.length > 0) {
				query.append(StringPool.OPEN_PARENTHESIS);

				query.append(_FINDER_COLUMN_U_P_USERID_7);

				query.append(StringUtil.merge(userIds));

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(StringPool.CLOSE_PARENTHESIS);

				query.append(WHERE_AND);
			}

			boolean bindPlatform = false;

			if (platform == null) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_1);
			}
			else if (platform.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_U_P_PLATFORM_3);
			}
			else {
				bindPlatform = true;

				query.append(_FINDER_COLUMN_U_P_PLATFORM_2);
			}

			query.setStringAt(removeConjunction(query.stringAt(query.index() -
						1)), query.index() - 1);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPlatform) {
					qPos.add(platform);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_P,
					finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_WITH_PAGINATION_COUNT_BY_U_P,
					finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_P_USERID_2 = "pushNotificationsDevice.userId = ? AND ";
	private static final String _FINDER_COLUMN_U_P_USERID_7 = "pushNotificationsDevice.userId IN (";
	private static final String _FINDER_COLUMN_U_P_PLATFORM_1 = "pushNotificationsDevice.platform IS NULL";
	private static final String _FINDER_COLUMN_U_P_PLATFORM_2 = "pushNotificationsDevice.platform = ?";
	private static final String _FINDER_COLUMN_U_P_PLATFORM_3 = "(pushNotificationsDevice.platform IS NULL OR pushNotificationsDevice.platform = '')";

	public PushNotificationsDevicePersistenceImpl() {
		setModelClass(PushNotificationsDevice.class);
	}

	/**
	 * Caches the push notifications device in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsDevice the push notifications device
	 */
	@Override
	public void cacheResult(PushNotificationsDevice pushNotificationsDevice) {
		entityCache.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			pushNotificationsDevice.getPrimaryKey(), pushNotificationsDevice);

		finderCache.putResult(FINDER_PATH_FETCH_BY_TOKEN,
			new Object[] { pushNotificationsDevice.getToken() },
			pushNotificationsDevice);

		pushNotificationsDevice.resetOriginalValues();
	}

	/**
	 * Caches the push notifications devices in the entity cache if it is enabled.
	 *
	 * @param pushNotificationsDevices the push notifications devices
	 */
	@Override
	public void cacheResult(
		List<PushNotificationsDevice> pushNotificationsDevices) {
		for (PushNotificationsDevice pushNotificationsDevice : pushNotificationsDevices) {
			if (entityCache.getResult(
						PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsDeviceImpl.class,
						pushNotificationsDevice.getPrimaryKey()) == null) {
				cacheResult(pushNotificationsDevice);
			}
			else {
				pushNotificationsDevice.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all push notifications devices.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PushNotificationsDeviceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the push notifications device.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PushNotificationsDevice pushNotificationsDevice) {
		entityCache.removeResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			pushNotificationsDevice.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((PushNotificationsDeviceModelImpl)pushNotificationsDevice);
	}

	@Override
	public void clearCache(
		List<PushNotificationsDevice> pushNotificationsDevices) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PushNotificationsDevice pushNotificationsDevice : pushNotificationsDevices) {
			entityCache.removeResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsDeviceImpl.class,
				pushNotificationsDevice.getPrimaryKey());

			clearUniqueFindersCache((PushNotificationsDeviceModelImpl)pushNotificationsDevice);
		}
	}

	protected void cacheUniqueFindersCache(
		PushNotificationsDeviceModelImpl pushNotificationsDeviceModelImpl,
		boolean isNew) {
		if (isNew) {
			Object[] args = new Object[] {
					pushNotificationsDeviceModelImpl.getToken()
				};

			finderCache.putResult(FINDER_PATH_COUNT_BY_TOKEN, args,
				Long.valueOf(1));
			finderCache.putResult(FINDER_PATH_FETCH_BY_TOKEN, args,
				pushNotificationsDeviceModelImpl);
		}
		else {
			if ((pushNotificationsDeviceModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_TOKEN.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushNotificationsDeviceModelImpl.getToken()
					};

				finderCache.putResult(FINDER_PATH_COUNT_BY_TOKEN, args,
					Long.valueOf(1));
				finderCache.putResult(FINDER_PATH_FETCH_BY_TOKEN, args,
					pushNotificationsDeviceModelImpl);
			}
		}
	}

	protected void clearUniqueFindersCache(
		PushNotificationsDeviceModelImpl pushNotificationsDeviceModelImpl) {
		Object[] args = new Object[] { pushNotificationsDeviceModelImpl.getToken() };

		finderCache.removeResult(FINDER_PATH_COUNT_BY_TOKEN, args);
		finderCache.removeResult(FINDER_PATH_FETCH_BY_TOKEN, args);

		if ((pushNotificationsDeviceModelImpl.getColumnBitmask() &
				FINDER_PATH_FETCH_BY_TOKEN.getColumnBitmask()) != 0) {
			args = new Object[] {
					pushNotificationsDeviceModelImpl.getOriginalToken()
				};

			finderCache.removeResult(FINDER_PATH_COUNT_BY_TOKEN, args);
			finderCache.removeResult(FINDER_PATH_FETCH_BY_TOKEN, args);
		}
	}

	/**
	 * Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	 *
	 * @param pushNotificationsDeviceId the primary key for the new push notifications device
	 * @return the new push notifications device
	 */
	@Override
	public PushNotificationsDevice create(long pushNotificationsDeviceId) {
		PushNotificationsDevice pushNotificationsDevice = new PushNotificationsDeviceImpl();

		pushNotificationsDevice.setNew(true);
		pushNotificationsDevice.setPrimaryKey(pushNotificationsDeviceId);

		return pushNotificationsDevice;
	}

	/**
	 * Removes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device that was removed
	 * @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice remove(long pushNotificationsDeviceId)
		throws NoSuchDeviceException {
		return remove((Serializable)pushNotificationsDeviceId);
	}

	/**
	 * Removes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the push notifications device
	 * @return the push notifications device that was removed
	 * @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice remove(Serializable primaryKey)
		throws NoSuchDeviceException {
		Session session = null;

		try {
			session = openSession();

			PushNotificationsDevice pushNotificationsDevice = (PushNotificationsDevice)session.get(PushNotificationsDeviceImpl.class,
					primaryKey);

			if (pushNotificationsDevice == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDeviceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pushNotificationsDevice);
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
	protected PushNotificationsDevice removeImpl(
		PushNotificationsDevice pushNotificationsDevice) {
		pushNotificationsDevice = toUnwrappedModel(pushNotificationsDevice);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(pushNotificationsDevice)) {
				pushNotificationsDevice = (PushNotificationsDevice)session.get(PushNotificationsDeviceImpl.class,
						pushNotificationsDevice.getPrimaryKeyObj());
			}

			if (pushNotificationsDevice != null) {
				session.delete(pushNotificationsDevice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (pushNotificationsDevice != null) {
			clearCache(pushNotificationsDevice);
		}

		return pushNotificationsDevice;
	}

	@Override
	public PushNotificationsDevice updateImpl(
		PushNotificationsDevice pushNotificationsDevice) {
		pushNotificationsDevice = toUnwrappedModel(pushNotificationsDevice);

		boolean isNew = pushNotificationsDevice.isNew();

		PushNotificationsDeviceModelImpl pushNotificationsDeviceModelImpl = (PushNotificationsDeviceModelImpl)pushNotificationsDevice;

		Session session = null;

		try {
			session = openSession();

			if (pushNotificationsDevice.isNew()) {
				session.save(pushNotificationsDevice);

				pushNotificationsDevice.setNew(false);
			}
			else {
				pushNotificationsDevice = (PushNotificationsDevice)session.merge(pushNotificationsDevice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PushNotificationsDeviceModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pushNotificationsDeviceModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_P.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						pushNotificationsDeviceModelImpl.getOriginalUserId(),
						pushNotificationsDeviceModelImpl.getOriginalPlatform()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_P,
					args);

				args = new Object[] {
						pushNotificationsDeviceModelImpl.getUserId(),
						pushNotificationsDeviceModelImpl.getPlatform()
					};

				finderCache.removeResult(FINDER_PATH_COUNT_BY_U_P, args);
				finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_U_P,
					args);
			}
		}

		entityCache.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
			PushNotificationsDeviceImpl.class,
			pushNotificationsDevice.getPrimaryKey(), pushNotificationsDevice,
			false);

		clearUniqueFindersCache(pushNotificationsDeviceModelImpl);
		cacheUniqueFindersCache(pushNotificationsDeviceModelImpl, isNew);

		pushNotificationsDevice.resetOriginalValues();

		return pushNotificationsDevice;
	}

	protected PushNotificationsDevice toUnwrappedModel(
		PushNotificationsDevice pushNotificationsDevice) {
		if (pushNotificationsDevice instanceof PushNotificationsDeviceImpl) {
			return pushNotificationsDevice;
		}

		PushNotificationsDeviceImpl pushNotificationsDeviceImpl = new PushNotificationsDeviceImpl();

		pushNotificationsDeviceImpl.setNew(pushNotificationsDevice.isNew());
		pushNotificationsDeviceImpl.setPrimaryKey(pushNotificationsDevice.getPrimaryKey());

		pushNotificationsDeviceImpl.setPushNotificationsDeviceId(pushNotificationsDevice.getPushNotificationsDeviceId());
		pushNotificationsDeviceImpl.setUserId(pushNotificationsDevice.getUserId());
		pushNotificationsDeviceImpl.setCreateDate(pushNotificationsDevice.getCreateDate());
		pushNotificationsDeviceImpl.setPlatform(pushNotificationsDevice.getPlatform());
		pushNotificationsDeviceImpl.setToken(pushNotificationsDevice.getToken());

		return pushNotificationsDeviceImpl;
	}

	/**
	 * Returns the push notifications device with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications device
	 * @return the push notifications device
	 * @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDeviceException {
		PushNotificationsDevice pushNotificationsDevice = fetchByPrimaryKey(primaryKey);

		if (pushNotificationsDevice == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDeviceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return pushNotificationsDevice;
	}

	/**
	 * Returns the push notifications device with the primary key or throws a {@link NoSuchDeviceException} if it could not be found.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device
	 * @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice findByPrimaryKey(
		long pushNotificationsDeviceId) throws NoSuchDeviceException {
		return findByPrimaryKey((Serializable)pushNotificationsDeviceId);
	}

	/**
	 * Returns the push notifications device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the push notifications device
	 * @return the push notifications device, or <code>null</code> if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice fetchByPrimaryKey(Serializable primaryKey) {
		PushNotificationsDevice pushNotificationsDevice = (PushNotificationsDevice)entityCache.getResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
				PushNotificationsDeviceImpl.class, primaryKey);

		if (pushNotificationsDevice == _nullPushNotificationsDevice) {
			return null;
		}

		if (pushNotificationsDevice == null) {
			Session session = null;

			try {
				session = openSession();

				pushNotificationsDevice = (PushNotificationsDevice)session.get(PushNotificationsDeviceImpl.class,
						primaryKey);

				if (pushNotificationsDevice != null) {
					cacheResult(pushNotificationsDevice);
				}
				else {
					entityCache.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
						PushNotificationsDeviceImpl.class, primaryKey,
						_nullPushNotificationsDevice);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsDeviceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return pushNotificationsDevice;
	}

	/**
	 * Returns the push notifications device with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param pushNotificationsDeviceId the primary key of the push notifications device
	 * @return the push notifications device, or <code>null</code> if a push notifications device with the primary key could not be found
	 */
	@Override
	public PushNotificationsDevice fetchByPrimaryKey(
		long pushNotificationsDeviceId) {
		return fetchByPrimaryKey((Serializable)pushNotificationsDeviceId);
	}

	@Override
	public Map<Serializable, PushNotificationsDevice> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, PushNotificationsDevice> map = new HashMap<Serializable, PushNotificationsDevice>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			PushNotificationsDevice pushNotificationsDevice = fetchByPrimaryKey(primaryKey);

			if (pushNotificationsDevice != null) {
				map.put(primaryKey, pushNotificationsDevice);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			PushNotificationsDevice pushNotificationsDevice = (PushNotificationsDevice)entityCache.getResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsDeviceImpl.class, primaryKey);

			if (pushNotificationsDevice == null) {
				if (uncachedPrimaryKeys == null) {
					uncachedPrimaryKeys = new HashSet<Serializable>();
				}

				uncachedPrimaryKeys.add(primaryKey);
			}
			else {
				map.put(primaryKey, pushNotificationsDevice);
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE_PKS_IN);

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

			for (PushNotificationsDevice pushNotificationsDevice : (List<PushNotificationsDevice>)q.list()) {
				map.put(pushNotificationsDevice.getPrimaryKeyObj(),
					pushNotificationsDevice);

				cacheResult(pushNotificationsDevice);

				uncachedPrimaryKeys.remove(pushNotificationsDevice.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PushNotificationsDeviceModelImpl.ENTITY_CACHE_ENABLED,
					PushNotificationsDeviceImpl.class, primaryKey,
					_nullPushNotificationsDevice);
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
	 * Returns all the push notifications devices.
	 *
	 * @return the push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the push notifications devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @return the range of push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the push notifications devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findAll(int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the push notifications devices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PushNotificationsDeviceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of push notifications devices
	 * @param end the upper bound of the range of push notifications devices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of push notifications devices
	 */
	@Override
	public List<PushNotificationsDevice> findAll(int start, int end,
		OrderByComparator<PushNotificationsDevice> orderByComparator,
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

		List<PushNotificationsDevice> list = null;

		if (retrieveFromCache) {
			list = (List<PushNotificationsDevice>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PUSHNOTIFICATIONSDEVICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PUSHNOTIFICATIONSDEVICE;

				if (pagination) {
					sql = sql.concat(PushNotificationsDeviceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<PushNotificationsDevice>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes all the push notifications devices from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (PushNotificationsDevice pushNotificationsDevice : findAll()) {
			remove(pushNotificationsDevice);
		}
	}

	/**
	 * Returns the number of push notifications devices.
	 *
	 * @return the number of push notifications devices
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PUSHNOTIFICATIONSDEVICE);

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
	protected Map<String, Integer> getTableColumnsMap() {
		return PushNotificationsDeviceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the push notifications device persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PushNotificationsDeviceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	protected EntityCache entityCache = EntityCacheUtil.getEntityCache();
	protected FinderCache finderCache = FinderCacheUtil.getFinderCache();
	private static final String _SQL_SELECT_PUSHNOTIFICATIONSDEVICE = "SELECT pushNotificationsDevice FROM PushNotificationsDevice pushNotificationsDevice";
	private static final String _SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE_PKS_IN =
		"SELECT pushNotificationsDevice FROM PushNotificationsDevice pushNotificationsDevice WHERE pushNotificationsDeviceId IN (";
	private static final String _SQL_SELECT_PUSHNOTIFICATIONSDEVICE_WHERE = "SELECT pushNotificationsDevice FROM PushNotificationsDevice pushNotificationsDevice WHERE ";
	private static final String _SQL_COUNT_PUSHNOTIFICATIONSDEVICE = "SELECT COUNT(pushNotificationsDevice) FROM PushNotificationsDevice pushNotificationsDevice";
	private static final String _SQL_COUNT_PUSHNOTIFICATIONSDEVICE_WHERE = "SELECT COUNT(pushNotificationsDevice) FROM PushNotificationsDevice pushNotificationsDevice WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pushNotificationsDevice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PushNotificationsDevice exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PushNotificationsDevice exists with the key {";
	private static final Log _log = LogFactoryUtil.getLog(PushNotificationsDevicePersistenceImpl.class);
	private static final PushNotificationsDevice _nullPushNotificationsDevice = new PushNotificationsDeviceImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PushNotificationsDevice> toCacheModel() {
				return _nullPushNotificationsDeviceCacheModel;
			}
		};

	private static final CacheModel<PushNotificationsDevice> _nullPushNotificationsDeviceCacheModel =
		new CacheModel<PushNotificationsDevice>() {
			@Override
			public PushNotificationsDevice toEntityModel() {
				return _nullPushNotificationsDevice;
			}
		};
}