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
import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import com.liferay.bi.report.DefinitionFileException;
import com.liferay.bi.report.DefinitionNameException;
import com.liferay.bi.report.NoSuchDefinitionException;
import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bi.reporting.ReportFormat;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CompanyConstants;
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

		String jspPageParameter = null;
		try {
			if ((contentType.startsWith(ContentTypes.MULTIPART_FORM_DATA))) {
				jspPageParameter =
					performUpdateWithFileUpdated(actionRequest, actionResponse);
			}
			else {
				jspPageParameter =
					performUpdate(actionRequest, actionResponse);
			}
		}
		catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException("Unable to update definition: ", e);
		}

		// TBD This should not be hard coded...
		actionResponse.setRenderParameter("jspPage", jspPageParameter);
			//"jspPage", "/definition/edit_definition.jsp");
		return false;
	}

	private String performUpdate(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		long definitionId = ParamUtil.getLong(actionRequest, "definitionId");
		if (definitionId <= 0) {
			throw new NoSuchDefinitionException();
		}
		String definitionName =
			ParamUtil.getString(actionRequest, "definitionName");
		if (Validator.isNull(definitionName)) {
			throw new DefinitionNameException();
		}

		String description = ParamUtil.getString(actionRequest, "description");
		String datasourceName =
			ParamUtil.getString(actionRequest, "dataSourceName");
		String format = ParamUtil.getString(actionRequest, "format");
		String reportParameters =
			ParamUtil.getString(actionRequest, "reportParameters");
		ReportDefinition definition =
			ReportDefinitionLocalServiceUtil.updateReportDefinition(
				definitionId, definitionName, description, datasourceName,
				ReportFormat.parse(format), reportParameters);

		actionRequest.setAttribute(
			"fileName", ParamUtil.getString(actionRequest, "fileName"));
		actionRequest.setAttribute("definition", definition);
		return ParamUtil.getString(actionRequest, "jspPage");
	}

	private String performUpdateWithFileUpdated(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortalException, SystemException {

		UploadPortletRequest uploadRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		long definitionId = ParamUtil.getLong(uploadRequest, "definitionId");
		if (definitionId <= 0) {
			throw new NoSuchDefinitionException();
		}
		ReportDefinition definition =
			ReportDefinitionLocalServiceUtil.getReportDefinition(definitionId);

		String definitionName =
			ParamUtil.getString(uploadRequest, "definitionName");
		if (Validator.isNull(definitionName)) {
			throw new DefinitionNameException();
		}

		String fileName = uploadRequest.getFileName("msgFile");

		// TBD Any type of file operation should really be handle in the service layer
		if (Validator.isNull(definitionName)) {
			throw new DefinitionFileException();
		}
		String[] existingAttachments =
			DLServiceUtil.getFileNames(
				definition.getCompanyId(), CompanyConstants.SYSTEM,
				definition.getAttachmentsDir());
		for (String attachment : existingAttachments) {
			DLServiceUtil.deleteFile(
				definition.getCompanyId(), CompanyConstants.SYSTEM_STRING,
				CompanyConstants.SYSTEM, attachment);
		}

		File file = uploadRequest.getFile("msgFile");
		String portletId = CompanyConstants.SYSTEM_STRING;
		long repositoryId = 0;
		long fileEntryId = 0;
		String properties = StringPool.BLANK;
		Date modifiedDate = new Date();
		String[] tagsCategories = new String[0];
		String[] tagsEntries = new String[0];

		DLServiceUtil.addFile(
			definition.getCompanyId(), portletId, definition.getGroupId(),
			repositoryId, definition.getAttachmentsDir() + StringPool.SLASH +
				fileName, fileEntryId, properties, modifiedDate,
			tagsCategories, tagsEntries, file);

		String description = ParamUtil.getString(uploadRequest, "description");
		String datasourceName =
			ParamUtil.getString(uploadRequest, "dataSourceName");
		String format = ParamUtil.getString(uploadRequest, "format");
		String reportParameters =
			ParamUtil.getString(uploadRequest, "reportParameters");

		definition.setDefinitionName(definitionName);
		definition.setDescription(description);
		definition.setDataSourceName(datasourceName);
		definition.setReportFormat(format.toString());
		definition.setReportParameters(reportParameters);

		ReportDefinitionLocalServiceUtil.updateReportDefinition(definition);

		uploadRequest.setAttribute("fileName", fileName);
		uploadRequest.setAttribute("definition", definition);
		return ParamUtil.getString(uploadRequest, "jspPage");

	}

	private static Log _log =
		LogFactoryUtil.getLog(UpdateDefinitionAction.class);
}
