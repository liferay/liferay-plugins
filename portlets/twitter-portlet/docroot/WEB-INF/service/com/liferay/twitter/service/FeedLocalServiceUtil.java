/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="FeedLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FeedLocalServiceUtil {
	public static com.liferay.twitter.model.Feed addFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		return getService().addFeed(feed);
	}

	public static com.liferay.twitter.model.Feed createFeed(long feedId) {
		return getService().createFeed(feedId);
	}

	public static void deleteFeed(long feedId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteFeed(feedId);
	}

	public static void deleteFeed(com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		getService().deleteFeed(feed);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static com.liferay.twitter.model.Feed getFeed(long feedId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getFeed(feedId);
	}

	public static java.util.List<com.liferay.twitter.model.Feed> getFeeds(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getFeeds(start, end);
	}

	public static int getFeedsCount() throws com.liferay.portal.SystemException {
		return getService().getFeedsCount();
	}

	public static com.liferay.twitter.model.Feed updateFeed(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		return getService().updateFeed(feed);
	}

	public static void updateFeed(long userId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().updateFeed(userId);
	}

	public static void updateFeeds()
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().updateFeeds();
	}

	public static void updateFeeds(long companyId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().updateFeeds(companyId);
	}

	public static FeedLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate("twitter-portlet",
					FeedLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate("chat-portlet",
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new FeedLocalServiceClp(classLoaderProxy);
		}

		return _service;
	}

	public void setService(FeedLocalService service) {
		_service = service;
	}

	private static FeedLocalService _service;
}