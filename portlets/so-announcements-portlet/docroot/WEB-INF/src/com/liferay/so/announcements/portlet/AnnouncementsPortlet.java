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

package com.liferay.so.announcements.portlet;

import com.liferay.announcements.kernel.exception.EntryContentException;
import com.liferay.announcements.kernel.exception.EntryDisplayDateException;
import com.liferay.announcements.kernel.exception.EntryExpirationDateException;
import com.liferay.announcements.kernel.exception.EntryTitleException;
import com.liferay.announcements.kernel.exception.EntryURLException;
import com.liferay.announcements.kernel.service.AnnouncementsEntryServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletURL;

/**
 * @author Raymond Augé
 * @author Evan Thibodeau
 */
public class AnnouncementsPortlet extends MVCPortlet {

	public void deleteEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		try {
			AnnouncementsEntryServiceUtil.deleteEntry(entryId);

			SessionMessages.add(actionRequest, "announcementDeleted");

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			jsonObject.put(
				"message",
				translate(
					actionRequest, "the-announcement-could-not-be-deleted"));
			jsonObject.put("success", false);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			String actionName = ParamUtil.getString(
				actionRequest, ActionRequest.ACTION_NAME);

			if (actionName.equals("deleteEntry")) {
				deleteEntry(actionRequest, actionResponse);
			}
			else if (actionName.equals("saveEntry")) {
				saveEntry(actionRequest, actionResponse);
			}
			else {
				super.processAction(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	public void saveEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			doSaveEntry(actionRequest, actionResponse);

			long entryId = ParamUtil.getLong(actionRequest, "entryId");

			if (entryId <= 0) {
				SessionMessages.add(actionRequest, "announcementAdded");
			}
			else {
				SessionMessages.add(actionRequest, "announcementUpdated");
			}

			LiferayPortletResponse liferayPortletResponse =
				(LiferayPortletResponse)actionResponse;

			PortletURL portletURL = liferayPortletResponse.createRenderURL();

			portletURL.setParameter("mvcPath", "/manage_entries.jsp");
			portletURL.setParameter(
				"distributionScope",
				ParamUtil.getString(actionRequest, "distributionScope"));

			jsonObject.put("redirect", portletURL.toString());

			jsonObject.put("success", true);
		}
		catch (Exception e) {
			String message = null;

			if (e instanceof EntryContentException) {
				message = "please-enter-valid-content";
			}
			else if (e instanceof EntryDisplayDateException) {
				message = "please-enter-a-valid-display-date";
			}
			else if (e instanceof EntryExpirationDateException) {
				message = "please-enter-a-valid-expiration-date";
			}
			else if (e instanceof EntryTitleException) {
				message = "please-enter-a-valid-title";
			}
			else if (e instanceof EntryURLException) {
				message = "please-enter-a-valid-url";
			}
			else {
				throw new PortletException(e);
			}

			SessionErrors.clear(actionRequest);

			jsonObject.put("message", translate(actionRequest, message));
			jsonObject.put("success", false);
		}

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	protected void doSaveEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long entryId = ParamUtil.getLong(actionRequest, "entryId");

		String[] distributionScopeParts = StringUtil.split(
			ParamUtil.getString(actionRequest, "distributionScope"));

		long classNameId = 0;
		long classPK = 0;

		if (distributionScopeParts.length == 2) {
			classNameId = GetterUtil.getLong(distributionScopeParts[0]);

			if (classNameId > 0) {
				classPK = GetterUtil.getLong(distributionScopeParts[1]);
			}
		}

		String title = ParamUtil.getString(actionRequest, "title");
		String content = ParamUtil.getString(actionRequest, "content");
		String url = ParamUtil.getString(actionRequest, "url");
		String type = ParamUtil.getString(actionRequest, "type");

		int displayDateMonth = ParamUtil.getInteger(
			actionRequest, "displayDateMonth");
		int displayDateDay = ParamUtil.getInteger(
			actionRequest, "displayDateDay");
		int displayDateYear = ParamUtil.getInteger(
			actionRequest, "displayDateYear");
		int displayDateHour = ParamUtil.getInteger(
			actionRequest, "displayDateHour");
		int displayDateMinute = ParamUtil.getInteger(
			actionRequest, "displayDateMinute");
		int displayDateAmPm = ParamUtil.getInteger(
			actionRequest, "displayDateAmPm");

		if (displayDateAmPm == Calendar.PM) {
			displayDateHour += 12;
		}

		boolean displayImmediately = ParamUtil.getBoolean(
			actionRequest, "displayImmediately");

		int expirationDateMonth = ParamUtil.getInteger(
			actionRequest, "expirationDateMonth");
		int expirationDateDay = ParamUtil.getInteger(
			actionRequest, "expirationDateDay");
		int expirationDateYear = ParamUtil.getInteger(
			actionRequest, "expirationDateYear");
		int expirationDateHour = ParamUtil.getInteger(
			actionRequest, "expirationDateHour");
		int expirationDateMinute = ParamUtil.getInteger(
			actionRequest, "expirationDateMinute");
		int expirationDateAmPm = ParamUtil.getInteger(
			actionRequest, "expirationDateAmPm");

		if (expirationDateAmPm == Calendar.PM) {
			expirationDateHour += 12;
		}

		int priority = ParamUtil.getInteger(actionRequest, "priority");
		boolean alert = ParamUtil.getBoolean(actionRequest, "alert");

		if (entryId <= 0) {

			// Add entry

			AnnouncementsEntryServiceUtil.addEntry(
				themeDisplay.getPlid(), classNameId, classPK, title, content,
				url, type, displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, displayImmediately,
				expirationDateMonth, expirationDateDay, expirationDateYear,
				expirationDateHour, expirationDateMinute, priority, alert);
		}
		else {

			// Update entry

			AnnouncementsEntryServiceUtil.updateEntry(
				entryId, title, content, url, type, displayDateMonth,
				displayDateDay, displayDateYear, displayDateHour,
				displayDateMinute, displayImmediately, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, priority);
		}
	}

}