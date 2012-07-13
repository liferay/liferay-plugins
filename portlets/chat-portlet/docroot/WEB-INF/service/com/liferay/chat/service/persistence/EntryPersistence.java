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

package com.liferay.chat.service.persistence;

import com.liferay.chat.model.Entry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EntryPersistenceImpl
 * @see EntryUtil
 * @generated
 */
public interface EntryPersistence extends BasePersistence<Entry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntryUtil} to access the entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public void cacheResult(com.liferay.chat.model.Entry entry);

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.chat.model.Entry> entries);

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public com.liferay.chat.model.Entry create(long entryId);

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry remove(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.chat.model.Entry updateImpl(
		com.liferay.chat.model.Entry entry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entry with the primary key or throws a {@link com.liferay.chat.NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByPrimaryKey(long entryId)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByPrimaryKey(long entryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where createDate = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByCreateDate(
		long createDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByCreateDate_First(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByCreateDate_First(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByCreateDate_Last(long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63;.
	*
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByCreateDate_Last(
		long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByCreateDate_PrevAndNext(
		long entryId, long createDate,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByFromUserId(
		long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByFromUserId_First(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByFromUserId_First(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByFromUserId_Last(long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByFromUserId_Last(
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where fromUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByFromUserId_PrevAndNext(
		long entryId, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByToUserId(
		long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByToUserId_First(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByToUserId_First(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByToUserId_Last(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByToUserId_Last(long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByToUserId_PrevAndNext(
		long entryId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_F(
		long createDate, long fromUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByC_F_First(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByC_F_First(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByC_F_Last(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByC_F_Last(long createDate,
		long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByC_F_PrevAndNext(long entryId,
		long createDate, long fromUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_T(
		long createDate, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByC_T_First(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByC_T_First(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByC_T_Last(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByC_T_Last(long createDate,
		long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63; and toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByC_T_PrevAndNext(long entryId,
		long createDate, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByC_F_T(
		long createDate, long fromUserId, long toUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByC_F_T_First(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByC_F_T_First(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByC_F_T_Last(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByC_F_T_Last(long createDate,
		long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByC_F_T_PrevAndNext(
		long entryId, long createDate, long fromUserId, long toUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @return the matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findByF_T_C(
		long fromUserId, long toUserId, java.lang.String content, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByF_T_C_First(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByF_T_C_First(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws com.liferay.chat.NoSuchEntryException if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry findByF_T_C_Last(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry fetchByF_T_C_Last(long fromUserId,
		long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the entries before and after the current entry in the ordered set where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws com.liferay.chat.NoSuchEntryException if a entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.chat.model.Entry[] findByF_T_C_PrevAndNext(
		long entryId, long fromUserId, long toUserId, java.lang.String content,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.chat.NoSuchEntryException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the entries.
	*
	* @return the entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.chat.model.Entry> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where createDate = &#63; from the database.
	*
	* @param createDate the create date
	* @throws SystemException if a system exception occurred
	*/
	public void removeByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where fromUserId = &#63; from the database.
	*
	* @param fromUserId the from user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where toUserId = &#63; from the database.
	*
	* @param toUserId the to user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where createDate = &#63; and fromUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where createDate = &#63; and toUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63; from the database.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_F_T(long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries where fromUserId = &#63; and toUserId = &#63; and content = &#63; from the database.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @throws SystemException if a system exception occurred
	*/
	public void removeByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where createDate = &#63;.
	*
	* @param createDate the create date
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByCreateDate(long createDate)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where fromUserId = &#63;.
	*
	* @param fromUserId the from user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByFromUserId(long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where toUserId = &#63;.
	*
	* @param toUserId the to user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByToUserId(long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where createDate = &#63; and fromUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_F(long createDate, long fromUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where createDate = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param toUserId the to user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_T(long createDate, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where createDate = &#63; and fromUserId = &#63; and toUserId = &#63;.
	*
	* @param createDate the create date
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_F_T(long createDate, long fromUserId, long toUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries where fromUserId = &#63; and toUserId = &#63; and content = &#63;.
	*
	* @param fromUserId the from user ID
	* @param toUserId the to user ID
	* @param content the content
	* @return the number of matching entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByF_T_C(long fromUserId, long toUserId,
		java.lang.String content)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}