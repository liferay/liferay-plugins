/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.microblogs.service.persistence;

import com.liferay.microblogs.model.MicroblogsEntry;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the microblogs entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MicroblogsEntryPersistenceImpl
 * @see MicroblogsEntryUtil
 * @generated
 */
public interface MicroblogsEntryPersistence extends BasePersistence<MicroblogsEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link MicroblogsEntryUtil} to access the microblogs entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the microblogs entry in the entity cache if it is enabled.
	*
	* @param microblogsEntry the microblogs entry to cache
	*/
	public void cacheResult(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry);

	/**
	* Caches the microblogs entries in the entity cache if it is enabled.
	*
	* @param microblogsEntries the microblogs entries to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.microblogs.model.MicroblogsEntry> microblogsEntries);

	/**
	* Creates a new microblogs entry with the primary key. Does not add the microblogs entry to the database.
	*
	* @param microblogsEntryId the primary key for the new microblogs entry
	* @return the new microblogs entry
	*/
	public com.liferay.microblogs.model.MicroblogsEntry create(
		long microblogsEntryId);

	/**
	* Removes the microblogs entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param microblogsEntryId the primary key of the microblogs entry to remove
	* @return the microblogs entry that was removed
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry remove(
		long microblogsEntryId)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.microblogs.model.MicroblogsEntry updateImpl(
		com.liferay.microblogs.model.MicroblogsEntry microblogsEntry,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entry with the primary key or throws a {@link com.liferay.microblogs.NoSuchEntryException} if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry to find
	* @return the microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByPrimaryKey(
		long microblogsEntryId)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param microblogsEntryId the primary key of the microblogs entry to find
	* @return the microblogs entry, or <code>null</code> if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry fetchByPrimaryKey(
		long microblogsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID to search with
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first microblogs entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last microblogs entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID to search with
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the microblogs entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByUserId_PrevAndNext(
		long microblogsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID to search with
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first microblogs entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last microblogs entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param companyId the company ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID to search with
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the microblogs entries where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters the microblogs entries before and after the current microblogs entry in the ordered set where companyId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param companyId the company ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByCompanyId_PrevAndNext(
		long microblogsEntryId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RU(
		int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RU(
		int type, long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RU(
		int type, long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_RU_First(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_RU_Last(
		int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByT_RU_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RU(
		int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RU(
		int type, long receiverUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RU(
		int type, long receiverUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByT_RU_PrevAndNext(
		long microblogsEntryId, int type, long receiverUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RE(
		int type, long receiverEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RE(
		int type, long receiverEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByT_RE(
		int type, long receiverEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_RE_First(
		int type, long receiverEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByT_RE_Last(
		int type, long receiverEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByT_RE_PrevAndNext(
		long microblogsEntryId, int type, long receiverEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RE(
		int type, long receiverEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RE(
		int type, long receiverEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByT_RE(
		int type, long receiverEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters the microblogs entries before and after the current microblogs entry in the ordered set where type = &#63; and receiverEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByT_RE_PrevAndNext(
		long microblogsEntryId, int type, long receiverEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @return the matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the first microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByU_T_First(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the last microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a matching microblogs entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry findByU_T_Last(
		long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] findByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @return the matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds a range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and finds an ordered range of all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> filterFindByU_T(
		long userId, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters the microblogs entries before and after the current microblogs entry in the ordered set where userId = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param microblogsEntryId the primary key of the current microblogs entry
	* @param userId the user ID to search with
	* @param type the type to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next microblogs entry
	* @throws com.liferay.microblogs.NoSuchEntryException if a microblogs entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.microblogs.model.MicroblogsEntry[] filterFindByU_T_PrevAndNext(
		long microblogsEntryId, long userId, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.microblogs.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the microblogs entries.
	*
	* @return the microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @return the range of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the microblogs entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of microblogs entries to return
	* @param end the upper bound of the range of microblogs entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.microblogs.model.MicroblogsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the microblogs entries where userId = &#63; from the database.
	*
	* @param userId the user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the microblogs entries where companyId = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the microblogs entries where type = &#63; and receiverUserId = &#63; from the database.
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByT_RU(int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the microblogs entries where type = &#63; and receiverEntryId = &#63; from the database.
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByT_RE(int type, long receiverEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the microblogs entries where userId = &#63; and type = &#63; from the database.
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the microblogs entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID to search with
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the microblogs entries where userId = &#63;.
	*
	* @param userId the user ID to search with
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID to search with
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the microblogs entries where companyId = &#63;.
	*
	* @param companyId the company ID to search with
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByT_RU(int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the microblogs entries where type = &#63; and receiverUserId = &#63;.
	*
	* @param type the type to search with
	* @param receiverUserId the receiver user ID to search with
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByT_RU(int type, long receiverUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByT_RE(int type, long receiverEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the microblogs entries where type = &#63; and receiverEntryId = &#63;.
	*
	* @param type the type to search with
	* @param receiverEntryId the receiver entry ID to search with
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByT_RE(int type, long receiverEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @return the number of matching microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Filters by the user's permissions and counts all the microblogs entries where userId = &#63; and type = &#63;.
	*
	* @param userId the user ID to search with
	* @param type the type to search with
	* @return the number of matching microblogs entries that the user has permission to view
	* @throws SystemException if a system exception occurred
	*/
	public int filterCountByU_T(long userId, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the microblogs entries.
	*
	* @return the number of microblogs entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public MicroblogsEntry remove(MicroblogsEntry microblogsEntry)
		throws SystemException;
}