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

package com.liferay.wol.meetups.portlet;

import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wol.service.MeetupsEntryLocalServiceUtil;
import com.liferay.wol.service.MeetupsRegistrationLocalServiceUtil;

import java.io.File;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * <a href="MeetupsPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsPortlet extends JSPPortlet {

	public void deleteMeetupsEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin(themeDisplay.getCompanyId())) {
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

		if (!permissionChecker.isCompanyAdmin(themeDisplay.getCompanyId())) {
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