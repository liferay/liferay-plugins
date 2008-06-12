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

package com.liferay.wol.meetups.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;
import com.liferay.wol.service.MeetupsEntryLocalServiceUtil;
import com.liferay.wol.service.MeetupsRegistrationLocalServiceUtil;
import com.liferay.wol.util.WOLConstants;

import java.io.File;
import java.io.IOException;

import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="MeetupsPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class MeetupsPortlet extends JSPPortlet {

	public void processAction(ActionRequest req, ActionResponse res)
		throws IOException, PortletException {

		try {
			String actionName = ParamUtil.getString(
				req, ActionRequest.ACTION_NAME);

			if (actionName.equals("deleteMeetupsEntry")) {
				deleteMeetupsEntry(req);
			}
			else if (actionName.equals("updateMeetupsEntry")) {
				updateMeetupsEntry(req);
			}
			else if (actionName.equals("updateMeetupsRegistration")) {
				updateMeetupsRegistration(req);
			}

			if (Validator.isNull(actionName)) {
				return;
			}

			if (SessionErrors.isEmpty(req)) {
				SessionMessages.add(req, "request_processed");
			}

			String redirect = ParamUtil.getString(req, "redirect");

			res.sendRedirect(redirect);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

	protected void deleteMeetupsEntry(ActionRequest req) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!UserLocalServiceUtil.hasOrganizationUser(
				WOLConstants.ORGANIZATION_LIFERAY_INC_ID,
				themeDisplay.getUserId())) {

			return;
		}

		long meetupsEntryId = ParamUtil.getLong(req, "meetupsEntryId");

		MeetupsEntryLocalServiceUtil.deleteMeetupsEntry(meetupsEntryId);
	}

	protected void updateMeetupsEntry(ActionRequest req) throws Exception {
		UploadPortletRequest uploadReq = PortalUtil.getUploadPortletRequest(
			req);

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadReq.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!UserLocalServiceUtil.hasOrganizationUser(
				WOLConstants.ORGANIZATION_LIFERAY_INC_ID,
				themeDisplay.getUserId())) {

			return;
		}

		long meetupsEntryId = ParamUtil.getLong(uploadReq, "meetupsEntryId");

		String title = ParamUtil.getString(uploadReq, "title");
		String description = ParamUtil.getString(uploadReq, "description");

		int startDateMonth = ParamUtil.getInteger(uploadReq, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(uploadReq, "startDateDay");
		int startDateYear = ParamUtil.getInteger(uploadReq, "startDateYear");
		int startDateHour = ParamUtil.getInteger(uploadReq, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			uploadReq, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(uploadReq, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int endDateMonth = ParamUtil.getInteger(uploadReq, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(uploadReq, "endDateDay");
		int endDateYear = ParamUtil.getInteger(uploadReq, "endDateYear");
		int endDateHour = ParamUtil.getInteger(uploadReq, "endDateHour");
		int endDateMinute = ParamUtil.getInteger(uploadReq, "endDateMinute");
		int endDateAmPm = ParamUtil.getInteger(uploadReq, "endDateAmPm");

		if (endDateAmPm == Calendar.PM) {
			endDateHour += 12;
		}

		int totalAttendees = ParamUtil.getInteger(uploadReq, "totalAttendees");
		int maxAttendees = ParamUtil.getInteger(uploadReq, "maxAttendees");
		double price = ParamUtil.getDouble(uploadReq, "price");

		File file = uploadReq.getFile("fileName");
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

	protected void updateMeetupsRegistration(ActionRequest req)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)req.getAttribute(
			WebKeys.THEME_DISPLAY);

		long meetupsEntryId = ParamUtil.getLong(req, "meetupsEntryId");
		int status = ParamUtil.getInteger(req, "status");
		String comments = ParamUtil.getString(req, "comments");

		MeetupsRegistrationLocalServiceUtil.updateMeetupsRegistration(
			themeDisplay.getUserId(), meetupsEntryId, status, comments);
	}

	private static Log _log = LogFactory.getLog(MeetupsPortlet.class);

}