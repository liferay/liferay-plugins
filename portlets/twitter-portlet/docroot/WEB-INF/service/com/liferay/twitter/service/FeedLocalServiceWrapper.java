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

package com.liferay.twitter.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FeedLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedLocalService
 * @generated
 */
public class FeedLocalServiceWrapper implements FeedLocalService,
	ServiceWrapper<FeedLocalService> {
	public FeedLocalServiceWrapper(FeedLocalService feedLocalService) {
		_feedLocalService = feedLocalService;
	}

	/**
	* Adds the feed to the database. Also notifies the appropriate model listeners.
	*
	* @param feed the feed
	* @return the feed that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed addFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.addFeed(feed);
	}

	/**
	* Creates a new feed with the primary key. Does not add the feed to the database.
	*
	* @param feedId the primary key for the new feed
	* @return the new feed
	*/
	public com.liferay.twitter.model.Feed createFeed(long feedId) {
		return _feedLocalService.createFeed(feedId);
	}

	/**
	* Deletes the feed with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedId the primary key of the feed
	* @throws PortalException if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public void deleteFeed(long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.deleteFeed(feedId);
	}

	/**
	* Deletes the feed from the database. Also notifies the appropriate model listeners.
	*
	* @param feed the feed
	* @throws SystemException if a system exception occurred
	*/
	public void deleteFeed(com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.deleteFeed(feed);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.twitter.model.Feed fetchFeed(long feedId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.fetchFeed(feedId);
	}

	/**
	* Returns the feed with the primary key.
	*
	* @param feedId the primary key of the feed
	* @return the feed
	* @throws PortalException if a feed with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed getFeed(long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getFeed(feedId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getPersistedModel(primaryKeyObj);
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
	public java.util.List<com.liferay.twitter.model.Feed> getFeeds(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getFeeds(start, end);
	}

	/**
	* Returns the number of feeds.
	*
	* @return the number of feeds
	* @throws SystemException if a system exception occurred
	*/
	public int getFeedsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getFeedsCount();
	}

	/**
	* Updates the feed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feed the feed
	* @return the feed that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.updateFeed(feed);
	}

	/**
	* Updates the feed in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param feed the feed
	* @param merge whether to merge the feed with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the feed that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.updateFeed(feed, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _feedLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_feedLocalService.setBeanIdentifier(beanIdentifier);
	}

	public void updateFeed(long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.updateFeed(userId);
	}

	public void updateFeeds()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.updateFeeds();
	}

	public void updateFeeds(long companyId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.updateFeeds(companyId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public FeedLocalService getWrappedFeedLocalService() {
		return _feedLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedFeedLocalService(FeedLocalService feedLocalService) {
		_feedLocalService = feedLocalService;
	}

	public FeedLocalService getWrappedService() {
		return _feedLocalService;
	}

	public void setWrappedService(FeedLocalService feedLocalService) {
		_feedLocalService = feedLocalService;
	}

	private FeedLocalService _feedLocalService;
}