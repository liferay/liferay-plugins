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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.servlet.http.HttpServletResponse;

import com.liferay.bi.report.NoSuchRequestedReportException;
import com.liferay.bi.report.model.RequestedReport;
import com.liferay.bi.report.service.RequestedReportLocalServiceUtil;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.simplemvc.Action;
import com.liferay.util.servlet.ServletResponseUtil;

/**
 * <a href="DeleteDefinitionAction.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 * @author Gavin Wan
 */
public class GetReportAction implements Action {
	
	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {
		//TBD at renderAction
		long requestId = ParamUtil.getLong(actionRequest, "requestId");

		if (requestId == -1) {
			SessionErrors.add(
				actionRequest, NoSuchRequestedReportException.class.getName());
			return false;
		}
		try {
			RequestedReport requestedReport =
				RequestedReportLocalServiceUtil.getRequestedReport(requestId);

			HttpServletResponse response =
				PortalUtil.getHttpServletResponse(actionResponse);

			String[] existingFiles = requestedReport.getAttachmentsFiles();

			byte[] report = null;
			String fileName = null;
			for (String existingFile : existingFiles) {
				fileName =
					StringUtil.extractLast(existingFile, StringPool.SLASH);
				report =
					DLServiceUtil.getFile(
						requestedReport.getCompanyId(), 0, existingFile);
				break;
			}
			ServletResponseUtil.sendFile(response, fileName, report);
			return true;
		}
		catch (Exception e) {
			_log.error(e);
			throw new PortletException("Unable to get the report", e);
		}

	}
	private static Log _log = LogFactoryUtil.getLog(GetReportAction.class);
}
