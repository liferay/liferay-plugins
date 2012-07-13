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

package com.liferay.so.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.so.model.FavoriteSite;

/**
 * The persistence interface for the favorite site service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSitePersistenceImpl
 * @see FavoriteSiteUtil
 * @generated
 */
public interface FavoriteSitePersistence extends BasePersistence<FavoriteSite> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FavoriteSiteUtil} to access the favorite site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the favorite site in the entity cache if it is enabled.
	*
	* @param favoriteSite the favorite site
	*/
	public void cacheResult(com.liferay.so.model.FavoriteSite favoriteSite);

	/**
	* Caches the favorite sites in the entity cache if it is enabled.
	*
	* @param favoriteSites the favorite sites
	*/
	public void cacheResult(
		java.util.List<com.liferay.so.model.FavoriteSite> favoriteSites);

	/**
	* Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	*
	* @param favoriteSiteId the primary key for the new favorite site
	* @return the new favorite site
	*/
	public com.liferay.so.model.FavoriteSite create(long favoriteSiteId);

	/**
	* Removes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site that was removed
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite remove(long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	public com.liferay.so.model.FavoriteSite updateImpl(
		com.liferay.so.model.FavoriteSite favoriteSite, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the favorite site with the primary key or throws a {@link com.liferay.so.NoSuchFavoriteSiteException} if it could not be found.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite findByPrimaryKey(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the favorite site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site, or <code>null</code> if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite fetchByPrimaryKey(
		long favoriteSiteId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the favorite sites where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the favorite sites where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the favorite sites where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the first favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site, or <code>null</code> if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the last favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site, or <code>null</code> if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the favorite sites before and after the current favorite site in the ordered set where userId = &#63;.
	*
	* @param favoriteSiteId the primary key of the current favorite site
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite[] findByUserId_PrevAndNext(
		long favoriteSiteId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns all the favorite sites where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findByG_U(
		long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the favorite sites where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findByG_U(
		long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the favorite sites where groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findByG_U(
		long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite findByG_U_First(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the first favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site, or <code>null</code> if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite fetchByG_U_First(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite findByG_U_Last(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the last favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site, or <code>null</code> if a matching favorite site could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite fetchByG_U_Last(long groupId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the favorite sites before and after the current favorite site in the ordered set where groupId = &#63; and userId = &#63;.
	*
	* @param favoriteSiteId the primary key of the current favorite site
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next favorite site
	* @throws com.liferay.so.NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.so.model.FavoriteSite[] findByG_U_PrevAndNext(
		long favoriteSiteId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns all the favorite sites.
	*
	* @return the favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the favorite sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the favorite sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.so.model.FavoriteSite> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the favorite sites where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the favorite sites where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the favorite sites from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of favorite sites where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of favorite sites where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public int countByG_U(long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of favorite sites.
	*
	* @return the number of favorite sites
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}