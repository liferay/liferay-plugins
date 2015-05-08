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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.twitter.model.Feed;

/**
 * The persistence interface for the feed service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.twitter.service.persistence.impl.FeedPersistenceImpl
 * @see FeedUtil
 * @generated
 */
@ProviderType
public interface FeedPersistence extends BasePersistence<Feed> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FeedUtil} to access the feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the feed where userId = &#63; and twitterScreenName = &#63; or throws a {@link NoSuchFeedException} if it could not be found.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the matching feed
	* @throws NoSuchFeedException if a matching feed could not be found
	*/
	public Feed findByU_TSN(long userId, java.lang.String twitterScreenName)
		throws com.liferay.twitter.NoSuchFeedException;

	/**
	* Returns the feed where userId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	*/
	public Feed fetchByU_TSN(long userId, java.lang.String twitterScreenName);

	/**
	* Returns the feed where userId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	*/
	public Feed fetchByU_TSN(long userId, java.lang.String twitterScreenName,
		boolean retrieveFromCache);

	/**
	* Removes the feed where userId = &#63; and twitterScreenName = &#63; from the database.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the feed that was removed
	*/
	public Feed removeByU_TSN(long userId, java.lang.String twitterScreenName)
		throws com.liferay.twitter.NoSuchFeedException;

	/**
	* Returns the number of feeds where userId = &#63; and twitterScreenName = &#63;.
	*
	* @param userId the user ID
	* @param twitterScreenName the twitter screen name
	* @return the number of matching feeds
	*/
	public int countByU_TSN(long userId, java.lang.String twitterScreenName);

	/**
	* Caches the feed in the entity cache if it is enabled.
	*
	* @param feed the feed
	*/
	public void cacheResult(Feed feed);

	/**
	* Caches the feeds in the entity cache if it is enabled.
	*
	* @param feeds the feeds
	*/
	public void cacheResult(java.util.List<Feed> feeds);

	/**
	* Creates a new feed with the primary key. Does not add the feed to the database.
	*
	* @param feedId the primary key for the new feed
	* @return the new feed
	*/
	public Feed create(long feedId);

	/**
	* Removes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedId the primary key of the feed
	* @return the feed that was removed
	* @throws NoSuchFeedException if a feed with the primary key could not be found
	*/
	public Feed remove(long feedId)
		throws com.liferay.twitter.NoSuchFeedException;

	public Feed updateImpl(Feed feed);

	/**
	* Returns the feed with the primary key or throws a {@link NoSuchFeedException} if it could not be found.
	*
	* @param feedId the primary key of the feed
	* @return the feed
	* @throws NoSuchFeedException if a feed with the primary key could not be found
	*/
	public Feed findByPrimaryKey(long feedId)
		throws com.liferay.twitter.NoSuchFeedException;

	/**
	* Returns the feed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedId the primary key of the feed
	* @return the feed, or <code>null</code> if a feed with the primary key could not be found
	*/
	public Feed fetchByPrimaryKey(long feedId);

	@Override
	public java.util.Map<java.io.Serializable, Feed> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the feeds.
	*
	* @return the feeds
	*/
	public java.util.List<Feed> findAll();

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
	public java.util.List<Feed> findAll(int start, int end);

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
	public java.util.List<Feed> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Feed> orderByComparator);

	/**
	* Removes all the feeds from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of feeds.
	*
	* @return the number of feeds
	*/
	public int countAll();
}