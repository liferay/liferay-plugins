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
import com.liferay.bi.report.NoSuchDefinitionException;
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
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.simplemvc.Action;

/**
 * <a href="UpdateDefinition.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 */
public class UpdateDefinitionAction implements Action {

	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		String contentType = actionRequest.getContentType();
		String jspPage = null;

		try {
			if ((contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA))) {
				jspPage =
					performUpdateWithFileUpdated(actionRequest, actionResponse);
			}
			else {
				jspPage = performUpdate(actionRequest, actionResponse);
			}
		}
		catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException("Unable to update definition: ", e);
		}

		actionResponse.setRenderParameter("jspPage", jspPage);
		return false;
	}

	private String performUpdate(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		String jspPage = ParamUtil.getString(actionRequest, "jspPage");

		long definitionId = ParamUtil.getLong(actionRequest, "definitionId");
		if (definitionId <= 0) {
			SessionErrors.add(
				actionRequest, NoSuchDefinitionException.class.getName());
		}
		String definitionName =
			ParamUtil.getString(actionRequest, "definitionName");
		if (Validator.isNull(definitionName)) {
			SessionErrors.add(
				actionRequest, DefinitionNameException.class.getName());
		}

		String format = ParamUtil.getString(actionRequest, "format");
		ReportFormat reportFormat = null;
		try {
			reportFormat = ReportFormat.parse(format);
		}
		catch (IllegalArgumentException e) {
			SessionErrors.add(
				actionRequest, DefinitionFormatException.class.getName());
		}

		String description = ParamUtil.getString(actionRequest, "description");
		String datasourceName =
			ParamUtil.getString(actionRequest, "dataSourceName");
		String reportParameters =
			ParamUtil.getString(actionRequest, "reportParameters");

		ReportDefinition definition = null;
		if (SessionErrors.isEmpty(actionRequest)) {
			definition =
				ReportDefinitionLocalServiceUtil.updateReportDefinition(
					definitionId, definitionName, description, datasourceName,
					reportFormat, reportParameters);
			SessionMessages.add(actionRequest, "request_processed");
		}
		else {
			definition = new ReportDefinitionImpl();
			definition.setDefinitionId(definitionId);
			definition.setDefinitionName(definitionName);
			definition.setDescription(description);
			definition.setDataSourceName(datasourceName);
			definition.setReportFormat(format.toString());
			definition.setReportParameters(reportParameters);
		}

		actionRequest.setAttribute("fileName", ParamUtil.getString(
			actionRequest, "fileName"));
		actionRequest.setAttribute("definition", definition);
		return jspPage;
	}

	private String performUpdateWithFileUpdated(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		UploadPortletRequest uploadRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);
		String jspPage = ParamUtil.getString(uploadRequest, "jspPage");

		long definitionId = ParamUtil.getLong(uploadRequest, "definitionId");
		if (definitionId <= 0) {
			SessionErrors.add(
				actionRequest, NoSuchDefinitionException.class.getName());
		}

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

		String description = ParamUtil.getString(uploadRequest, "description");
		String datasourceName =
			ParamUtil.getString(uploadRequest, "dataSourceName");

		String reportParameters =
			ParamUtil.getString(uploadRequest, "reportParameters");

		ReportDefinition definition = null;
		if (SessionErrors.isEmpty(actionRequest)) {
			definition =
				ReportDefinitionLocalServiceUtil.updateReportDefinition(
					definitionId, definitionName, description, datasourceName,
					reportFormat, reportParameters, fileName, file);
			SessionMessages.add(actionRequest, "request_processed");
		}
		else {
			definition = new ReportDefinitionImpl();
			definition.setDefinitionId(definitionId);
			definition.setDefinitionName(definitionName);
			definition.setDescription(description);
			definition.setDataSourceName(datasourceName);
			definition.setReportFormat(format.toString());
			definition.setReportParameters(reportParameters);
		}

		uploadRequest.setAttribute("fileName", fileName);
		uploadRequest.setAttribute("definition", definition);
		return jspPage;

	}
	private static Log _log =
		LogFactoryUtil.getLog(UpdateDefinitionAction.class);
}
