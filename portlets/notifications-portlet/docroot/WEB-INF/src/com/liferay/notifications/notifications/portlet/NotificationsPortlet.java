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

package com.liferay.notifications.notifications.portlet;

import com.liferay.notifications.util.PortletKeys;
import com.liferay.notifications.util.PortletPropsValues;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.notifications.UserNotificationFeedEntry;
import com.liferay.portal.kernel.notifications.UserNotificationManagerUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Subscription;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.SubscriptionLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;

/**
 * @author Jonathan Lee
 */
public class NotificationsPortlet extends MVCPortlet {

	public void deleteUserNotificationEvent(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userNotificationEventId = ParamUtil.getLong(
			actionRequest, "userNotificationEventId");

		try {
			UserNotificationEventLocalServiceUtil.deleteUserNotificationEvent(
				userNotificationEventId);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void markAllAsRead(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] userNotificationEventIds = ParamUtil.getLongValues(
			actionRequest, "userNotificationEventIds");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			for (long userNotificationEventId : userNotificationEventIds) {
				updateArchived(userNotificationEventId);
			}

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void markAsRead(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userNotificationEventId = ParamUtil.getLong(
			actionRequest, "userNotificationEventId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			updateArchived(userNotificationEventId);

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("deleteUserNotificationEvent")) {
				deleteUserNotificationEvent(actionRequest, actionResponse);
			}
			else if (actionName.equals("markAllAsRead")) {
				markAllAsRead(actionRequest, actionResponse);
			}
			else if (actionName.equals("markAsRead")) {
				markAsRead(actionRequest, actionResponse);
			}
			else if (actionName.equals("setDelivered")) {
				setDelivered(actionRequest, actionResponse);
			}
			else if (actionName.equals("unsubscribe")) {
				unsubscribe(actionRequest, actionResponse);
			}
			else if (actionName.equals("updateUserNotificationDelivery")) {
				updateUserNotificationDelivery(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	@Override
	public void serveResource(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws PortletException {

		try {
			String resourceID = resourceRequest.getResourceID();

			if (resourceID.equals("getNotificationsCount")) {
				getNotificationsCount(resourceRequest, resourceResponse);
			}
			else if (resourceID.equals("getUserNotificationEvents")) {
				getUserNotificationEvents(resourceRequest, resourceResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void setDelivered(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			List<UserNotificationEvent> userNotificationEvents =
				new ArrayList<>();

			if (PortletPropsValues.USER_NOTIFICATION_DOCKBAR_SPLIT) {
				boolean actionable = ParamUtil.getBoolean(
					actionRequest, "actionable");

				userNotificationEvents.addAll(
					UserNotificationEventLocalServiceUtil.
						getDeliveredUserNotificationEvents(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false, actionable));
			}
			else {
				userNotificationEvents.addAll(
					UserNotificationEventLocalServiceUtil.
						getDeliveredUserNotificationEvents(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false));
			}

			for (UserNotificationEvent userNotificationEvent :
					userNotificationEvents) {

				userNotificationEvent.setDelivered(true);

				UserNotificationEventLocalServiceUtil.
					updateUserNotificationEvent(userNotificationEvent);
			}

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void unsubscribe(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long subscriptionId = ParamUtil.getLong(
			actionRequest, "subscriptionId");
		long userNotificationEventId = ParamUtil.getLong(
			actionRequest, "userNotificationEventId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			SubscriptionLocalServiceUtil.deleteSubscription(subscriptionId);

			UserNotificationEvent userNotificationEvent =
				UserNotificationEventLocalServiceUtil.
					fetchUserNotificationEvent(userNotificationEventId);

			if (userNotificationEvent != null) {
				if (!userNotificationEvent.isArchived()) {
					updateArchived(userNotificationEventId);
				}
			}

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateUserNotificationDelivery(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long userNotificationDeliveryId = ParamUtil.getLong(
			actionRequest, "userNotificationDeliveryId");

		boolean deliver = ParamUtil.getBoolean(actionRequest, "deliver", true);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			UserNotificationDeliveryLocalServiceUtil.
				updateUserNotificationDelivery(
					userNotificationDeliveryId, deliver);

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	protected static String getIconMenuDiv(String listItems) {
		if (!Validator.isBlank(listItems)) {
			StringBundler sb = new StringBundler(6);

			sb.append("<div class=\"lfr-icon-menu\"><a ");
			sb.append("class=\"dropdown-toggle\" href=\"javascript:;\"><i ");
			sb.append("class=\"caret\"></i></a><ul class=\"dropdown-menu ");
			sb.append("lfr-menu-list direction-left\">");
			sb.append(listItems);
			sb.append("</ul></div>");

			return sb.toString();
		}

		return StringPool.BLANK;
	}

	protected static String getMarkAsReadLI(ThemeDisplay themeDisplay) {
		StringBundler sb = new StringBundler(5);

		sb.append("<li><a class=\"taglib-icon mark-as-read\" ");
		sb.append("href=\"javascript:;\"><i class=\"icon-remove\"></i><span ");
		sb.append("class=\"taglib-text-icon\">");
		sb.append(LanguageUtil.get(themeDisplay.getLocale(), "mark-as-read"));
		sb.append("</span></a></li>");

		return sb.toString();
	}

	protected static String getUnsubscribeLI(
		String unsubscribeURL, ThemeDisplay themeDisplay) {

		StringBundler sb = new StringBundler(8);

		sb.append("<li><a class=\"taglib-icon unsubscribe\" ");
		sb.append("data-unsubscribeURL=\"");
		sb.append(unsubscribeURL);
		sb.append("\" href=\"javascript:;\"><i class=\"icon-rss\"></i><span ");
		sb.append("class=\"taglib-text-icon\">");
		sb.append(LanguageUtil.get(themeDisplay.getLocale(), "unsubscribe"));
		sb.append("</span><div class=\"unsubscribe-info\">");
		sb.append(
			LanguageUtil.get(
				themeDisplay.getLocale(),
				"stop-receiving-notifications-from-this-asset"));
		sb.append("</div></a></li>");

		return sb.toString();
	}

	protected String getIconMenu(
			UserNotificationEvent userNotificationEvent,
			LiferayPortletResponse liferayPortletResponse,
			ThemeDisplay themeDisplay)
		throws Exception {

		String markAsReadLI = StringPool.BLANK;

		if (!userNotificationEvent.isArchived()) {
			markAsReadLI = getMarkAsReadLI(themeDisplay);
		}

		String unsubscribeLI = StringPool.BLANK;

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			userNotificationEvent.getPayload());

		long subscriptionId = jsonObject.getLong("subscriptionId");

		if (subscriptionId > 0) {
			Subscription subscription =
				SubscriptionLocalServiceUtil.fetchSubscription(subscriptionId);

			if (subscription == null) {
				subscriptionId = 0;
			}
		}

		if (subscriptionId > 0) {
			PortletURL unsubscribeActionURL =
				liferayPortletResponse.createActionURL(
					PortletKeys.NOTIFICATIONS);

			unsubscribeActionURL.setParameter(
				"subscriptionId", String.valueOf(subscriptionId));
			unsubscribeActionURL.setParameter(
				"userNotificationEventId",
				String.valueOf(
					userNotificationEvent.getUserNotificationEventId()));
			unsubscribeActionURL.setWindowState(WindowState.NORMAL);
			unsubscribeActionURL.setParameter(
				"javax.portlet.action", "unsubscribe");

			unsubscribeLI = getUnsubscribeLI(
				unsubscribeActionURL.toString(), themeDisplay);
		}

		String listItems = markAsReadLI + unsubscribeLI;

		return getIconMenuDiv(listItems);
	}

	protected void getNotificationsCount(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			jsonObject.put(
				"timestamp", String.valueOf(System.currentTimeMillis()));

			if (PortletPropsValues.USER_NOTIFICATION_DOCKBAR_SPLIT) {
				int newActionableUserNotificationsCount =
					UserNotificationEventLocalServiceUtil.
						getDeliveredUserNotificationEventsCount(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false, true);

				jsonObject.put(
					"newActionableUserNotificationsCount",
					newActionableUserNotificationsCount);

				int newNonactionableUserNotificationsCount =
					UserNotificationEventLocalServiceUtil.
						getDeliveredUserNotificationEventsCount(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false, false);

				jsonObject.put(
					"newNonactionableUserNotificationsCount",
					newNonactionableUserNotificationsCount);

				int unreadActionableUserNotificationsCount =
					UserNotificationEventLocalServiceUtil.
						getArchivedUserNotificationEventsCount(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							true, false);

				jsonObject.put(
					"unreadActionableUserNotificationsCount",
					unreadActionableUserNotificationsCount);

				int unreadNonactionableUserNotificationsCount =
					UserNotificationEventLocalServiceUtil.
						getArchivedUserNotificationEventsCount(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false, false);

				jsonObject.put(
					"unreadNonactionableUserNotificationsCount",
					unreadNonactionableUserNotificationsCount);
			}
			else {
				int newUserNotificationsCount =
					UserNotificationEventLocalServiceUtil.
						getDeliveredUserNotificationEventsCount(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false);

				jsonObject.put(
					"newUserNotificationsCount", newUserNotificationsCount);

				int unreadUserNotificationsCount =
					UserNotificationEventLocalServiceUtil.
						getArchivedUserNotificationEventsCount(
							themeDisplay.getUserId(),
							UserNotificationDeliveryConstants.TYPE_WEBSITE,
							false);

				jsonObject.put(
					"unreadUserNotificationsCount",
					unreadUserNotificationsCount);
			}

			jsonObject.put("success", Boolean.TRUE);
		}
		catch (Exception e) {
			jsonObject.put("success", Boolean.FALSE);
		}

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected void getUserNotificationEvents(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean fullView = ParamUtil.getBoolean(resourceRequest, "fullView");
		boolean actionable = ParamUtil.getBoolean(
			resourceRequest, "actionable");
		int start = ParamUtil.getInteger(resourceRequest, "start");
		int end = ParamUtil.getInteger(resourceRequest, "end");

		List<UserNotificationEvent> userNotificationEvents = null;
		int total = 0;

		if (fullView) {
			userNotificationEvents =
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEvents(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, true,
						actionable, start, end);

			total =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						actionable, false);
		}
		else {
			userNotificationEvents =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEvents(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						actionable, false, start, end);

			total =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						actionable, false);
		}

		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();

		List<Long> newUserNotificationEventIds = new ArrayList<>();

		for (UserNotificationEvent userNotificationEvent :
				userNotificationEvents) {

			String entry = renderEntry(
				resourceRequest, resourceResponse, userNotificationEvent);

			if (Validator.isNotNull(entry)) {
				jsonArray.put(entry);

				if (!userNotificationEvent.isArchived()) {
					newUserNotificationEventIds.add(
						userNotificationEvent.getUserNotificationEventId());
				}
			}
		}

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("entries", jsonArray);

		int newTotalUuserNotificationEventsCount = total;

		jsonObject.put(
			"newTotalUuserNotificationEventsCount",
			newTotalUuserNotificationEventsCount);

		jsonObject.put(
			"newUserNotificationEventIds",
			StringUtil.merge(newUserNotificationEventIds));
		jsonObject.put(
			"newUserNotificationEventsCount",
			newUserNotificationEventIds.size());

		jsonObject.put("total", total);

		writeJSON(resourceRequest, resourceResponse, jsonObject);
	}

	protected String renderEntry(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse,
			UserNotificationEvent userNotificationEvent)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String actionDiv = _ACTION_DIV_DEFAULT;

		Portlet portlet = PortletLocalServiceUtil.getPortletById(
			themeDisplay.getCompanyId(), userNotificationEvent.getType());

		String portletName = portlet.getDisplayName();
		String portletIcon = portlet.getContextPath() + portlet.getIcon();

		JSONObject userNotificationEventJSONObject =
			JSONFactoryUtil.createJSONObject(
				userNotificationEvent.getPayload());

		long userId = userNotificationEventJSONObject.getLong("userId");

		String userFullName = HtmlUtil.escape(
			PortalUtil.getUserName(userId, StringPool.BLANK));

		String userPortraitURL = StringPool.BLANK;

		User user = UserLocalServiceUtil.fetchUserById(userId);

		if (user != null) {
			userPortraitURL = user.getPortraitURL(themeDisplay);
		}

		Format dateFormatDate = FastDateFormatFactoryUtil.getDate(
			DateFormat.FULL, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		Format dateTimeFormat = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.FULL, DateFormat.SHORT, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		String timestamp = Time.getRelativeTimeDescription(
			userNotificationEvent.getTimestamp(), themeDisplay.getLocale(),
			themeDisplay.getTimeZone(), dateFormatDate);

		String timeTitle = dateTimeFormat.format(
			userNotificationEvent.getTimestamp());

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			resourceRequest);

		UserNotificationFeedEntry userNotificationFeedEntry =
			UserNotificationManagerUtil.interpret(
				StringPool.BLANK, userNotificationEvent, serviceContext);

		if (userNotificationFeedEntry == null) {
			String body = StringUtil.replace(
				_BODY_TEMPLATE_DEFAULT, new String[] {"[$BODY$]", "[$TITLE$]"},
				new String[] {
					serviceContext.translate(
						"unable-to-display-notification-for-x",
						portlet.getDisplayName()),
					serviceContext.translate(
						"notification-no-longer-applies")
				});

			return StringUtil.replace(
				ContentUtil.get(PortletPropsValues.USER_NOTIFICATION_ENTRY),
				new String[] {
					"[$ACTION_DIV$]", "[$BODY$]", "[$CSS_CLASS$]",
					"[$ICON_MENU$]", "[$PORTLET_ICON$]", "[$PORTLET_NAME$]",
					"[$TIMESTAMP$]", "[$TIMETITLE$]", "[$USER_FULL_NAME$]",
					"[$USER_PORTRAIT_URL$]"
				},
				new String[] {
					actionDiv, body, StringPool.BLANK, StringPool.BLANK,
					portletIcon, portletName, timestamp, timeTitle,
					userFullName, userPortraitURL
				});
		}

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		PortletURL actionURL = liferayPortletResponse.createActionURL(
			PortletKeys.NOTIFICATIONS);

		actionURL.setParameter(
			"userNotificationEventId",
			String.valueOf(userNotificationEvent.getUserNotificationEventId()));
		actionURL.setWindowState(WindowState.NORMAL);

		String cssClass = StringPool.BLANK;
		String iconMenu = StringPool.BLANK;

		if (userNotificationEvent.isActionRequired()) {
			actionURL.setParameter(
				"javax.portlet.action", "deleteUserNotificationEvent");

			actionDiv = StringUtil.replace(
				_DELETE_DIV, "[$DELETE_URL$]", actionURL.toString());
		}
		else {
			actionURL.setParameter("javax.portlet.action", "markAsRead");

			actionDiv = StringUtil.replace(
				_MARK_AS_READ_DIV,
				new String[] {"[$LINK$]", "[$MARK_AS_READ_URL$]"},
				new String[] {
					userNotificationFeedEntry.getLink(), actionURL.toString()
				});

			if (userNotificationEvent.isArchived()) {
				cssClass = "archived";
			}

			iconMenu = getIconMenu(
				userNotificationEvent, liferayPortletResponse, themeDisplay);
		}

		return StringUtil.replace(
			ContentUtil.get(PortletPropsValues.USER_NOTIFICATION_ENTRY),
			new String[] {
				"[$ACTION_DIV$]", "[$BODY$]", "[$CSS_CLASS$]", "[$ICON_MENU$]",
				"[$PORTLET_ICON$]", "[$PORTLET_NAME$]", "[$TIMESTAMP$]",
				"[$TIMETITLE$]", "[$USER_FULL_NAME$]", "[$USER_PORTRAIT_URL$]"
			},
			new String[] {
				actionDiv, userNotificationFeedEntry.getBody(), cssClass,
				iconMenu, portletIcon, portletName, timestamp, timeTitle,
				userFullName, userPortraitURL
			});
	}

	protected void updateArchived(long userNotificationEventId)
		throws Exception {

		UserNotificationEvent userNotificationEvent =
			UserNotificationEventLocalServiceUtil.getUserNotificationEvent(
				userNotificationEventId);

		userNotificationEvent.setArchived(true);

		UserNotificationEventLocalServiceUtil.updateUserNotificationEvent(
			userNotificationEvent);
	}

	private static final String _ACTION_DIV_DEFAULT =
		"<div class=\"clearfix\">";

	private static final String _BODY_TEMPLATE_DEFAULT =
		"<div class=\"title\">[$TITLE$]</div><div class=\"body\">[$BODY$]" +
			"</div>";

	private static final String _DELETE_DIV =
		"<div class=\"clearfix user-notification-delete\" data-deleteURL=\"" +
			"[$DELETE_URL$]\">";

	private static final String _MARK_AS_READ_DIV =
		"<div class=\"clearfix user-notification-link\" data-href=\"" +
			"[$LINK$]\" data-markAsReadURL=\"[$MARK_AS_READ_URL$]\">";

}