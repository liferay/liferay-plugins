/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialnetworking.model.WallEntry;

import java.util.List;

/**
 * The persistence utility for the wall entry service.
 *
 * <p>
 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regnerate this class.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryPersistence
 * @see WallEntryPersistenceImpl
 * @generated
 */
public class WallEntryUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(WallEntry wallEntry) {
		getPersistence().clearCache(wallEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WallEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WallEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WallEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static WallEntry remove(WallEntry wallEntry)
		throws SystemException {
		return getPersistence().remove(wallEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static WallEntry update(WallEntry wallEntry, boolean merge)
		throws SystemException {
		return getPersistence().update(wallEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static WallEntry update(WallEntry wallEntry, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(wallEntry, merge, serviceContext);
	}

	/**
	* Caches the wall entry in the entity cache if it is enabled.
	*
	* @param wallEntry the wall entry to cache
	*/
	public static void cacheResult(
		com.liferay.socialnetworking.model.WallEntry wallEntry) {
		getPersistence().cacheResult(wallEntry);
	}

	/**
	* Caches the wall entries in the entity cache if it is enabled.
	*
	* @param wallEntries the wall entries to cache
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.WallEntry> wallEntries) {
		getPersistence().cacheResult(wallEntries);
	}

	/**
	* Creates a new wall entry with the primary key.
	*
	* @param wallEntryId the primary key for the new wall entry
	* @return the new wall entry
	*/
	public static com.liferay.socialnetworking.model.WallEntry create(
		long wallEntryId) {
		return getPersistence().create(wallEntryId);
	}

	/**
	* Removes the wall entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param wallEntryId the primary key of the wall entry to remove
	* @return the wall entry that was removed
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry remove(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence().remove(wallEntryId);
	}

	public static com.liferay.socialnetworking.model.WallEntry updateImpl(
		com.liferay.socialnetworking.model.WallEntry wallEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(wallEntry, merge);
	}

	/**
	* Finds the wall entry with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchWallEntryException} if it could not be found.
	*
	* @param wallEntryId the primary key of the wall entry to find
	* @return the wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByPrimaryKey(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence().findByPrimaryKey(wallEntryId);
	}

	/**
	* Finds the wall entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wallEntryId the primary key of the wall entry to find
	* @return the wall entry, or <code>null</code> if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByPrimaryKey(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wallEntryId);
	}

	/**
	* Finds all the wall entries where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

	/**
	* Finds a range of all the wall entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @return the range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

	/**
	* Finds an ordered range of all the wall entries where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Finds the first wall entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence().findByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Finds the last wall entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence().findByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Finds the wall entries before and after the current wall entry in the ordered set where groupId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param groupId the group id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry[] findByGroupId_PrevAndNext(
		long wallEntryId, long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence()
				   .findByGroupId_PrevAndNext(wallEntryId, groupId,
			orderByComparator);
	}

	/**
	* Finds all the wall entries where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Finds a range of all the wall entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @return the range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Finds an ordered range of all the wall entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Finds the first wall entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Finds the last wall entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Finds the wall entries before and after the current wall entry in the ordered set where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry[] findByUserId_PrevAndNext(
		long wallEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(wallEntryId, userId,
			orderByComparator);
	}

	/**
	* Finds all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Finds a range of all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @return the range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

	/**
	* Finds an ordered range of all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Finds the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the first matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence()
				   .findByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Finds the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the last matching wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry findByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence()
				   .findByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Finds the wall entries before and after the current wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @param orderByComparator the comparator to order the set by
	* @return the previous, current, and next wall entry
	* @throws com.liferay.socialnetworking.NoSuchWallEntryException if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry[] findByG_U_PrevAndNext(
		long wallEntryId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialnetworking.NoSuchWallEntryException {
		return getPersistence()
				   .findByG_U_PrevAndNext(wallEntryId, groupId, userId,
			orderByComparator);
	}

	/**
	* Finds all the wall entries.
	*
	* @return the wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Finds a range of all the wall entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @return the range of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Finds an ordered range of all the wall entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of wall entries to return
	* @param end the upper bound of the range of wall entries to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the wall entries where groupId = &#63; from the database.
	*
	* @param groupId the group id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the wall entries where userId = &#63; from the database.
	*
	* @param userId the user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the wall entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Removes all the wall entries from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Counts all the wall entries where groupId = &#63;.
	*
	* @param groupId the group id to search with
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Counts all the wall entries where userId = &#63;.
	*
	* @param userId the user id to search with
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Counts all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group id to search with
	* @param userId the user id to search with
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Counts all the wall entries.
	*
	* @return the number of wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static WallEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (WallEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialnetworking.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					WallEntryPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(WallEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static WallEntryPersistence _persistence;
}