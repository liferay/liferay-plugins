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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.sync.model.SyncDevice;

/**
 * The persistence interface for the sync device service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDevicePersistenceImpl
 * @see SyncDeviceUtil
 * @generated
 */
public interface SyncDevicePersistence extends BasePersistence<SyncDevice> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SyncDeviceUtil} to access the sync device persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the sync devices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching sync devices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDevice> findByUuid(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findByUuid(
		java.lang.String uuid, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findByUuid(
		java.lang.String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first sync device in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync device
	* @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice findByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the first sync device in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByUuid_First(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last sync device in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync device
	* @throws com.liferay.sync.NoSuchDeviceException if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice findByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the last sync device in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByUuid_Last(
		java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice[] findByUuid_PrevAndNext(
		long syncDeviceId, java.lang.String uuid,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Removes all the sync devices where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sync devices where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching sync devices
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid(java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sync devices where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the matching sync devices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDevice> findByUuid_C(
		java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findByUuid_C(
		java.lang.String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice findByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the first sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByUuid_C_First(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice findByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the last sync device in the ordered set where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByUuid_C_Last(
		java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice[] findByUuid_C_PrevAndNext(
		long syncDeviceId, java.lang.String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Removes all the sync devices where uuid = &#63; and companyId = &#63; from the database.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sync devices where uuid = &#63; and companyId = &#63;.
	*
	* @param uuid the uuid
	* @param companyId the company ID
	* @return the number of matching sync devices
	* @throws SystemException if a system exception occurred
	*/
	public int countByUuid_C(java.lang.String uuid, long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sync devices where companyId = &#63; and userName LIKE &#63;.
	*
	* @param companyId the company ID
	* @param userName the user name
	* @return the matching sync devices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDevice> findByC_U(
		long companyId, java.lang.String userName)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findByC_U(
		long companyId, java.lang.String userName, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findByC_U(
		long companyId, java.lang.String userName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice findByC_U_First(long companyId,
		java.lang.String userName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the first sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	*
	* @param companyId the company ID
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByC_U_First(long companyId,
		java.lang.String userName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice findByC_U_Last(long companyId,
		java.lang.String userName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the last sync device in the ordered set where companyId = &#63; and userName LIKE &#63;.
	*
	* @param companyId the company ID
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching sync device, or <code>null</code> if a matching sync device could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByC_U_Last(long companyId,
		java.lang.String userName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.sync.model.SyncDevice[] findByC_U_PrevAndNext(
		long syncDeviceId, long companyId, java.lang.String userName,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Removes all the sync devices where companyId = &#63; and userName LIKE &#63; from the database.
	*
	* @param companyId the company ID
	* @param userName the user name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_U(long companyId, java.lang.String userName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sync devices where companyId = &#63; and userName LIKE &#63;.
	*
	* @param companyId the company ID
	* @param userName the user name
	* @return the number of matching sync devices
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_U(long companyId, java.lang.String userName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Caches the sync device in the entity cache if it is enabled.
	*
	* @param syncDevice the sync device
	*/
	public void cacheResult(com.liferay.sync.model.SyncDevice syncDevice);

	/**
	* Caches the sync devices in the entity cache if it is enabled.
	*
	* @param syncDevices the sync devices
	*/
	public void cacheResult(
		java.util.List<com.liferay.sync.model.SyncDevice> syncDevices);

	/**
	* Creates a new sync device with the primary key. Does not add the sync device to the database.
	*
	* @param syncDeviceId the primary key for the new sync device
	* @return the new sync device
	*/
	public com.liferay.sync.model.SyncDevice create(long syncDeviceId);

	/**
	* Removes the sync device with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device that was removed
	* @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice remove(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	public com.liferay.sync.model.SyncDevice updateImpl(
		com.liferay.sync.model.SyncDevice syncDevice)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the sync device with the primary key or throws a {@link com.liferay.sync.NoSuchDeviceException} if it could not be found.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device
	* @throws com.liferay.sync.NoSuchDeviceException if a sync device with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice findByPrimaryKey(long syncDeviceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.sync.NoSuchDeviceException;

	/**
	* Returns the sync device with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param syncDeviceId the primary key of the sync device
	* @return the sync device, or <code>null</code> if a sync device with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.sync.model.SyncDevice fetchByPrimaryKey(
		long syncDeviceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the sync devices.
	*
	* @return the sync devices
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.sync.model.SyncDevice> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.sync.model.SyncDevice> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the sync devices from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of sync devices.
	*
	* @return the number of sync devices
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}