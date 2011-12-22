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

import com.liferay.socialnetworking.model.WallEntry;

/**
 * The persistence interface for the wall entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryPersistenceImpl
 * @see WallEntryUtil
 * @generated
 */
public interface WallEntryPersistence extends BasePersistence<WallEntry> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WallEntryUtil} to access the wall entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the wall entry in the entity cache if it is enabled.
	*
	* @param wallEntry the wall entry
	*/
	public void cacheResult(
		com.liferay.socialnetworking.model.WallEntry wallEntry);

	/**
	* Caches the wall entries in the entity cache if it is enabled.
	*
	* @param wallEntries the wall entries
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.WallEntry> wallEntries);

	/**
	* Creates a new wall entry with the primary key. Does not add the wall entry to the database.
	*
	* @param wallEntryId the primary key for the new wall entry
	* @return the new wall entry
	*/
	public com.liferay.socialnetworking.model.WallEntry create(long wallEntryId);

	/**
	* Removes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry that was removed
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry remove(long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	public com.liferay.socialnetworking.model.WallEntry updateImpl(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the wall entry with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchWallEntryException} if it could not be found.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByPrimaryKey(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the wall entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry, or <code>null</code> if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry fetchByPrimaryKey(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the wall entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wall entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @return the range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wall entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wall entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the last wall entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry[] findByGroupId_PrevAndNext(
		long wallEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns all the wall entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wall entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @return the range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wall entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wall entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the last wall entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the wall entries before and after the current wall entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry[] findByUserId_PrevAndNext(
		long wallEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @return the range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry findByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialnetworking.model.WallEntry[] findByG_U_PrevAndNext(
		long wallEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException;

	/**
	* Returns all the wall entries.
	*
	* @return the wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the wall entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @return the range of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the wall entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of wall entries
	* @param end the upper bound of the range of wall entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wall entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wall entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wall entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the wall entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wall entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wall entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wall entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of wall entries.
	*
	* @return the number of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}