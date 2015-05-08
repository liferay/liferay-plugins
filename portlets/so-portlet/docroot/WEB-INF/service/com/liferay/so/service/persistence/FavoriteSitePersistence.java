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

package com.liferay.so.service.persistence;

import aQute.bnd.annotation.ProviderType;

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
 * @see com.liferay.so.service.persistence.impl.FavoriteSitePersistenceImpl
 * @see FavoriteSiteUtil
 * @generated
 */
@ProviderType
public interface FavoriteSitePersistence extends BasePersistence<FavoriteSite> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FavoriteSiteUtil} to access the favorite site persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the favorite sites where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching favorite sites
	*/
	public java.util.List<FavoriteSite> findByUserId(long userId);

	/**
	* Returns a range of all the favorite sites where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of matching favorite sites
	*/
	public java.util.List<FavoriteSite> findByUserId(long userId, int start,
		int end);

	/**
	* Returns an ordered range of all the favorite sites where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching favorite sites
	*/
	public java.util.List<FavoriteSite> findByUserId(long userId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator);

	/**
	* Returns the first favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site
	* @throws NoSuchFavoriteSiteException if a matching favorite site could not be found
	*/
	public FavoriteSite findByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the first favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public FavoriteSite fetchByUserId_First(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator);

	/**
	* Returns the last favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site
	* @throws NoSuchFavoriteSiteException if a matching favorite site could not be found
	*/
	public FavoriteSite findByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the last favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public FavoriteSite fetchByUserId_Last(long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator);

	/**
	* Returns the favorite sites before and after the current favorite site in the ordered set where userId = &#63;.
	*
	* @param favoriteSiteId the primary key of the current favorite site
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next favorite site
	* @throws NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	*/
	public FavoriteSite[] findByUserId_PrevAndNext(long favoriteSiteId,
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Removes all the favorite sites where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public void removeByUserId(long userId);

	/**
	* Returns the number of favorite sites where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching favorite sites
	*/
	public int countByUserId(long userId);

	/**
	* Returns the favorite site where groupId = &#63; and userId = &#63; or throws a {@link NoSuchFavoriteSiteException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching favorite site
	* @throws NoSuchFavoriteSiteException if a matching favorite site could not be found
	*/
	public FavoriteSite findByG_U(long groupId, long userId)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the favorite site where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public FavoriteSite fetchByG_U(long groupId, long userId);

	/**
	* Returns the favorite site where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public FavoriteSite fetchByG_U(long groupId, long userId,
		boolean retrieveFromCache);

	/**
	* Removes the favorite site where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the favorite site that was removed
	*/
	public FavoriteSite removeByG_U(long groupId, long userId)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the number of favorite sites where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching favorite sites
	*/
	public int countByG_U(long groupId, long userId);

	/**
	* Caches the favorite site in the entity cache if it is enabled.
	*
	* @param favoriteSite the favorite site
	*/
	public void cacheResult(FavoriteSite favoriteSite);

	/**
	* Caches the favorite sites in the entity cache if it is enabled.
	*
	* @param favoriteSites the favorite sites
	*/
	public void cacheResult(java.util.List<FavoriteSite> favoriteSites);

	/**
	* Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	*
	* @param favoriteSiteId the primary key for the new favorite site
	* @return the new favorite site
	*/
	public FavoriteSite create(long favoriteSiteId);

	/**
	* Removes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site that was removed
	* @throws NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	*/
	public FavoriteSite remove(long favoriteSiteId)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	public FavoriteSite updateImpl(FavoriteSite favoriteSite);

	/**
	* Returns the favorite site with the primary key or throws a {@link NoSuchFavoriteSiteException} if it could not be found.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site
	* @throws NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	*/
	public FavoriteSite findByPrimaryKey(long favoriteSiteId)
		throws com.liferay.so.NoSuchFavoriteSiteException;

	/**
	* Returns the favorite site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site, or <code>null</code> if a favorite site with the primary key could not be found
	*/
	public FavoriteSite fetchByPrimaryKey(long favoriteSiteId);

	@Override
	public java.util.Map<java.io.Serializable, FavoriteSite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the favorite sites.
	*
	* @return the favorite sites
	*/
	public java.util.List<FavoriteSite> findAll();

	/**
	* Returns a range of all the favorite sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @return the range of favorite sites
	*/
	public java.util.List<FavoriteSite> findAll(int start, int end);

	/**
	* Returns an ordered range of all the favorite sites.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FavoriteSiteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of favorite sites
	* @param end the upper bound of the range of favorite sites (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of favorite sites
	*/
	public java.util.List<FavoriteSite> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FavoriteSite> orderByComparator);

	/**
	* Removes all the favorite sites from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of favorite sites.
	*
	* @return the number of favorite sites
	*/
	public int countAll();
}