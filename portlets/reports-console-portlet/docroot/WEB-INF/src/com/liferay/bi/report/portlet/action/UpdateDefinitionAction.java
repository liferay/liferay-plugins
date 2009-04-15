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

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.util.bridges.simplemvc.Action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * <a href="UpdateDefinition.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class UpdateDefinitionAction implements Action {
	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		// TBD Need to complete
		String contentType = actionRequest.getContentType();
	    	
		if ((contentType != null) &&
			(contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA))) {
//		    actionRequest = PortalUtil
//			.getUploadPortletRequest(actionRequest);
		}
	    
		long definitionId = ParamUtil.getLong(actionRequest, "definitionId");
		if (definitionId == -1) {
			//this should NEVER be -1...
			// TBD Need to do some processing here...to add error message?
			return false;
		}

		String definitionName = ParamUtil.getString(actionRequest, "definitionName");
		String description = ParamUtil.getString(actionRequest, "description");
		String datasourceName = ParamUtil.getString(
			actionRequest, "dataSourceName");
		String format = ParamUtil.getString(actionRequest, "format");

		try {
			ReportDefinition definition =
				ReportDefinitionLocalServiceUtil.updateReportDefinition(
					definitionId, description, datasourceName,
					ReportFormat.parse(format));
			actionRequest.setAttribute("currentDefinition", definition);
		}
		catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
			_log.error(e);
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException(
				"Unable to update definition: " + definitionName, e);
		}
		return false;
	}
	private static Log _log = LogFactoryUtil.getLog(UpdateDefinitionAction.class);
}
