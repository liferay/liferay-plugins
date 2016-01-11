/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This file is part of Liferay Social Office. Liferay Social Office is free
 * software: you can redistribute it and/or modify it under the terms of the GNU
 * Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Liferay Social Office is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Liferay Social Office. If not, see http://www.gnu.org/licenses/agpl-3.0.html.
 */

package com.liferay.microblogs.util;

import com.liferay.microblogs.model.MicroblogsEntry;
import com.liferay.microblogs.model.MicroblogsEntryConstants;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.comparator.UserFirstNameComparator;
import com.liferay.portlet.PortletURLFactoryUtil;
import com.liferay.portlet.social.model.SocialRelationConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.WindowState;
import javax.portlet.WindowStateException;

/**
 * @author Jonathan Lee
 */
public class MicroblogsUtil {

	public static List<String> getHashtags(String content) {
		List<String> hashtags = new ArrayList<>();

		Matcher matcher = _hashtagPattern.matcher(content);

		while (matcher.find()) {
			String hashtag = matcher.group();

			hashtag = hashtag.substring(1);

			hashtags.add(hashtag);
		}

		return hashtags;
	}

	public static JSONArray getJSONRecipients(
			long userId, ThemeDisplay themeDisplay)
		throws PortalException {

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<User> users = UserLocalServiceUtil.getSocialUsers(
			userId, SocialRelationConstants.TYPE_BI_CONNECTION,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			new UserFirstNameComparator(true));

		for (User user : users) {
			if (user.isDefaultUser() || (userId == user.getUserId())) {
				continue;
			}

			JSONObject userJSONObject = JSONFactoryUtil.createJSONObject();

			userJSONObject.put("emailAddress", user.getEmailAddress());
			userJSONObject.put("fullName", user.getFullName());
			userJSONObject.put("jobTitle", user.getJobTitle());
			userJSONObject.put(
				"portraitURL", user.getPortraitURL(themeDisplay));
			userJSONObject.put("screenName", user.getScreenName());
			userJSONObject.put("userId", user.getUserId());

			jsonArray.put(userJSONObject);
		}

		return jsonArray;
	}

	public static int getNotificationType(
			MicroblogsEntry microblogsEntry, long userId, int deliveryType)
		throws PortalException {

		if (isTaggedUser(
				microblogsEntry.getMicroblogsEntryId(), false, userId) &&
			UserNotificationManagerUtil.isDeliver(
				userId, PortletKeys.MICROBLOGS, 0,
				MicroblogsEntryConstants.NOTIFICATION_TYPE_TAG, deliveryType)) {

			return MicroblogsEntryConstants.NOTIFICATION_TYPE_TAG;
		}
		else if (microblogsEntry.getType() ==
					MicroblogsEntryConstants.TYPE_REPLY) {

			long rootMicroblogsEntryId = getRootMicroblogsEntryId(
				microblogsEntry);

			if ((getRootMicroblogsUserId(microblogsEntry) == userId) &&
				UserNotificationManagerUtil.isDeliver(
					userId, PortletKeys.MICROBLOGS, 0,
					MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY,
					deliveryType)) {

				return MicroblogsEntryConstants.NOTIFICATION_TYPE_REPLY;
			}
			else if (hasReplied(rootMicroblogsEntryId, userId) &&
					 UserNotificationManagerUtil.isDeliver(
						 userId, PortletKeys.MICROBLOGS, 0,
						 MicroblogsEntryConstants.
							 NOTIFICATION_TYPE_REPLY_TO_REPLIED,
						 deliveryType)) {

				return MicroblogsEntryConstants.
					NOTIFICATION_TYPE_REPLY_TO_REPLIED;
			}
			else if (MicroblogsUtil.isTaggedUser(
						rootMicroblogsEntryId, true, userId) &&
					 UserNotificationManagerUtil.isDeliver(
						 userId, PortletKeys.MICROBLOGS, 0,
						 MicroblogsEntryConstants.
							 NOTIFICATION_TYPE_REPLY_TO_TAGGED,
						 deliveryType)) {

				return MicroblogsEntryConstants.
					NOTIFICATION_TYPE_REPLY_TO_TAGGED;
			}
		}

		return MicroblogsEntryConstants.NOTIFICATION_TYPE_UNKNOWN;
	}

	public static String getProcessedContent(
			MicroblogsEntry microblogsEntry, ServiceContext serviceContext)
		throws PortalException {

		return getProcessedContent(
			microblogsEntry.getContent(), serviceContext);
	}

	public static String getProcessedContent(
			String content, ServiceContext serviceContext)
		throws PortalException {

		content = replaceHashtags(content, serviceContext);

		content = replaceUserTags(content, serviceContext);

		return content;
	}

	public static long getRootMicroblogsEntryId(
		MicroblogsEntry microblogsEntry) {

		if (microblogsEntry.getType() == MicroblogsEntryConstants.TYPE_REPOST) {
			return microblogsEntry.getMicroblogsEntryId();
		}

		return microblogsEntry.getParentMicroblogsEntryId();
	}

	public static long getRootMicroblogsUserId(MicroblogsEntry microblogsEntry)
		throws PortalException {

		if (microblogsEntry.getType() == MicroblogsEntryConstants.TYPE_REPOST) {
			return microblogsEntry.getUserId();
		}

		return microblogsEntry.getParentMicroblogsEntryUserId();
	}

	public static List<String> getScreenNames(String content) {
		List<String> screenNames = new ArrayList<>();

		Matcher matcher = _userTagPattern.matcher(content);

		while (matcher.find()) {
			String screenName = matcher.group();

			screenName = screenName.replace("[@", StringPool.BLANK);
			screenName = screenName.replace("]", StringPool.BLANK);

			screenNames.add(screenName);
		}

		return screenNames;
	}

	public static List<Long> getSubscriberUserIds(
		MicroblogsEntry microblogsEntry) {

		List<Long> receiverUserIds = new ArrayList<>();

		List<Subscription> subscriptions =
			SubscriptionLocalServiceUtil.getSubscriptions(
				microblogsEntry.getCompanyId(), MicroblogsEntry.class.getName(),
				getRootMicroblogsEntryId(microblogsEntry));

		for (Subscription subscription : subscriptions) {
			if (microblogsEntry.getUserId() == subscription.getUserId()) {
				continue;
			}

			receiverUserIds.add(subscription.getUserId());
		}

		return receiverUserIds;
	}

	public static boolean hasReplied(long parentMicroblogsEntryId, long userId)
		throws PortalException {

		List<MicroblogsEntry> microblogsEntries = new ArrayList<>();

		microblogsEntries.addAll(
			MicroblogsEntryLocalServiceUtil.
				getParentMicroblogsEntryMicroblogsEntries(
					MicroblogsEntryConstants.TYPE_REPLY,
					parentMicroblogsEntryId, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS));

		microblogsEntries.add(
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				parentMicroblogsEntryId));

		for (MicroblogsEntry microblogsEntry : microblogsEntries) {
			if (microblogsEntry.getUserId() == userId) {
				return true;
			}
		}

		return false;
	}

	public static boolean isTaggedUser(
			long microblogsEntryId, boolean checkParent, long userId)
		throws PortalException {

		MicroblogsEntry microblogsEntry =
			MicroblogsEntryLocalServiceUtil.fetchMicroblogsEntry(
				microblogsEntryId);

		if (microblogsEntry == null) {
			return false;
		}

		if (!checkParent) {
			return isTaggedUser(microblogsEntry, userId);
		}

		long rootMicroblogsEntryId = getRootMicroblogsEntryId(microblogsEntry);

		List<MicroblogsEntry> microblogsEntries = new ArrayList<>();

		microblogsEntries.addAll(
			MicroblogsEntryLocalServiceUtil.
				getParentMicroblogsEntryMicroblogsEntries(
					MicroblogsEntryConstants.TYPE_REPLY, rootMicroblogsEntryId,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS));

		microblogsEntries.add(
			MicroblogsEntryLocalServiceUtil.getMicroblogsEntry(
				rootMicroblogsEntryId));

		for (MicroblogsEntry curMicroblogsEntry : microblogsEntries) {
			if (isTaggedUser(curMicroblogsEntry, userId)) {
				return true;
			}
		}

		return false;
	}

	protected static boolean isTaggedUser(
			MicroblogsEntry microblogsEntry, long userId)
		throws PortalException {

		List<String> screenNames = getScreenNames(microblogsEntry.getContent());

		for (String screenName : screenNames) {
			long screenNameUserId = UserLocalServiceUtil.getUserIdByScreenName(
				microblogsEntry.getCompanyId(), screenName);

			if (screenNameUserId == userId) {
				return true;
			}
		}

		return false;
	}

	protected static String replaceHashtags(
			String content, ServiceContext serviceContext)
		throws PortalException {

		String escapedContent = HtmlUtil.escape(content);

		ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

		Matcher matcher = _hashtagPattern.matcher(content);

		while (matcher.find()) {
			String result = matcher.group();

			StringBuilder sb = new StringBuilder(6);

			sb.append("<span class=\"hashtag\">#</span>");
			sb.append("<a class=\"hashtag-link\" href=\"");

			PortletURL portletURL = null;

			Group group = GroupLocalServiceUtil.getUserGroup(
				themeDisplay.getCompanyId(), themeDisplay.getUserId());

			long portletPlid = PortalUtil.getPlidFromPortletId(
				group.getGroupId(), true, "1_WAR_microblogsportlet");

			if (portletPlid != 0) {
				portletURL = PortletURLFactoryUtil.create(
					serviceContext.getLiferayPortletRequest(),
					"1_WAR_microblogsportlet", portletPlid,
					PortletRequest.RENDER_PHASE);

				try {
					portletURL.setWindowState(LiferayWindowState.NORMAL);
				}
				catch (WindowStateException wse) {
				}
			}
			else {
				LiferayPortletResponse liferayPortletResponse =
					serviceContext.getLiferayPortletResponse();

				portletURL = liferayPortletResponse.createRenderURL(
					"1_WAR_microblogsportlet");

				try {
					portletURL.setWindowState(WindowState.MAXIMIZED);
				}
				catch (WindowStateException wse) {
				}
			}

			portletURL.setParameter("mvcPath", "/microblogs/view.jsp");

			String assetTagName = result.substring(1);

			portletURL.setParameter("tabs1", assetTagName);
			portletURL.setParameter("assetTagName", assetTagName);

			sb.append(portletURL);

			sb.append("\">");
			sb.append(assetTagName);
			sb.append("</a>");

			String tagLink = sb.toString();

			escapedContent = StringUtil.replace(
				escapedContent, result, tagLink);
		}

		return escapedContent;
	}

	protected static String replaceUserTags(
			String content, ServiceContext serviceContext)
		throws PortalException {

		Matcher matcher = _userTagPattern.matcher(content);

		while (matcher.find()) {
			String result = matcher.group();

			try {
				StringBuilder sb = new StringBuilder(5);

				sb.append("<a href=\"");

				String assetTagScreenName = result.replace(
					"[@", StringPool.BLANK);

				assetTagScreenName = assetTagScreenName.replace(
					"]", StringPool.BLANK);

				ThemeDisplay themeDisplay = serviceContext.getThemeDisplay();

				User assetTagUser = UserLocalServiceUtil.getUserByScreenName(
					themeDisplay.getCompanyId(), assetTagScreenName);

				sb.append(assetTagUser.getDisplayURL(themeDisplay));

				sb.append("\">");

				String assetTagUserName = PortalUtil.getUserName(
					assetTagUser.getUserId(), assetTagScreenName);

				sb.append(assetTagUserName);

				sb.append("</a>");

				String userLink = sb.toString();

				content = StringUtil.replace(content, result, userLink);
			}
			catch (NoSuchUserException nsue) {
			}
		}

		return content;
	}

	private static Pattern _hashtagPattern = Pattern.compile("\\#[a-zA-Z]\\w*");
	private static Pattern _userTagPattern = Pattern.compile("\\[\\@\\S*\\]");

}