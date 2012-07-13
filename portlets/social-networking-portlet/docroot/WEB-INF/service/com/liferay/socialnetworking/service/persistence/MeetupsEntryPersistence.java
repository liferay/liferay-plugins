/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialnetworking.model.MeetupsEntry;

/**
 * The persistence interface for the meetups entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntryPersistenceImpl
 * @see MeetupsEntryUtil
 * @generated
 */
public interface MeetupsEntryPersistence extends BasePersistence<MeetupsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MeetupsEntryUtil} to access the meetups entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the meetups entry in the entity cache if it is enabled.
	*
	* @param meetupsEntry the meetups entry
	*/
	public void cacheResult(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry);

	/**
	* Caches the meetups entries in the entity cache if it is enabled.
	*
	* @param meetupsEntries the meetups entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> meetupsEntries);

	/**
	* Creates a new meetups entry with the primary key. Does not add the meetups entry to the database.
	*
	* @param meetupsEntryId the primary key for the new meetups entry
	* @return the new meetups entry
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry create(
		long meetupsEntryId);

	/**
	* Removes the meetups entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param meetupsEntryId the primary key of the meetups entry
	* @return the meetups entry that was removed
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry remove(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	public com.liferay.socialnetworking.model.MeetupsEntry updateImpl(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups entry with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchMeetupsEntryException} if it could not be found.
	*
	* @param meetupsEntryId the primary key of the meetups entry
	* @return the meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry findByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns the meetups entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param meetupsEntryId the primary key of the meetups entry
	* @return the meetups entry, or <code>null</code> if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry fetchByPrimaryKey(
		long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the meetups entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @return the range of matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meetups entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns the first meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry fetchByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns the last meetups entry in the ordered set where companyId = &#63;.
	*
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry fetchByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups entries before and after the current meetups entry in the ordered set where companyId = &#63;.
	*
	* @param meetupsEntryId the primary key of the current meetups entry
	* @param companyId the company ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry[] findByCompanyId_PrevAndNext(
		long meetupsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns all the meetups entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @return the range of matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meetups entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns the first meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns the last meetups entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching meetups entry, or <code>null</code> if a matching meetups entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the meetups entries before and after the current meetups entry in the ordered set where userId = &#63;.
	*
	* @param meetupsEntryId the primary key of the current meetups entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next meetups entry
	* @throws com.liferay.socialnetworking.NoSuchMeetupsEntryException if a meetups entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.MeetupsEntry[] findByUserId_PrevAndNext(
		long meetupsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchMeetupsEntryException;

	/**
	* Returns all the meetups entries.
	*
	* @return the meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the meetups entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @return the range of meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the meetups entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of meetups entries
	* @param end the upper bound of the range of meetups entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.MeetupsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meetups entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meetups entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the meetups entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups entries where companyId = &#63;.
	*
	* @param companyId the company ID
	* @return the number of matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of meetups entries.
	*
	* @return the number of meetups entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}