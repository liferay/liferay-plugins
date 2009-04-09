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

import com.liferay.util.bridges.simplemvc.Action;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.PortalException;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.model.impl.ReportDefinitionModelImpl;
import com.liferay.bi.report.model.impl.ReportDefinitionImpl;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * <a href="AddDefinitionAction.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class AddDefinitionAction implements Action {
	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {
	    
		UploadPortletRequest uploadRequest = PortalUtil
			.getUploadPortletRequest(actionRequest);
	    
		String name = ParamUtil.getString(uploadRequest, "definitionName");
		String format = ParamUtil.getString(uploadRequest, "format");
		
		// TBD Need to set some validation...for instance name, format cannot be nul...
		String description = ParamUtil.getString(uploadRequest, "description");
		String datasourceName = ParamUtil.getString(uploadRequest, "dataSourceName");

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadRequest.getAttribute(WebKeys.THEME_DISPLAY);

		try {
			ReportDefinition definition =
				ReportDefinitionLocalServiceUtil.addReportDefinition(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
					themeDisplay.getUserId(), name, description, datasourceName,
					ReportFormat.parse(format));
			uploadRequest.setAttribute("currentDefinition", definition);
			return true;
		}
		catch (PortalException e) {
			SessionErrors.add(uploadRequest, e.getClass().getName());
			return false;
		}
		catch (SystemException e) {
			throw new PortletException(
				"Unable to create new definition: " + name, e);
		}
	}
}
