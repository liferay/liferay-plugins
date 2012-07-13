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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialnetworking.model.WallEntry;

import java.util.List;

/**
 * The persistence utility for the wall entry service. This utility wraps {@link WallEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntryPersistence
 * @see WallEntryPersistenceImpl
 * @generated
 */
public class WallEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	* @param wallEntry the wall entry
	*/
	public static void cacheResult(
		com.liferay.socialnetworking.model.WallEntry wallEntry) {
		getPersistence().cacheResult(wallEntry);
	}

	/**
	* Caches the wall entries in the entity cache if it is enabled.
	*
	* @param wallEntries the wall entries
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialnetworking.model.WallEntry> wallEntries) {
		getPersistence().cacheResult(wallEntries);
	}

	/**
	* Creates a new wall entry with the primary key. Does not add the wall entry to the database.
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
	* @param wallEntryId the primary key of the wall entry
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
	* Returns the wall entry with the primary key or throws a {@link com.liferay.socialnetworking.NoSuchWallEntryException} if it could not be found.
	*
	* @param wallEntryId the primary key of the wall entry
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
	* Returns the wall entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param wallEntryId the primary key of the wall entry
	* @return the wall entry, or <code>null</code> if a wall entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByPrimaryKey(
		long wallEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(wallEntryId);
	}

	/**
	* Returns all the wall entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByGroupId(groupId, start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByGroupId(
		long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByGroupId(groupId, start, end, orderByComparator);
	}

	/**
	* Returns the first wall entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns the first wall entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByGroupId_First(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_First(groupId, orderByComparator);
	}

	/**
	* Returns the last wall entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns the last wall entry in the ordered set where groupId = &#63;.
	*
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByGroupId_Last(
		long groupId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByGroupId_Last(groupId, orderByComparator);
	}

	/**
	* Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63;.
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param groupId the group ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns all the wall entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first wall entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns the first wall entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last wall entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns the last wall entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the wall entries before and after the current wall entry in the ordered set where userId = &#63;.
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns all the wall entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByG_U(groupId, userId, start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByG_U(groupId, userId, start, end, orderByComparator);
	}

	/**
	* Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns the first wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching wall entry, or <code>null</code> if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByG_U_First(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_U_First(groupId, userId, orderByComparator);
	}

	/**
	* Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns the last wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching wall entry, or <code>null</code> if a matching wall entry could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialnetworking.model.WallEntry fetchByG_U_Last(
		long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByG_U_Last(groupId, userId, orderByComparator);
	}

	/**
	* Returns the wall entries before and after the current wall entry in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param wallEntryId the primary key of the current wall entry
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
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
	* Returns all the wall entries.
	*
	* @return the wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.liferay.socialnetworking.model.WallEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the wall entries where groupId = &#63; from the database.
	*
	* @param groupId the group ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByGroupId(groupId);
	}

	/**
	* Removes all the wall entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the wall entries where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
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
	* Returns the number of wall entries where groupId = &#63;.
	*
	* @param groupId the group ID
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByGroupId(groupId);
	}

	/**
	* Returns the number of wall entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of wall entries where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching wall entries
	* @throws SystemException if a system exception occurred
	*/
	public static int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Returns the number of wall entries.
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
			_persistence = (WallEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialnetworking.service.ClpSerializer.getServletContextName(),
					WallEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(WallEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(WallEntryPersistence persistence) {
	}

	private static WallEntryPersistence _persistence;
}