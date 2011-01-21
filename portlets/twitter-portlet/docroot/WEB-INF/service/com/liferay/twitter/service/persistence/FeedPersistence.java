/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.exception.SystemException;
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
 * @see FeedPersistenceImpl
 * @see FeedUtil
 * @generated
 */
public interface FeedPersistence extends BasePersistence<Feed> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FeedUtil} to access the feed persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the feed in the entity cache if it is enabled.
	*
	* @param feed the feed to cache
	*/
	public void cacheResult(com.liferay.twitter.model.Feed feed);

	/**
	* Caches the feeds in the entity cache if it is enabled.
	*
	* @param feeds the feeds to cache
	*/
	public void cacheResult(
		java.util.List<com.liferay.twitter.model.Feed> feeds);

	/**
	* Creates a new feed with the primary key. Does not add the feed to the database.
	*
	* @param feedId the primary key for the new feed
	* @return the new feed
	*/
	public com.liferay.twitter.model.Feed create(long feedId);

	/**
	* Removes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedId the primary key of the feed to remove
	* @return the feed that was removed
	* @throws com.liferay.twitter.NoSuchFeedException if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed remove(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException;

	public com.liferay.twitter.model.Feed updateImpl(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the feed with the primary key or throws a {@link com.liferay.twitter.NoSuchFeedException} if it could not be found.
	*
	* @param feedId the primary key of the feed to find
	* @return the feed
	* @throws com.liferay.twitter.NoSuchFeedException if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed findByPrimaryKey(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException;

	/**
	* Finds the feed with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedId the primary key of the feed to find
	* @return the feed, or <code>null</code> if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed fetchByPrimaryKey(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the feed where companyId = &#63; and twitterUserId = &#63; or throws a {@link com.liferay.twitter.NoSuchFeedException} if it could not be found.
	*
	* @param companyId the company ID to search with
	* @param twitterUserId the twitter user ID to search with
	* @return the matching feed
	* @throws com.liferay.twitter.NoSuchFeedException if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed findByC_TWUI(long companyId,
		long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException;

	/**
	* Finds the feed where companyId = &#63; and twitterUserId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID to search with
	* @param twitterUserId the twitter user ID to search with
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed fetchByC_TWUI(long companyId,
		long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the feed where companyId = &#63; and twitterUserId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID to search with
	* @param twitterUserId the twitter user ID to search with
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed fetchByC_TWUI(long companyId,
		long twitterUserId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the feed where companyId = &#63; and twitterScreenName = &#63; or throws a {@link com.liferay.twitter.NoSuchFeedException} if it could not be found.
	*
	* @param companyId the company ID to search with
	* @param twitterScreenName the twitter screen name to search with
	* @return the matching feed
	* @throws com.liferay.twitter.NoSuchFeedException if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed findByC_TSN(long companyId,
		java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException;

	/**
	* Finds the feed where companyId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID to search with
	* @param twitterScreenName the twitter screen name to search with
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed fetchByC_TSN(long companyId,
		java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds the feed where companyId = &#63; and twitterScreenName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID to search with
	* @param twitterScreenName the twitter screen name to search with
	* @return the matching feed, or <code>null</code> if a matching feed could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed fetchByC_TSN(long companyId,
		java.lang.String twitterScreenName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds all the feeds.
	*
	* @return the feeds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.twitter.model.Feed> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds a range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of feeds to return
	* @param end the upper bound of the range of feeds to return (not inclusive)
	* @return the range of feeds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.twitter.model.Feed> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Finds an ordered range of all the feeds.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of feeds to return
	* @param end the upper bound of the range of feeds to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of feeds
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.twitter.model.Feed> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the feed where companyId = &#63; and twitterUserId = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param twitterUserId the twitter user ID to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_TWUI(long companyId, long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException;

	/**
	* Removes the feed where companyId = &#63; and twitterScreenName = &#63; from the database.
	*
	* @param companyId the company ID to search with
	* @param twitterScreenName the twitter screen name to search with
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_TSN(long companyId, java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.twitter.NoSuchFeedException;

	/**
	* Removes all the feeds from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the feeds where companyId = &#63; and twitterUserId = &#63;.
	*
	* @param companyId the company ID to search with
	* @param twitterUserId the twitter user ID to search with
	* @return the number of matching feeds
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_TWUI(long companyId, long twitterUserId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the feeds where companyId = &#63; and twitterScreenName = &#63;.
	*
	* @param companyId the company ID to search with
	* @param twitterScreenName the twitter screen name to search with
	* @return the number of matching feeds
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_TSN(long companyId, java.lang.String twitterScreenName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Counts all the feeds.
	*
	* @return the number of feeds
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public Feed remove(Feed feed) throws SystemException;
}