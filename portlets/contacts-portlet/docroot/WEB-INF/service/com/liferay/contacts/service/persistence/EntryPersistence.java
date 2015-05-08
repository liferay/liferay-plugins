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

package com.liferay.contacts.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.contacts.model.Entry;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.contacts.service.persistence.impl.EntryPersistenceImpl
 * @see EntryUtil
 * @generated
 */
@ProviderType
public interface EntryPersistence extends BasePersistence<Entry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EntryUtil} to access the entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching entries
	*/
	public java.util.List<Entry> findByUserId(long userId);

	/**
	* Returns a range of all the entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of matching entries
	*/
	public java.util.List<Entry> findByUserId(long userId, int start, int end);

	/**
	* Returns an ordered range of all the entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching entries
	*/
	public java.util.List<Entry> findByUserId(long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator);

	/**
	* Returns the first entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public Entry findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator)
		throws com.liferay.contacts.NoSuchEntryException;

	/**
	* Returns the first entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public Entry fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator);

	/**
	* Returns the last entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public Entry findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator)
		throws com.liferay.contacts.NoSuchEntryException;

	/**
	* Returns the last entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public Entry fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator);

	/**
	* Returns the entries before and after the current entry in the ordered set where userId = &#63;.
	*
	* @param entryId the primary key of the current entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public Entry[] findByUserId_PrevAndNext(long entryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator)
		throws com.liferay.contacts.NoSuchEntryException;

	/**
	* Removes all the entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching entries
	*/
	public int countByUserId(long userId);

	/**
	* Returns the entry where userId = &#63; and emailAddress = &#63; or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param userId the user ID
	* @param emailAddress the email address
	* @return the matching entry
	* @throws NoSuchEntryException if a matching entry could not be found
	*/
	public Entry findByU_EA(long userId, java.lang.String emailAddress)
		throws com.liferay.contacts.NoSuchEntryException;

	/**
	* Returns the entry where userId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param emailAddress the email address
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public Entry fetchByU_EA(long userId, java.lang.String emailAddress);

	/**
	* Returns the entry where userId = &#63; and emailAddress = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param emailAddress the email address
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching entry, or <code>null</code> if a matching entry could not be found
	*/
	public Entry fetchByU_EA(long userId, java.lang.String emailAddress,
		boolean retrieveFromCache);

	/**
	* Removes the entry where userId = &#63; and emailAddress = &#63; from the database.
	*
	* @param userId the user ID
	* @param emailAddress the email address
	* @return the entry that was removed
	*/
	public Entry removeByU_EA(long userId, java.lang.String emailAddress)
		throws com.liferay.contacts.NoSuchEntryException;

	/**
	* Returns the number of entries where userId = &#63; and emailAddress = &#63;.
	*
	* @param userId the user ID
	* @param emailAddress the email address
	* @return the number of matching entries
	*/
	public int countByU_EA(long userId, java.lang.String emailAddress);

	/**
	* Caches the entry in the entity cache if it is enabled.
	*
	* @param entry the entry
	*/
	public void cacheResult(Entry entry);

	/**
	* Caches the entries in the entity cache if it is enabled.
	*
	* @param entries the entries
	*/
	public void cacheResult(java.util.List<Entry> entries);

	/**
	* Creates a new entry with the primary key. Does not add the entry to the database.
	*
	* @param entryId the primary key for the new entry
	* @return the new entry
	*/
	public Entry create(long entryId);

	/**
	* Removes the entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param entryId the primary key of the entry
	* @return the entry that was removed
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public Entry remove(long entryId)
		throws com.liferay.contacts.NoSuchEntryException;

	public Entry updateImpl(Entry entry);

	/**
	* Returns the entry with the primary key or throws a {@link NoSuchEntryException} if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry
	* @throws NoSuchEntryException if a entry with the primary key could not be found
	*/
	public Entry findByPrimaryKey(long entryId)
		throws com.liferay.contacts.NoSuchEntryException;

	/**
	* Returns the entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param entryId the primary key of the entry
	* @return the entry, or <code>null</code> if a entry with the primary key could not be found
	*/
	public Entry fetchByPrimaryKey(long entryId);

	@Override
	public java.util.Map<java.io.Serializable, Entry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the entries.
	*
	* @return the entries
	*/
	public java.util.List<Entry> findAll();

	/**
	* Returns a range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @return the range of entries
	*/
	public java.util.List<Entry> findAll(int start, int end);

	/**
	* Returns an ordered range of all the entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of entries
	* @param end the upper bound of the range of entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of entries
	*/
	public java.util.List<Entry> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Entry> orderByComparator);

	/**
	* Removes all the entries from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of entries.
	*
	* @return the number of entries
	*/
	public int countAll();
}