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

package com.liferay.twitter.service;

/**
 * <p>
 * This class is a wrapper for {@link FeedLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FeedLocalService
 * @generated
 */
public class FeedLocalServiceWrapper implements FeedLocalService {
	public FeedLocalServiceWrapper(FeedLocalService feedLocalService) {
		_feedLocalService = feedLocalService;
	}

	public com.liferay.twitter.model.Feed addFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.addFeed(feed);
	}

	public com.liferay.twitter.model.Feed createFeed(long feedId) {
		return _feedLocalService.createFeed(feedId);
	}

	public void deleteFeed(long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.deleteFeed(feedId);
	}

	public void deleteFeed(com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.kernel.exception.SystemException {
		_feedLocalService.deleteFeed(feed);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.twitter.model.Feed getFeed(long feedId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getFeed(feedId);
	}

	public java.util.List<com.liferay.twitter.model.Feed> getFeeds(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getFeeds(start, end);
	}

	public int getFeedsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.getFeedsCount();
	}

	public com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.updateFeed(feed);
	}

	public com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _feedLocalService.updateFeed(feed, merge);
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

	public FeedLocalService getWrappedFeedLocalService() {
		return _feedLocalService;
	}

	private FeedLocalService _feedLocalService;
}