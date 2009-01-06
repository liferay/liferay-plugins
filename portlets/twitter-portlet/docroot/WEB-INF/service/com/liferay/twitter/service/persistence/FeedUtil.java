/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package com.liferay.twitter.service.persistence;

/**
 * <a href="FeedUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FeedUtil {
	public static com.liferay.twitter.model.Feed create(long feedId) {
		return getPersistence().create(feedId);
	}

	public static com.liferay.twitter.model.Feed remove(long feedId)
		throws com.liferay.portal.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().remove(feedId);
	}

	public static com.liferay.twitter.model.Feed remove(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(feed);
	}

	public static com.liferay.twitter.model.Feed update(
		com.liferay.twitter.model.Feed feed)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(feed);
	}

	public static com.liferay.twitter.model.Feed update(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(feed, merge);
	}

	public static com.liferay.twitter.model.Feed updateImpl(
		com.liferay.twitter.model.Feed feed, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(feed, merge);
	}

	public static com.liferay.twitter.model.Feed findByPrimaryKey(long feedId)
		throws com.liferay.portal.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByPrimaryKey(feedId);
	}

	public static com.liferay.twitter.model.Feed fetchByPrimaryKey(long feedId)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(feedId);
	}

	public static com.liferay.twitter.model.Feed findByTwitterUserId(
		long twitterUserId)
		throws com.liferay.portal.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByTwitterUserId(twitterUserId);
	}

	public static com.liferay.twitter.model.Feed fetchByTwitterUserId(
		long twitterUserId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByTwitterUserId(twitterUserId);
	}

	public static com.liferay.twitter.model.Feed findByTwitterScreenName(
		java.lang.String twitterScreenName)
		throws com.liferay.portal.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		return getPersistence().findByTwitterScreenName(twitterScreenName);
	}

	public static com.liferay.twitter.model.Feed fetchByTwitterScreenName(
		java.lang.String twitterScreenName)
		throws com.liferay.portal.SystemException {
		return getPersistence().fetchByTwitterScreenName(twitterScreenName);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<com.liferay.twitter.model.Feed> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.twitter.model.Feed> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.twitter.model.Feed> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByTwitterUserId(long twitterUserId)
		throws com.liferay.portal.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		getPersistence().removeByTwitterUserId(twitterUserId);
	}

	public static void removeByTwitterScreenName(
		java.lang.String twitterScreenName)
		throws com.liferay.portal.SystemException,
			com.liferay.twitter.NoSuchFeedException {
		getPersistence().removeByTwitterScreenName(twitterScreenName);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByTwitterUserId(long twitterUserId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByTwitterUserId(twitterUserId);
	}

	public static int countByTwitterScreenName(
		java.lang.String twitterScreenName)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByTwitterScreenName(twitterScreenName);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static FeedPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(FeedPersistence persistence) {
		_persistence = persistence;
	}

	private static FeedPersistence _persistence;
}