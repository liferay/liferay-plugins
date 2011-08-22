/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.meetups.portlet;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.socialnetworking.service.MeetupsEntryLocalServiceUtil;
import com.liferay.socialnetworking.service.MeetupsRegistrationLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsPortlet extends MVCPortlet {

	public void deleteMeetupsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			return;
		}

		long meetupsEntryId = ParamUtil.getLong(
			actionRequest, "meetupsEntryId");

		MeetupsEntryLocalServiceUtil.deleteMeetupsEntry(meetupsEntryId);
	}

	public void updateMeetupsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			return;
		}

		long meetupsEntryId = ParamUtil.getLong(
			uploadPortletRequest, "meetupsEntryId");

		String title = ParamUtil.getString(uploadPortletRequest, "title");
		String description = ParamUtil.getString(
			uploadPortletRequest, "description");

		int startDateMonth = ParamUtil.getInteger(
			uploadPortletRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(
			uploadPortletRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			uploadPortletRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			uploadPortletRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			uploadPortletRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			uploadPortletRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int endDateMonth = ParamUtil.getInteger(
			uploadPortletRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(
			uploadPortletRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(
			uploadPortletRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(
			uploadPortletRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			uploadPortletRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(
			uploadPortletRequest, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}

		int totalAttendees = ParamUtil.getInteger(
			uploadPortletRequest, "totalAttendees");
		int maxAttendees = ParamUtil.getInteger(
			uploadPortletRequest, "maxAttendees");
		double price = ParamUtil.getDouble(uploadPortletRequest, "price");

		File file = uploadPortletRequest.getFile("fileName");
		byte[] bytes = FileUtil.getBytes(file);

		if (meetupsEntryId <= 0) {
			MeetupsEntryLocalServiceUtil.addMeetupsEntry(
				themeDisplay.getUserId(), title, description, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, endDateHour,
				endDateMinute, totalAttendees, maxAttendees, price, bytes);
		}
		else {
			MeetupsEntryLocalServiceUtil.updateMeetupsEntry(
				themeDisplay.getUserId(), meetupsEntryId, title, description,
				startDateMonth, startDateDay, startDateYear, startDateHour,
				startDateMinute, endDateMonth, endDateDay, endDateYear,
				endDateHour, endDateMinute, totalAttendees, maxAttendees,
				price, bytes);
		}
	}

	public void updateMeetupsRegistration(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long meetupsEntryId = ParamUtil.getLong(
			actionRequest, "meetupsEntryId");
		int status = ParamUtil.getInteger(actionRequest, "status");
		String comments = ParamUtil.getString(actionRequest, "comments");

		MeetupsRegistrationLocalServiceUtil.updateMeetupsRegistration(
			themeDisplay.getUserId(), meetupsEntryId, status, comments);
	}

}