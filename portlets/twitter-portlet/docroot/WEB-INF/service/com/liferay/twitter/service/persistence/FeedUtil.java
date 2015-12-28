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

package com.liferay.twitter.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.twitter.model.Feed;

import java.util.List;

/**
 * The persistence utility for the feed service. This utility wraps {@link com.liferay.twitter.service.persistence.impl.FeedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedPersistence
 * @see com.liferay.twitter.service.persistence.impl.FeedPersistenceImpl
 * @generated
 */
@ProviderType
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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Feed> findWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Feed> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Feed> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator<Feed> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static Feed update(Feed feed) {
		return getPersistence().update(feed);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static Feed update(Feed feed, ServiceContext serviceContext) {
		return getPersistence().update(feed, serviceContext);
	}

	/**
	* Returns the feed where userId = &#63; and twitterScreenName = &#63; or throws a {@link NoSuchFeedException} if it could not be found.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the matching feed
	* @throws NoSuchFeedException if a matching feed could not be found
	*/
	public static Feed findByU_TSN(long userId,
		java.lang.String twitterScreenName)
		throws com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByU_TSN(userId, twitterScreenName);
	}

	/**
	* Returns the feed where userId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	*/
	public static Feed fetchByU_TSN(long userId,
		java.lang.String twitterScreenName) {
		return getPersistence().fetchByU_TSN(userId, twitterScreenName);
	}

	/**
	* Returns the feed where userId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	*/
	public static Feed fetchByU_TSN(long userId,
		java.lang.String twitterScreenName, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByU_TSN(userId, twitterScreenName, retrieveFromCache);
	}

	/**
	* Removes the feed where userId = &#63; and twitterScreenName = &#63; from the database.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the feed that was removed
	*/
	public static Feed removeByU_TSN(long userId,
		java.lang.String twitterScreenName)
		throws com.liferay.twitter.NoSuchFeedException {
		return getPersistence().removeByU_TSN(userId, twitterScreenName);
	}

	/**
	* Returns the number of feeds where userId = &#63; and twitterScreenName = &#63;.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the number of matching feeds
	*/
	public static int countByU_TSN(long userId,
		java.lang.String twitterScreenName) {
		return getPersistence().countByU_TSN(userId, twitterScreenName);
	}

	/**
	* Caches the feed in the entity cache if it is enabled.
	*
	* @param feed the feed
	*/
	public static void cacheResult(Feed feed) {
		getPersistence().cacheResult(feed);
	}

	/**
	* Caches the feeds in the entity cache if it is enabled.
	*
	* @param feeds the feeds
	*/
	public static void cacheResult(List<Feed> feeds) {
		getPersistence().cacheResult(feeds);
	}

	/**
	* Creates a new feed with the primary key. Does not add the feed to the database.
	*
	* @param feedId the primary key for the new feed
	* @return the new feed
	*/
	public static Feed create(long feedId) {
		return getPersistence().create(feedId);
	}

	/**
	* Removes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedId the primary key of the feed
	* @return the feed that was removed
	* @throws NoSuchFeedException if a feed with the primary key could not be found
	*/
	public static Feed remove(long feedId)
		throws com.liferay.twitter.NoSuchFeedException {
		return getPersistence().remove(feedId);
	}

	public static Feed updateImpl(Feed feed) {
		return getPersistence().updateImpl(feed);
	}

	/**
	* Returns the feed with the primary key or throws a {@link NoSuchFeedException} if it could not be found.
	*
	* @param feedId the primary key of the feed
	* @return the feed
	* @throws NoSuchFeedException if a feed with the primary key could not be found
	*/
	public static Feed findByPrimaryKey(long feedId)
		throws com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByPrimaryKey(feedId);
	}

	/**
	* Returns the feed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedId the primary key of the feed
	* @return the feed, or <code>null</code> if a feed with the primary key could not be found
	*/
	public static Feed fetchByPrimaryKey(long feedId) {
		return getPersistence().fetchByPrimaryKey(feedId);
	}

	public static java.util.Map<java.io.Serializable, Feed> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the feeds.
	*
	* @return the feeds
	*/
	public static List<Feed> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feeds
	* @param end the upper bound of the range of feeds (not inclusive)
	* @return the range of feeds
	*/
	public static List<Feed> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feeds
	* @param end the upper bound of the range of feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of feeds
	*/
	public static List<Feed> findAll(int start, int end,
		OrderByComparator<Feed> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feeds
	* @param end the upper bound of the range of feeds (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of feeds
	*/
	public static List<Feed> findAll(int start, int end,
		OrderByComparator<Feed> orderByComparator, boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the feeds from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of feeds.
	*
	* @return the number of feeds
	*/
	public static int countAll() {
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

	private static FeedPersistence _persistence;
}