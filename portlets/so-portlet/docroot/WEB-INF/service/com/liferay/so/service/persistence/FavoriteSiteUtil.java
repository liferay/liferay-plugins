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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.so.model.FavoriteSite;

import java.util.List;

/**
 * The persistence utility for the favorite site service. This utility wraps {@link com.liferay.so.service.persistence.impl.FavoriteSitePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSitePersistence
 * @see com.liferay.so.service.persistence.impl.FavoriteSitePersistenceImpl
 * @generated
 */
@ProviderType
public class FavoriteSiteUtil {
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
	public static void clearCache(FavoriteSite favoriteSite) {
		getPersistence().clearCache(favoriteSite);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FavoriteSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FavoriteSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FavoriteSite> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FavoriteSite> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static FavoriteSite update(FavoriteSite favoriteSite) {
		return getPersistence().update(favoriteSite);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static FavoriteSite update(FavoriteSite favoriteSite,
		ServiceContext serviceContext) {
		return getPersistence().update(favoriteSite, serviceContext);
	}

	/**
	* Returns all the favorite sites where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching favorite sites
	*/
	public static List<FavoriteSite> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

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
	public static List<FavoriteSite> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

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
	public static List<FavoriteSite> findByUserId(long userId, int start,
		int end, OrderByComparator<FavoriteSite> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site
	* @throws NoSuchFavoriteSiteException if a matching favorite site could not be found
	*/
	public static FavoriteSite findByUserId_First(long userId,
		OrderByComparator<FavoriteSite> orderByComparator)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public static FavoriteSite fetchByUserId_First(long userId,
		OrderByComparator<FavoriteSite> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site
	* @throws NoSuchFavoriteSiteException if a matching favorite site could not be found
	*/
	public static FavoriteSite findByUserId_Last(long userId,
		OrderByComparator<FavoriteSite> orderByComparator)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last favorite site in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public static FavoriteSite fetchByUserId_Last(long userId,
		OrderByComparator<FavoriteSite> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the favorite sites before and after the current favorite site in the ordered set where userId = &#63;.
	*
	* @param favoriteSiteId the primary key of the current favorite site
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next favorite site
	* @throws NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	*/
	public static FavoriteSite[] findByUserId_PrevAndNext(long favoriteSiteId,
		long userId, OrderByComparator<FavoriteSite> orderByComparator)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence()
				   .findByUserId_PrevAndNext(favoriteSiteId, userId,
			orderByComparator);
	}

	/**
	* Removes all the favorite sites where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of favorite sites where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching favorite sites
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the favorite site where groupId = &#63; and userId = &#63; or throws a {@link NoSuchFavoriteSiteException} if it could not be found.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching favorite site
	* @throws NoSuchFavoriteSiteException if a matching favorite site could not be found
	*/
	public static FavoriteSite findByG_U(long groupId, long userId)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence().findByG_U(groupId, userId);
	}

	/**
	* Returns the favorite site where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public static FavoriteSite fetchByG_U(long groupId, long userId) {
		return getPersistence().fetchByG_U(groupId, userId);
	}

	/**
	* Returns the favorite site where groupId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching favorite site, or <code>null</code> if a matching favorite site could not be found
	*/
	public static FavoriteSite fetchByG_U(long groupId, long userId,
		boolean retrieveFromCache) {
		return getPersistence().fetchByG_U(groupId, userId, retrieveFromCache);
	}

	/**
	* Removes the favorite site where groupId = &#63; and userId = &#63; from the database.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the favorite site that was removed
	*/
	public static FavoriteSite removeByG_U(long groupId, long userId)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence().removeByG_U(groupId, userId);
	}

	/**
	* Returns the number of favorite sites where groupId = &#63; and userId = &#63;.
	*
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching favorite sites
	*/
	public static int countByG_U(long groupId, long userId) {
		return getPersistence().countByG_U(groupId, userId);
	}

	/**
	* Caches the favorite site in the entity cache if it is enabled.
	*
	* @param favoriteSite the favorite site
	*/
	public static void cacheResult(FavoriteSite favoriteSite) {
		getPersistence().cacheResult(favoriteSite);
	}

	/**
	* Caches the favorite sites in the entity cache if it is enabled.
	*
	* @param favoriteSites the favorite sites
	*/
	public static void cacheResult(List<FavoriteSite> favoriteSites) {
		getPersistence().cacheResult(favoriteSites);
	}

	/**
	* Creates a new favorite site with the primary key. Does not add the favorite site to the database.
	*
	* @param favoriteSiteId the primary key for the new favorite site
	* @return the new favorite site
	*/
	public static FavoriteSite create(long favoriteSiteId) {
		return getPersistence().create(favoriteSiteId);
	}

	/**
	* Removes the favorite site with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site that was removed
	* @throws NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	*/
	public static FavoriteSite remove(long favoriteSiteId)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence().remove(favoriteSiteId);
	}

	public static FavoriteSite updateImpl(FavoriteSite favoriteSite) {
		return getPersistence().updateImpl(favoriteSite);
	}

	/**
	* Returns the favorite site with the primary key or throws a {@link NoSuchFavoriteSiteException} if it could not be found.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site
	* @throws NoSuchFavoriteSiteException if a favorite site with the primary key could not be found
	*/
	public static FavoriteSite findByPrimaryKey(long favoriteSiteId)
		throws com.liferay.so.NoSuchFavoriteSiteException {
		return getPersistence().findByPrimaryKey(favoriteSiteId);
	}

	/**
	* Returns the favorite site with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param favoriteSiteId the primary key of the favorite site
	* @return the favorite site, or <code>null</code> if a favorite site with the primary key could not be found
	*/
	public static FavoriteSite fetchByPrimaryKey(long favoriteSiteId) {
		return getPersistence().fetchByPrimaryKey(favoriteSiteId);
	}

	public static java.util.Map<java.io.Serializable, FavoriteSite> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the favorite sites.
	*
	* @return the favorite sites
	*/
	public static List<FavoriteSite> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<FavoriteSite> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<FavoriteSite> findAll(int start, int end,
		OrderByComparator<FavoriteSite> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the favorite sites from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of favorite sites.
	*
	* @return the number of favorite sites
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FavoriteSitePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FavoriteSitePersistence)PortletBeanLocatorUtil.locate(com.liferay.so.service.ClpSerializer.getServletContextName(),
					FavoriteSitePersistence.class.getName());

			ReferenceRegistry.registerReference(FavoriteSiteUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(FavoriteSitePersistence persistence) {
	}

	private static FavoriteSitePersistence _persistence;
}