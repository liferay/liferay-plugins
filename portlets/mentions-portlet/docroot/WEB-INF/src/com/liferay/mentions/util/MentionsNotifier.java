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

package com.liferay.mentions.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.notifications.NotificationEvent;
import com.liferay.portal.kernel.notifications.NotificationEventFactoryUtil;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.SubscriptionSender;
import com.liferay.portlet.asset.AssetRendererFactoryRegistryUtil;
import com.liferay.portlet.asset.model.AssetRendererFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Sergio González
 */
public class MentionsNotifier {

	public void notify(
			long userId, long groupId, String title, String content,
			String className, long classPK, String subject, String body,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		String[] mentionedUsersScreenNames = getMentionedUsersScreenNames(
			userId, content);

		if (ArrayUtil.isEmpty(mentionedUsersScreenNames)) {
			return;
		}

		User user = UserLocalServiceUtil.getUser(userId);

		String contentURL = (String)serviceContext.getAttribute("contentURL");

		String messageUserEmailAddress = PortalUtil.getUserEmailAddress(userId);
		String messageUserName = PortalUtil.getUserName(
			userId, StringPool.BLANK);

		String fromName = PrefsPropsUtil.getString(
			user.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_NAME);
		String fromAddress = PrefsPropsUtil.getString(
			user.getCompanyId(), PropsKeys.ADMIN_EMAIL_FROM_ADDRESS);

		SubscriptionSender subscriptionSender = new SubscriptionSender();

		subscriptionSender.setBody(body);
		subscriptionSender.setCompanyId(user.getCompanyId());
		subscriptionSender.setContextAttribute("[$CONTENT$]", content, false);
		subscriptionSender.setContextAttributes(
			"[$ASSET_ENTRY_NAME$]",
			getAssetEntryName(className, serviceContext), "[$USER_ADDRESS$]",
			messageUserEmailAddress, "[USER_NAME$]", messageUserName,
			"[$CONTENT_URL$]", contentURL);
		subscriptionSender.setFrom(fromAddress, fromName);
		subscriptionSender.setHtmlFormat(true);
		subscriptionSender.setMailId("mb_discussion", classPK);
		subscriptionSender.setPortletId(PortletKeys.MENTIONS);
		subscriptionSender.setScopeGroupId(groupId);
		subscriptionSender.setServiceContext(serviceContext);
		subscriptionSender.setSubject(subject);
		subscriptionSender.setUserId(userId);

		for (int i = 0; i < mentionedUsersScreenNames.length; i++) {
			String mentionedUserScreenName = mentionedUsersScreenNames[i];

			User mentionedUser = UserLocalServiceUtil.fetchUserByScreenName(
				user.getCompanyId(), mentionedUserScreenName);

			if (mentionedUser == null) {
				continue;
			}

			subscriptionSender.addRuntimeSubscribers(
				mentionedUser.getEmailAddress(), mentionedUser.getFullName());

			sendWebsiteNotification(
				mentionedUser.getUserId(), title, className, classPK,
				contentURL);
		}

		subscriptionSender.flushNotificationsAsync();
	}

	protected String getAssetEntryName(
		String className, ServiceContext serviceContext) {

		AssetRendererFactory assetRendererFactory =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactoryByClassName(
				className);

		if (assetRendererFactory != null) {
			return assetRendererFactory.getTypeName(
				serviceContext.getLocale(), false);
		}

		return StringPool.BLANK;
	}

	protected String[] getMentionedUsersScreenNames(long userId, String content)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		Matcher matcher = _pattern.matcher(content);

		Set<String> mentionedUsersScreenNames = new HashSet<String>();

		while (matcher.find()) {
			String mentionedUserScreenName = matcher.group(2);

			List<User> users = MentionsUserFinderUtil.getUsers(
				user.getCompanyId(), mentionedUserScreenName);

			for (User curUser : users) {
				if (mentionedUserScreenName.equals(curUser.getScreenName())) {
					mentionedUsersScreenNames.add(mentionedUserScreenName);

					break;
				}
			}
		}

		return mentionedUsersScreenNames.toArray(
			new String[mentionedUsersScreenNames.size()]);
	}

	protected void sendWebsiteNotification(
			long userId, String title, String className, long classPK,
			String contentURL)
		throws PortalException, SystemException {

		User user = UserLocalServiceUtil.getUser(userId);

		if (UserNotificationManagerUtil.isDeliver(
				userId, PortletKeys.MENTIONS, 0,
				MentionsConstants.NOTIFICATION_TYPE_MENTION,
				UserNotificationDeliveryConstants.TYPE_WEBSITE)) {

			JSONObject notificationEventJSONObject =
				JSONFactoryUtil.createJSONObject();

			notificationEventJSONObject.put("className", className);
			notificationEventJSONObject.put("classPK", classPK);
			notificationEventJSONObject.put("entryTitle", title);
			notificationEventJSONObject.put("entryURL", contentURL);
			notificationEventJSONObject.put("userId", userId);

			NotificationEvent notificationEvent =
				NotificationEventFactoryUtil.createNotificationEvent(
					System.currentTimeMillis(), PortletKeys.MENTIONS,
					notificationEventJSONObject);

			notificationEvent.setDeliveryRequired(0);

			UserNotificationEventLocalServiceUtil.addUserNotificationEvent(
				user.getUserId(), notificationEvent);
		}
	}

	private static Pattern _pattern = Pattern.compile(
		"(?:\\s|^|\\]|>)(@([^(?:@|>|\\[|\\s|,|.|<)]+))",
		Pattern.CASE_INSENSITIVE);

}