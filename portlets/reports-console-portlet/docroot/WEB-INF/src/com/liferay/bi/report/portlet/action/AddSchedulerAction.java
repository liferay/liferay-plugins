/*
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

package com.liferay.bi.report.portlet.action;

import java.util.Calendar;
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.bi.report.service.RequestedReportLocalServiceUtil;
import com.liferay.bi.report.util.ReportUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.simplemvc.Action;

/**
 * <a href="AddSchedulerAction.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class AddSchedulerAction implements Action {

	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		long definitionId = ParamUtil.getLong(actionRequest, "definitionId");

		int recurrenceType =
			ParamUtil.getInteger(actionRequest, "recurrenceType");

		String description = ParamUtil.getString(actionRequest, "description");
		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long userId = themeDisplay.getUserId();
		try {
			Calendar startCal =
				ReportUtil.getDate(actionRequest, "schedulerStartDate", false);

			String cronText =
				ReportUtil.getCronText(
					actionRequest, startCal, false, recurrenceType);

			Date schedulerEndDate = null;

			int endDateType =
				ParamUtil.getInteger(actionRequest, "endDateType");

			if (endDateType == 1) {
				Calendar endCal =
					ReportUtil.getDate(actionRequest, "schedulerEndDate", false);

				schedulerEndDate = endCal.getTime();
			}

			RequestedReportLocalServiceUtil.addScheduler(
				definitionId, cronText, startCal.getTime(), schedulerEndDate,
				description, userId);
			SessionMessages.add(actionRequest, "request_processed");

		}
		catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException(
				"Unable to add the definition to schduler. ", e);
		}
		return false;
	}
	
	private static Log _log = LogFactoryUtil.getLog(AddSchedulerAction.class);
}
