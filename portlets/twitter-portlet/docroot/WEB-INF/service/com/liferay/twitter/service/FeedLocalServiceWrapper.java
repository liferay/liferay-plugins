/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.twitter.service;

/**
 * <a href="FeedLocalServiceWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class FeedLocalServiceWrapper implements FeedLocalService {
	public FeedLocalServiceWrapper(FeedLocalService feedLocalService) {
		_feedLocalService = feedLocalService;
	}

	public com.liferay.twitter.model.Feed addFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		return _feedLocalService.addFeed(feed);
	}

	public com.liferay.twitter.model.Feed createFeed(long feedId) {
		return _feedLocalService.createFeed(feedId);
	}

	public void deleteFeed(long feedId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_feedLocalService.deleteFeed(feedId);
	}

	public void deleteFeed(com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		_feedLocalService.deleteFeed(feed);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return _feedLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	public com.liferay.twitter.model.Feed getFeed(long feedId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return _feedLocalService.getFeed(feedId);
	}

	public java.util.List<com.liferay.twitter.model.Feed> getFeeds(int start,
		int end) throws com.liferay.portal.SystemException {
		return _feedLocalService.getFeeds(start, end);
	}

	public int getFeedsCount() throws com.liferay.portal.SystemException {
		return _feedLocalService.getFeedsCount();
	}

	public com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		return _feedLocalService.updateFeed(feed);
	}

	public com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.SystemException {
		return _feedLocalService.updateFeed(feed, merge);
	}

	public void updateFeed(long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_feedLocalService.updateFeed(userId);
	}

	public void updateFeeds()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_feedLocalService.updateFeeds();
	}

	public void updateFeeds(long companyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		_feedLocalService.updateFeeds(companyId);
	}

	public FeedLocalService getWrappedFeedLocalService() {
		return _feedLocalService;
	}

	private FeedLocalService _feedLocalService;
}