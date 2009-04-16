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

import com.liferay.bi.report.model.ReportDefinition;
import com.liferay.bi.report.service.ReportDefinitionLocalServiceUtil;
import com.liferay.documentlibrary.service.DLServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.util.bridges.simplemvc.Action;

/**
 * <a href="ViewDefinitionAction.java.html"><b><i>View Source</i></b></a>
 * 
 * @author Michael C. Han
 */
public class ViewDefinitionAction implements Action {

	public boolean processAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			long definitionId =
				ParamUtil.getLong(actionRequest, "definitionId", -1);
			if (definitionId > 0) {
				ReportDefinition definition =
					ReportDefinitionLocalServiceUtil.getReportDefinition(definitionId);

				String[] existingAttachments =
					DLServiceUtil.getFileNames(
						definition.getCompanyId(), CompanyConstants.SYSTEM,
						definition.getAttachmentsDir());

				String fileName = StringPool.NULL;
				if (existingAttachments.length != 0) {
					fileName =
						StringUtil.extractLast(
							existingAttachments[0], StringPool.SLASH);
				}

				actionRequest.setAttribute("definition", definition);
				actionRequest.setAttribute("fileName", fileName);
			}
		}
		catch (PortalException e) {
			SessionErrors.add(actionRequest, e.getClass().getName());
			_log.error(e);
			return true;
		}
		catch (SystemException e) {
			_log.error(e);
			throw new PortletException("No such a definition", e);
		}

		//TBD should not be hard coded
		//actionResponse.setRenderParameter("jspPage", ParamUtil.getString(uploadRequest, "jspPage"));
		actionResponse.setRenderParameter(
			"jspPage", "/definition/edit_definition.jsp");
		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(ViewDefinitionAction.class);
}
