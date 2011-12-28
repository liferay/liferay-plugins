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

package com.liferay.twitter.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.twitter.model.Feed;

import java.util.List;

/**
 * The persistence utility for the feed service. This utility wraps {@link FeedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedPersistence
 * @see FeedPersistenceImpl
 * @generated
 */
public class FeedUtil {
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
	public static void clearCache(Feed feed) {
		getPersistence().clearCache(feed);
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
	public static List<Feed> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Feed> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Feed> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Feed update(Feed feed, boolean merge)
		throws SystemException {
		return getPersistence().update(feed, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Feed update(Feed feed, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(feed, merge, serviceContext);
	}

	/**
	* Caches the feed in the entity cache if it is enabled.
	*
	* @param feed the feed
	*/
	public static void cacheResult(com.liferay.twitter.model.Feed feed) {
		getPersistence().cacheResult(feed);
	}

	/**
	* Caches the feeds in the entity cache if it is enabled.
	*
	* @param feeds the feeds
	*/
	public static void cacheResult(
		java.util.List<com.liferay.twitter.model.Feed> feeds) {
		getPersistence().cacheResult(feeds);
	}

	/**
	* Creates a new feed with the primary key. Does not add the feed to the database.
	*
	* @param feedId the primary key for the new feed
	* @return the new feed
	*/
	public static com.liferay.twitter.model.Feed create(long feedId) {
		return getPersistence().create(feedId);
	}

	/**
	* Removes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedId the primary key of the feed
	* @return the feed that was removed
	* @throws com.liferay.twitter.NoSuchFeedException if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed remove(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().remove(feedId);
	}

	public static com.liferay.twitter.model.Feed updateImpl(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(feed, merge);
	}

	/**
	* Returns the feed with the primary key or throws a {@link com.liferay.twitter.NoSuchFeedException} if it could not be found.
	*
	* @param feedId the primary key of the feed
	* @return the feed
	* @throws com.liferay.twitter.NoSuchFeedException if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed findByPrimaryKey(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByPrimaryKey(feedId);
	}

	/**
	* Returns the feed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedId the primary key of the feed
	* @return the feed, or <code>null</code> if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed fetchByPrimaryKey(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(feedId);
	}

	/**
	* Returns the feed where companyId = &#63; and twitterUserId = &#63; or throws a {@link com.liferay.twitter.NoSuchFeedException} if it could not be found.
	*
	* @param companyId the company ID
	* @param twitterUserId the twitter user ID
	* @return the matching feed
	* @throws com.liferay.twitter.NoSuchFeedException if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed findByC_TWUI(long companyId,
		long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByC_TWUI(companyId, twitterUserId);
	}

	/**
	* Returns the feed where companyId = &#63; and twitterUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param twitterUserId the twitter user ID
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed fetchByC_TWUI(long companyId,
		long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_TWUI(companyId, twitterUserId);
	}

	/**
	* Returns the feed where companyId = &#63; and twitterUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param twitterUserId the twitter user ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed fetchByC_TWUI(long companyId,
		long twitterUserId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_TWUI(companyId, twitterUserId, retrieveFromCache);
	}

	/**
	* Returns the feed where companyId = &#63; and twitterScreenName = &#63; or throws a {@link com.liferay.twitter.NoSuchFeedException} if it could not be found.
	*
	* @param companyId the company ID
	* @param twitterScreenName the twitter screen name
	* @return the matching feed
	* @throws com.liferay.twitter.NoSuchFeedException if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed findByC_TSN(long companyId,
		java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByC_TSN(companyId, twitterScreenName);
	}

	/**
	* Returns the feed where companyId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param twitterScreenName the twitter screen name
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed fetchByC_TSN(long companyId,
		java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_TSN(companyId, twitterScreenName);
	}

	/**
	* Returns the feed where companyId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param twitterScreenName the twitter screen name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.twitter.model.Feed fetchByC_TSN(long companyId,
		java.lang.String twitterScreenName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_TSN(companyId, twitterScreenName, retrieveFromCache);
	}

	/**
	* Returns all the feeds.
	*
	* @return the feeds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.twitter.model.Feed> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of feeds
	* @param end the upper bound of the range of feeds (not inclusive)
	* @return the range of feeds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.twitter.model.Feed> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of feeds
	* @param end the upper bound of the range of feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of feeds
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.twitter.model.Feed> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the feed where companyId = &#63; and twitterUserId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param twitterUserId the twitter user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_TWUI(long companyId, long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		getPersistence().removeByC_TWUI(companyId, twitterUserId);
	}

	/**
	* Removes the feed where companyId = &#63; and twitterScreenName = &#63; from the database.
	*
	* @param companyId the company ID
	* @param twitterScreenName the twitter screen name
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_TSN(long companyId,
		java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		getPersistence().removeByC_TSN(companyId, twitterScreenName);
	}

	/**
	* Removes all the feeds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of feeds where companyId = &#63; and twitterUserId = &#63;.
	*
	* @param companyId the company ID
	* @param twitterUserId the twitter user ID
	* @return the number of matching feeds
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_TWUI(long companyId, long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_TWUI(companyId, twitterUserId);
	}

	/**
	* Returns the number of feeds where companyId = &#63; and twitterScreenName = &#63;.
	*
	* @param companyId the company ID
	* @param twitterScreenName the twitter screen name
	* @return the number of matching feeds
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_TSN(long companyId,
		java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_TSN(companyId, twitterScreenName);
	}

	/**
	* Returns the number of feeds.
	*
	* @return the number of feeds
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static FeedPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FeedPersistence)PortletBeanLocatorUtil.locate(com.liferay.twitter.service.ClpSerializer.getServletContextName(),
					FeedPersistence.class.getName());

			ReferenceRegistry.registerReference(FeedUtil.class, "_persistence");
		}

		return _persistence;
	}

	public void setPersistence(FeedPersistence persistence) {
		_persistence = persistence;

		ReferenceRegistry.registerReference(FeedUtil.class, "_persistence");
	}

	private static FeedPersistence _persistence;
}