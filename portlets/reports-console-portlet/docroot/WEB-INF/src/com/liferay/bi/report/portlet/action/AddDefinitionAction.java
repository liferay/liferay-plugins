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
import com.liferay.bi.report.model.impl.ReportDefinitionImpl;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
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

		UploadPortletRequest uploadRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		actionResponse.setRenderParameter("jspPage", ParamUtil.getString(
			uploadRequest, "jspPage"));

		String definitionName =
			ParamUtil.getString(uploadRequest, "definitionName");
		if (Validator.isNull(definitionName)) {
			SessionErrors.add(
				actionRequest, DefinitionNameException.class.getName());
		}

		String format = ParamUtil.getString(uploadRequest, "format");
		ReportFormat reportFormat = null;
		try {
			reportFormat = ReportFormat.parse(format);
		}
		catch (IllegalArgumentException e) {
			SessionErrors.add(
				actionRequest, DefinitionFormatException.class.getName());
		}

		String fileName = uploadRequest.getFileName("msgFile");
		File file = uploadRequest.getFile("msgFile");
		if (Validator.isNull(fileName) || file.length() <= 0) {
			fileName = StringPool.BLANK;
			SessionErrors.add(
				actionRequest, DefinitionFileException.class.getName());
		}

		try {
			String description =
				ParamUtil.getString(uploadRequest, "description");
			String datasourceName =
				ParamUtil.getString(uploadRequest, "dataSourceName");
			String reportParameters =
				ParamUtil.getString(uploadRequest, "reportParameters");

			ThemeDisplay themeDisplay =
				(ThemeDisplay) uploadRequest.getAttribute(WebKeys.THEME_DISPLAY);
			ReportDefinition definition = null;
			if (SessionErrors.isEmpty(actionRequest)) {
				definition =
					ReportDefinitionLocalServiceUtil.addReportDefinition(
						themeDisplay.getCompanyId(),
						themeDisplay.getScopeGroupId(),
						themeDisplay.getUserId(), definitionName, description,
						datasourceName, reportFormat, fileName, file,
						reportParameters);
				actionRequest.setAttribute("fileName", fileName);
				SessionMessages.add(actionRequest, "request_processed");
			}else{
				definition = new ReportDefinitionImpl();
				definition.setDefinitionName(definitionName);
				definition.setDescription(description);
				definition.setDataSourceName(datasourceName);
				definition.setReportFormat(format.toString());
				definition.setReportParameters(reportParameters);
			}

			uploadRequest.setAttribute("definition", definition);
		}
		catch (PortalException e) {
			_log.error(e);
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException("Unable to create new definition ", e);
		}
		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(AddDefinitionAction.class);
}
