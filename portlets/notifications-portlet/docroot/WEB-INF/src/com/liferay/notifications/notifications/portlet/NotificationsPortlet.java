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
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserNotificationDeliveryConstants;
import com.liferay.portal.model.UserNotificationEvent;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.service.UserNotificationDeliveryLocalServiceUtil;
import com.liferay.portal.service.UserNotificationEventLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.ContentUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

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
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEvents(
						themeDisplay.getUserId(), false);

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
	}

	protected void getNotificationsCount(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)resourceRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			int newUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEventsCount(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

			jsonObject.put(
				"newUserNotificationsCount", newUserNotificationsCount);

			jsonObject.put(
				"timestamp", String.valueOf(System.currentTimeMillis()));

			int unreadUserNotificationsCount =
				UserNotificationEventLocalServiceUtil.
					getArchivedUserNotificationEventsCount(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE, false);

			jsonObject.put(
				"unreadUserNotificationsCount", unreadUserNotificationsCount);

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
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						actionable, start, end);

			total =
				UserNotificationEventLocalServiceUtil.
					getDeliveredUserNotificationEventsCount(
						themeDisplay.getUserId(),
						UserNotificationDeliveryConstants.TYPE_WEBSITE,
						actionable);
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

		List<Long> newUserNotificationEventIds = new ArrayList<Long>();

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

		UserNotificationFeedEntry userNotificationFeedEntry =
			UserNotificationManagerUtil.interpret(
				StringPool.BLANK, userNotificationEvent,
				ServiceContextFactory.getInstance(resourceRequest));

		if (userNotificationFeedEntry == null) {
			return null;
		}

		LiferayPortletResponse liferayPortletResponse =
			(LiferayPortletResponse)resourceResponse;

		PortletURL actionURL = liferayPortletResponse.createActionURL(
			PortletKeys.NOTIFICATIONS);

		actionURL.setParameter(
			"userNotificationEventId",
			String.valueOf(userNotificationEvent.getUserNotificationEventId()));
		actionURL.setWindowState(WindowState.NORMAL);

		String actionDiv = StringPool.BLANK;
		String cssClass = StringPool.BLANK;
		String markAsReadIcon = StringPool.BLANK;

		if (userNotificationEvent.isActionRequired()) {
			actionURL.setParameter(
				"javax.portlet.action", "deleteUserNotificationEvent");

			actionDiv =
				StringUtil.replace(
					_DELETE_DIV, "[$DELETE_URL$]", actionURL.toString());
		}
		else {
			actionURL.setParameter("javax.portlet.action", "markAsRead");

			actionDiv =
				StringUtil.replace(
					_MARK_AS_READ_DIV,
					new String[] {
						"[$LINK$]", "[$MARK_AS_READ_URL$]"},
					new String[] {
						userNotificationFeedEntry.getLink(),
						actionURL.toString()});

			if (userNotificationEvent.isArchived()) {
				cssClass = "archived";
			}
			else {
				markAsReadIcon = StringUtil.replace(
					_MARK_AS_READ_ICON, "[$TITLE_MESSAGE$]",
					LanguageUtil.get(themeDisplay.getLocale(), "mark-as-read"));
			}
		}

		Portlet portlet =
			PortletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), userNotificationEvent.getType());

		String portletName = portlet.getDisplayName();
		String portletIcon = portlet.getContextPath() + portlet.getIcon();

		Format simpleDateFormat =
			FastDateFormatFactoryUtil.getSimpleDateFormat(
				"EEEE, MMMMM dd, yyyy 'at' h:mm a", themeDisplay.getLocale(),
				themeDisplay.getTimeZone());

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

		return StringUtil.replace(
			ContentUtil.get(PortletPropsValues.USER_NOTIFICATION_ENTRY),
			new String[] {
				"[$ACTION_DIV$]", "[$BODY$]", "[$CSS_CLASS$]",
				"[$MARK_AS_READ_ICON$]", "[$PORTLET_ICON$]", "[$PORTLET_NAME$]",
				"[$TIMESTAMP$]", "[$TIMETITLE$]", "[$USER_FULL_NAME$]",
				"[$USER_PORTRAIT_URL$]"},
			new String[] {
				actionDiv, userNotificationFeedEntry.getBody(), cssClass,
				markAsReadIcon, portletIcon, portletName,
				Time.getRelativeTimeDescription(
					userNotificationEvent.getTimestamp(),
					themeDisplay.getLocale(), themeDisplay.getTimeZone()),
				simpleDateFormat.format(userNotificationEvent.getTimestamp()),
				userFullName, userPortraitURL});
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

	private static final String _DELETE_DIV =
		"<div class=\"clearfix user-notification-delete\" data-deleteURL=\"" +
			"[$DELETE_URL$]\">";

	private static final String _MARK_AS_READ_DIV =
		"<div class=\"clearfix user-notification-link\" data-href=\"" +
			"[$LINK$]\" data-markAsReadURL=\"[$MARK_AS_READ_URL$]\">";

	private static final String _MARK_AS_READ_ICON =
		"<div class=\"mark-as-read\" title=\"[$TITLE_MESSAGE$]\"><i class=\"" +
			"icon-remove\"></i></div>";

}