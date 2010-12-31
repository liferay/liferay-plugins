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

		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(
			actionRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			return;
		}

		long meetupsEntryId = ParamUtil.getLong(
			uploadRequest, "meetupsEntryId");

		String title = ParamUtil.getString(uploadRequest, "title");
		String description = ParamUtil.getString(uploadRequest, "description");

		int startDateMonth = ParamUtil.getInteger(
			uploadRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(uploadRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			uploadRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			uploadRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			uploadRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			uploadRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int endDateMonth = ParamUtil.getInteger(uploadRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(uploadRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(uploadRequest, "endDateYear");
		int endDateHour = ParamUtil.getInteger(uploadRequest, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(
			uploadRequest, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(uploadRequest, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}

		int totalAttendees = ParamUtil.getInteger(
			uploadRequest, "totalAttendees");
		int maxAttendees = ParamUtil.getInteger(uploadRequest, "maxAttendees");
		double price = ParamUtil.getDouble(uploadRequest, "price");

		File file = uploadRequest.getFile("fileName");
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