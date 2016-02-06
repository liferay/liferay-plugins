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

package com.liferay.pushnotifications.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.pushnotifications.exception.NoSuchDeviceException;
import com.liferay.pushnotifications.model.PushNotificationsDevice;

/**
 * The persistence interface for the push notifications device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Bruno Farache
 * @see com.liferay.pushnotifications.service.persistence.impl.PushNotificationsDevicePersistenceImpl
 * @see PushNotificationsDeviceUtil
 * @generated
 */
@ProviderType
public interface PushNotificationsDevicePersistence extends BasePersistence<PushNotificationsDevice> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link PushNotificationsDeviceUtil} to access the push notifications device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the push notifications device where token = &#63; or throws a {@link NoSuchDeviceException} if it could not be found.
	*
	* @param token the token
	* @return the matching push notifications device
	* @throws NoSuchDeviceException if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice findByToken(java.lang.String token)
		throws NoSuchDeviceException;

	/**
	* Returns the push notifications device where token = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param token the token
	* @return the matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice fetchByToken(java.lang.String token);

	/**
	* Returns the push notifications device where token = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param token the token
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice fetchByToken(java.lang.String token,
		boolean retrieveFromCache);

	/**
	* Removes the push notifications device where token = &#63; from the database.
	*
	* @param token the token
	* @return the push notifications device that was removed
	*/
	public PushNotificationsDevice removeByToken(java.lang.String token)
		throws NoSuchDeviceException;

	/**
	* Returns the number of push notifications devices where token = &#63;.
	*
	* @param token the token
	* @return the number of matching push notifications devices
	*/
	public int countByToken(java.lang.String token);

	/**
	* Returns all the push notifications devices where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @return the matching push notifications devices
	*/
	public java.util.List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform);

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
	public java.util.List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform, int start, int end);

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
	public java.util.List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator);

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
	public java.util.List<PushNotificationsDevice> findByU_P(long userId,
		java.lang.String platform, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications device
	* @throws NoSuchDeviceException if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice findByU_P_First(long userId,
		java.lang.String platform,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws NoSuchDeviceException;

	/**
	* Returns the first push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice fetchByU_P_First(long userId,
		java.lang.String platform,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator);

	/**
	* Returns the last push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications device
	* @throws NoSuchDeviceException if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice findByU_P_Last(long userId,
		java.lang.String platform,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws NoSuchDeviceException;

	/**
	* Returns the last push notifications device in the ordered set where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching push notifications device, or <code>null</code> if a matching push notifications device could not be found
	*/
	public PushNotificationsDevice fetchByU_P_Last(long userId,
		java.lang.String platform,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator);

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
	public PushNotificationsDevice[] findByU_P_PrevAndNext(
		long pushNotificationsDeviceId, long userId, java.lang.String platform,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator)
		throws NoSuchDeviceException;

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
	public java.util.List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform);

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
	public java.util.List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform, int start, int end);

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
	public java.util.List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator);

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
	public java.util.List<PushNotificationsDevice> findByU_P(long[] userIds,
		java.lang.String platform, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the push notifications devices where userId = &#63; and platform = &#63; from the database.
	*
	* @param userId the user ID
	* @param platform the platform
	*/
	public void removeByU_P(long userId, java.lang.String platform);

	/**
	* Returns the number of push notifications devices where userId = &#63; and platform = &#63;.
	*
	* @param userId the user ID
	* @param platform the platform
	* @return the number of matching push notifications devices
	*/
	public int countByU_P(long userId, java.lang.String platform);

	/**
	* Returns the number of push notifications devices where userId = any &#63; and platform = &#63;.
	*
	* @param userIds the user IDs
	* @param platform the platform
	* @return the number of matching push notifications devices
	*/
	public int countByU_P(long[] userIds, java.lang.String platform);

	/**
	* Caches the push notifications device in the entity cache if it is enabled.
	*
	* @param pushNotificationsDevice the push notifications device
	*/
	public void cacheResult(PushNotificationsDevice pushNotificationsDevice);

	/**
	* Caches the push notifications devices in the entity cache if it is enabled.
	*
	* @param pushNotificationsDevices the push notifications devices
	*/
	public void cacheResult(
		java.util.List<PushNotificationsDevice> pushNotificationsDevices);

	/**
	* Creates a new push notifications device with the primary key. Does not add the push notifications device to the database.
	*
	* @param pushNotificationsDeviceId the primary key for the new push notifications device
	* @return the new push notifications device
	*/
	public PushNotificationsDevice create(long pushNotificationsDeviceId);

	/**
	* Removes the push notifications device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device that was removed
	* @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	*/
	public PushNotificationsDevice remove(long pushNotificationsDeviceId)
		throws NoSuchDeviceException;

	public PushNotificationsDevice updateImpl(
		PushNotificationsDevice pushNotificationsDevice);

	/**
	* Returns the push notifications device with the primary key or throws a {@link NoSuchDeviceException} if it could not be found.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device
	* @throws NoSuchDeviceException if a push notifications device with the primary key could not be found
	*/
	public PushNotificationsDevice findByPrimaryKey(
		long pushNotificationsDeviceId) throws NoSuchDeviceException;

	/**
	* Returns the push notifications device with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param pushNotificationsDeviceId the primary key of the push notifications device
	* @return the push notifications device, or <code>null</code> if a push notifications device with the primary key could not be found
	*/
	public PushNotificationsDevice fetchByPrimaryKey(
		long pushNotificationsDeviceId);

	@Override
	public java.util.Map<java.io.Serializable, PushNotificationsDevice> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the push notifications devices.
	*
	* @return the push notifications devices
	*/
	public java.util.List<PushNotificationsDevice> findAll();

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
	public java.util.List<PushNotificationsDevice> findAll(int start, int end);

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
	public java.util.List<PushNotificationsDevice> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator);

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
	public java.util.List<PushNotificationsDevice> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<PushNotificationsDevice> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the push notifications devices from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of push notifications devices.
	*
	* @return the number of push notifications devices
	*/
	public int countAll();
}