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

package com.liferay.chat.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.chat.model.Status;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.chat.service.persistence.impl.StatusPersistenceImpl
 * @see StatusUtil
 * @generated
 */
@ProviderType
public interface StatusPersistence extends BasePersistence<Status> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link StatusUtil} to access the status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the status where userId = &#63; or throws a {@link NoSuchStatusException} if it could not be found.
	*
	* @param userId the user ID
	* @return the matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the status where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @return the matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByUserId(long userId);

	/**
	* Returns the status where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByUserId(long userId, boolean retrieveFromCache);

	/**
	* Removes the status where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @return the status that was removed
	*/
	public Status removeByUserId(long userId)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the number of statuses where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching statuses
	*/
	public int countByUserId(long userId);

	/**
	* Returns all the statuses where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the matching statuses
	*/
	public java.util.List<Status> findByModifiedDate(long modifiedDate);

	/**
	* Returns a range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of matching statuses
	*/
	public java.util.List<Status> findByModifiedDate(long modifiedDate,
		int start, int end);

	/**
	* Returns an ordered range of all the statuses where modifiedDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching statuses
	*/
	public java.util.List<Status> findByModifiedDate(long modifiedDate,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByModifiedDate_First(long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByModifiedDate_First(long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByModifiedDate_Last(long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByModifiedDate_Last(long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the statuses before and after the current status in the ordered set where modifiedDate = &#63;.
	*
	* @param statusId the primary key of the current status
	* @param modifiedDate the modified date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public Status[] findByModifiedDate_PrevAndNext(long statusId,
		long modifiedDate,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Removes all the statuses where modifiedDate = &#63; from the database.
	*
	* @param modifiedDate the modified date
	*/
	public void removeByModifiedDate(long modifiedDate);

	/**
	* Returns the number of statuses where modifiedDate = &#63;.
	*
	* @param modifiedDate the modified date
	* @return the number of matching statuses
	*/
	public int countByModifiedDate(long modifiedDate);

	/**
	* Returns all the statuses where online = &#63;.
	*
	* @param online the online
	* @return the matching statuses
	*/
	public java.util.List<Status> findByOnline(boolean online);

	/**
	* Returns a range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of matching statuses
	*/
	public java.util.List<Status> findByOnline(boolean online, int start,
		int end);

	/**
	* Returns an ordered range of all the statuses where online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching statuses
	*/
	public java.util.List<Status> findByOnline(boolean online, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the first status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByOnline_First(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the first status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByOnline_First(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the last status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByOnline_Last(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the last status in the ordered set where online = &#63;.
	*
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByOnline_Last(boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the statuses before and after the current status in the ordered set where online = &#63;.
	*
	* @param statusId the primary key of the current status
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public Status[] findByOnline_PrevAndNext(long statusId, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Removes all the statuses where online = &#63; from the database.
	*
	* @param online the online
	*/
	public void removeByOnline(boolean online);

	/**
	* Returns the number of statuses where online = &#63;.
	*
	* @param online the online
	* @return the number of matching statuses
	*/
	public int countByOnline(boolean online);

	/**
	* Returns all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @return the matching statuses
	*/
	public java.util.List<Status> findByM_O(long modifiedDate, boolean online);

	/**
	* Returns a range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of matching statuses
	*/
	public java.util.List<Status> findByM_O(long modifiedDate, boolean online,
		int start, int end);

	/**
	* Returns an ordered range of all the statuses where modifiedDate = &#63; and online = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching statuses
	*/
	public java.util.List<Status> findByM_O(long modifiedDate, boolean online,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByM_O_First(long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the first status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByM_O_First(long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status
	* @throws NoSuchStatusException if a matching status could not be found
	*/
	public Status findByM_O_Last(long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the last status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching status, or <code>null</code> if a matching status could not be found
	*/
	public Status fetchByM_O_Last(long modifiedDate, boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Returns the statuses before and after the current status in the ordered set where modifiedDate = &#63; and online = &#63;.
	*
	* @param statusId the primary key of the current status
	* @param modifiedDate the modified date
	* @param online the online
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public Status[] findByM_O_PrevAndNext(long statusId, long modifiedDate,
		boolean online,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Removes all the statuses where modifiedDate = &#63; and online = &#63; from the database.
	*
	* @param modifiedDate the modified date
	* @param online the online
	*/
	public void removeByM_O(long modifiedDate, boolean online);

	/**
	* Returns the number of statuses where modifiedDate = &#63; and online = &#63;.
	*
	* @param modifiedDate the modified date
	* @param online the online
	* @return the number of matching statuses
	*/
	public int countByM_O(long modifiedDate, boolean online);

	/**
	* Caches the status in the entity cache if it is enabled.
	*
	* @param status the status
	*/
	public void cacheResult(Status status);

	/**
	* Caches the statuses in the entity cache if it is enabled.
	*
	* @param statuses the statuses
	*/
	public void cacheResult(java.util.List<Status> statuses);

	/**
	* Creates a new status with the primary key. Does not add the status to the database.
	*
	* @param statusId the primary key for the new status
	* @return the new status
	*/
	public Status create(long statusId);

	/**
	* Removes the status with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param statusId the primary key of the status
	* @return the status that was removed
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public Status remove(long statusId)
		throws com.liferay.chat.NoSuchStatusException;

	public Status updateImpl(Status status);

	/**
	* Returns the status with the primary key or throws a {@link NoSuchStatusException} if it could not be found.
	*
	* @param statusId the primary key of the status
	* @return the status
	* @throws NoSuchStatusException if a status with the primary key could not be found
	*/
	public Status findByPrimaryKey(long statusId)
		throws com.liferay.chat.NoSuchStatusException;

	/**
	* Returns the status with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param statusId the primary key of the status
	* @return the status, or <code>null</code> if a status with the primary key could not be found
	*/
	public Status fetchByPrimaryKey(long statusId);

	@Override
	public java.util.Map<java.io.Serializable, Status> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the statuses.
	*
	* @return the statuses
	*/
	public java.util.List<Status> findAll();

	/**
	* Returns a range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @return the range of statuses
	*/
	public java.util.List<Status> findAll(int start, int end);

	/**
	* Returns an ordered range of all the statuses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link StatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of statuses
	* @param end the upper bound of the range of statuses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of statuses
	*/
	public java.util.List<Status> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Status> orderByComparator);

	/**
	* Removes all the statuses from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of statuses.
	*
	* @return the number of statuses
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}