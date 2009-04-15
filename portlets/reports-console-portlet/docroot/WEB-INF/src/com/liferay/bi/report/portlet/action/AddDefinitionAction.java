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

import java.io.File;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.bi.report.DefinitionFileException;
import com.liferay.bi.report.DefinitionFormatException;
import com.liferay.bi.report.DefinitionNameException;
import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.simplemvc.Action;

/**
 * <a href="AddDefinitionAction.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 */
public class AddDefinitionAction implements Action {

	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			UploadPortletRequest uploadRequest =
				PortalUtil.getUploadPortletRequest(actionRequest);

			String definitionName =
				ParamUtil.getString(uploadRequest, "definitionName");
			if (Validator.isNull(definitionName)) {
				throw new DefinitionNameException();
			}

			String format = ParamUtil.getString(uploadRequest, "format");
			ReportFormat reportFormat = null;
			try {
				reportFormat = ReportFormat.parse(format);
			}
			catch (IllegalArgumentException e) {
				throw new DefinitionFormatException();
			}

			String fileName = uploadRequest.getFileName("msgFile");
			if (Validator.isNull(definitionName)) {
				throw new DefinitionFileException();
			}

			String description =
				ParamUtil.getString(uploadRequest, "description");
			String datasourceName =
				ParamUtil.getString(uploadRequest, "dataSourceName");
			File file = uploadRequest.getFile("msgFile");
			String parameters =
				ParamUtil.getString(uploadRequest, "reportParameters");

			ThemeDisplay themeDisplay =
				(ThemeDisplay) uploadRequest.getAttribute(WebKeys.THEME_DISPLAY);

			ReportDefinition definition =
				ReportDefinitionLocalServiceUtil.addReportDefinition(
					themeDisplay.getCompanyId(),
					themeDisplay.getScopeGroupId(), themeDisplay.getUserId(),
					definitionName, description, datasourceName, reportFormat,
					fileName, file, parameters);
			
			actionRequest.setAttribute("existingName", fileName);
			uploadRequest.setAttribute("definition", definition);
		}
		catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException("Unable to create new definition ", e);
		}
		actionResponse.setRenderParameter(
			"jspPage", "/definition/edit_definition.jsp");
		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(AddDefinitionAction.class);
}
