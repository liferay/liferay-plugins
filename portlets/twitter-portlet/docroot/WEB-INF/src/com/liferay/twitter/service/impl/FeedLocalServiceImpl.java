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

package com.liferay.twitter.service.impl;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.twitter.FeedTwitterScreenNameException;
import com.liferay.twitter.model.Feed;
import com.liferay.twitter.service.base.FeedLocalServiceBaseImpl;
import com.liferay.twitter.social.TwitterActivityKeys;
import com.liferay.util.JSONUtil;
import com.liferay.util.dao.hibernate.QueryUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * <a href="FeedLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FeedLocalServiceImpl extends FeedLocalServiceBaseImpl {

	public Feed updateFeed(long userId)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUserById(userId);

		return updateFeed(user);
	}

	public void updateFeeds() throws PortalException, SystemException {
System.out.println("Updating feeds");
		for (long companyId : PortalUtil.getCompanyIds()) {
			updateFeeds(companyId);
		}
	}

	public void updateFeeds(long companyId)
		throws PortalException, SystemException {

		LinkedHashMap userParams = new LinkedHashMap();

		userParams.put("contactTwitterSn", Boolean.TRUE);

		List<User> users = UserLocalServiceUtil.search(
			companyId, null, null, userParams, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);

		for (User user : users) {
			try {
				updateFeed(user);
			}
			catch (Exception e) {
				if (_log.isWarnEnabled()) {
					_log.warn(e, e);
				}
			}
		}
	}

	protected JSONArray getUserTimelineJSONArray(
			String twitterScreenName, long sinceId)
		throws SystemException {

		try {
			String url = _URL + twitterScreenName + ".json?since_id=" + sinceId;

			return new JSONArray(HttpUtil.URLtoString(url));
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	protected Feed updateFeed(User user)
		throws PortalException, SystemException {

		String twitterScreenName = user.getContact().getTwitterSn();
		Date now = new Date();

		if (Validator.isNull(twitterScreenName)) {
			throw new FeedTwitterScreenNameException();
		}

		Feed feed = feedPersistence.fetchByTwitterScreenName(twitterScreenName);

		JSONArray jsonArray = null;

		if (feed == null) {
			jsonArray = getUserTimelineJSONArray(twitterScreenName, 0);

			long feedId = CounterLocalServiceUtil.increment();

			feed = feedPersistence.create(feedId);

			feed.setTwitterScreenName(twitterScreenName);
			feed.setCreateDate(now);
		}

		feed.setModifiedDate(now);

		if (jsonArray == null) {
			jsonArray = getUserTimelineJSONArray(
				twitterScreenName, feed.getLastStatusId());
		}

		if (jsonArray.length() == 0) {
			if (feed.isNew()) {
				feedPersistence.update(feed, false);
			}

			return feed;
		}

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject statusJSON = jsonArray.optJSONObject(i);

			SimpleDateFormat sdf = new SimpleDateFormat(
				"EEE MMM d hh:mm:ss Z yyyy");

			Date createDate = null;

			try {
				createDate = sdf.parse(statusJSON.optString("created_at"));
			}
			catch (ParseException pe) {
				throw new SystemException(pe);
			}

			long statusId = statusJSON.optLong("id");
			String text = statusJSON.optString("text");

			if (feed.getTwitterUserId() <= 0) {
				JSONObject userJSON = statusJSON.optJSONObject("user");

				feed.setTwitterUserId(userJSON.optLong("id"));
			}

			if (feed.getLastStatusId() < statusId) {
				feed.setLastStatusId(statusId);
			}

			JSONObject extraData = new JSONObject();

			JSONUtil.put(extraData, "text", text);

			SocialActivityLocalServiceUtil.addActivity(
				user.getUserId(), 0, createDate, Feed.class.getName(),
				statusId, TwitterActivityKeys.ADD_STATUS,
				extraData.toString(), 0);
		}

		feedPersistence.update(feed, false);

		return feed;
	}

	private static final String _URL =
		"http://twitter.com/statuses/user_timeline/";

	private static Log _log = LogFactory.getLog(FeedLocalServiceImpl.class);

}