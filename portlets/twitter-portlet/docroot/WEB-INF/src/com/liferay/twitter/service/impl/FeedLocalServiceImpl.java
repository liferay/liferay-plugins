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

package com.liferay.twitter.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.shard.ShardUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.Contact;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.social.service.SocialActivityLocalServiceUtil;
import com.liferay.twitter.FeedTwitterScreenNameException;
import com.liferay.twitter.model.Feed;
import com.liferay.twitter.service.base.FeedLocalServiceBaseImpl;
import com.liferay.twitter.social.TwitterActivityKeys;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class FeedLocalServiceImpl extends FeedLocalServiceBaseImpl {

	public void updateFeed(long userId)
		throws PortalException, SystemException {

		User user = userLocalService.getUserById(userId);

		updateFeed(user);
	}

	public void updateFeeds() throws PortalException, SystemException {
		for (long companyId : PortalUtil.getCompanyIds()) {
			updateFeeds(companyId);
		}
	}

	public void updateFeeds(long companyId)
		throws PortalException, SystemException {

		ShardUtil.pushCompanyService(companyId);

		try {
			LinkedHashMap userParams = new LinkedHashMap();

			userParams.put("contactTwitterSn", Boolean.TRUE);

			List<User> users = userLocalService.search(
				companyId, null, WorkflowConstants.STATUS_APPROVED, userParams,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, (OrderByComparator)null);

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
		finally {
			ShardUtil.popCompanyService();
		}
	}

	protected JSONArray getUserTimelineJSONArray(
		String twitterScreenName, long sinceId) {

		try {
			String url = _URL + twitterScreenName + ".json";

			if (sinceId > 0) {
				url += "?since_id=" + sinceId;
			}

			return JSONFactoryUtil.createJSONArray(HttpUtil.URLtoString(url));
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}

			return null;
		}
	}

	protected void updateFeed(User user)
		throws PortalException, SystemException {

		Contact contact = user.getContact();

		String twitterScreenName = contact.getTwitterSn();

		if (Validator.isNull(twitterScreenName)) {
			throw new FeedTwitterScreenNameException();
		}

		Date now = new Date();

		Feed feed = feedPersistence.fetchByC_TSN(
			user.getCompanyId(), twitterScreenName);

		JSONArray jsonArray = null;

		if (feed == null) {
			jsonArray = getUserTimelineJSONArray(twitterScreenName, 0);

			long feedId = counterLocalService.increment();

			feed = feedPersistence.create(feedId);

			feed.setCompanyId(user.getCompanyId());
			feed.setUserId(user.getUserId());
			feed.setUserName(user.getFullName());
			feed.setCreateDate(now);
			feed.setModifiedDate(now);
			feed.setTwitterScreenName(twitterScreenName);

			feedPersistence.update(feed, false);

			if (jsonArray == null) {
				return;
			}
		}

		if (jsonArray == null) {
			jsonArray = getUserTimelineJSONArray(
				twitterScreenName, feed.getLastStatusId());
		}

		if ((jsonArray == null) || (jsonArray.length() == 0)) {
			return;
		}

		try {
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject statusJSON = jsonArray.getJSONObject(i);

				SimpleDateFormat sdf = new SimpleDateFormat(
					"EEE MMM d hh:mm:ss Z yyyy");

				Date createDate = null;

				try {
					createDate = sdf.parse(statusJSON.getString("created_at"));
				}
				catch (ParseException pe) {
					throw new SystemException(pe);
				}

				long statusId = statusJSON.getLong("id");
				String text = statusJSON.getString("text");

				if (feed.getTwitterUserId() <= 0) {
					JSONObject userJSON = statusJSON.getJSONObject("user");

					feed.setTwitterUserId(userJSON.getLong("id"));
				}

				if (feed.getLastStatusId() < statusId) {
					feed.setLastStatusId(statusId);
				}

				JSONObject extraData = JSONFactoryUtil.createJSONObject();

				extraData.put("text", text);

				SocialActivityLocalServiceUtil.addActivity(
					user.getUserId(), 0, createDate, Feed.class.getName(),
					statusId, TwitterActivityKeys.ADD_STATUS,
					extraData.toString(), 0);
			}
		}
		finally {
			feed.setModifiedDate(now);

			feedPersistence.update(feed, false);
		}
	}

	private static final String _URL =
		"http://twitter.com/statuses/user_timeline/";

	private static Log _log = LogFactoryUtil.getLog(FeedLocalServiceImpl.class);

}