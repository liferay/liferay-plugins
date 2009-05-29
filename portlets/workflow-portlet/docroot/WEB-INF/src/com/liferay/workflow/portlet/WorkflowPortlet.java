/**
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

package com.liferay.workflow.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.liferay.workflow.DefinitionXmlException;
import com.liferay.workflow.NoSuchDefinitionException;
import com.liferay.workflow.model.WorkflowInstance;
import com.liferay.workflow.service.WorkflowDefinitionServiceUtil;
import com.liferay.workflow.service.WorkflowInstanceServiceUtil;
import com.liferay.workflow.service.WorkflowTaskServiceUtil;

import java.io.IOException;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * <a href="WorkflowPortlet.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WorkflowPortlet extends MVCPortlet {

	public void addInstance(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long definitionId = ParamUtil.getLong(actionRequest, "definitionId");

		WorkflowInstance instance =
			WorkflowInstanceServiceUtil.addInstance(definitionId);

		SessionMessages.add(actionRequest, "request_processed");

		String redirect = ParamUtil.getString(actionRequest, "redirect");

		redirect += "&instanceId=" + instance.getInstanceId();

		actionResponse.sendRedirect(redirect);
	}

	public void addDefinition(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String xml = ParamUtil.getString(actionRequest, "xml");

		String[] communityPermissions = actionRequest.getParameterValues(
			"communityPermissions");
		String[] guestPermissions = actionRequest.getParameterValues(
			"guestPermissions");

		WorkflowDefinitionServiceUtil.addDefinition(
			xml, communityPermissions, guestPermissions);
	}

	public void signalInstance(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long instanceId = ParamUtil.getLong(actionRequest, "instanceId");
		long tokenId = ParamUtil.getLong(actionRequest, "tokenId");

		if (tokenId <= 0) {
			WorkflowInstanceServiceUtil.signalInstance(instanceId);
		}
		else {
			WorkflowInstanceServiceUtil.signalToken(instanceId, tokenId);
		}
	}

	public void updateTask(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long taskId = ParamUtil.getLong(actionRequest, "taskId");

		String transition = ParamUtil.getString(
			actionRequest, "taskTransition");

		Map errors = WorkflowTaskServiceUtil.updateTask(
			taskId, transition, actionRequest.getParameterMap());

		if (errors.size() > 0) {
			SessionErrors.add(
				actionRequest, WorkflowPortlet.class.getName(), errors);
		}
	}

	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			if (!callActionMethod(actionRequest, actionResponse)) {
				return;
			}

			if (SessionErrors.isEmpty(actionRequest)) {
				SessionMessages.add(actionRequest, "request_processed");
			}
			else {
				return;
			}

			String redirect = ParamUtil.getString(
				actionRequest, "assignmentsRedirect");

			if (Validator.isNull(redirect)) {
				redirect = ParamUtil.getString(actionRequest, "redirect");
			}

			actionResponse.sendRedirect(redirect);
		}
		catch (PortletException pe) {
			Throwable cause = pe.getCause();

			if (cause instanceof NoSuchDefinitionException ||
				cause instanceof PrincipalException) {

				SessionErrors.add(actionRequest, cause.getClass().getName());

				actionResponse.setRenderParameter("jspPage", "/error.jsp");
			}
			else if (cause instanceof DefinitionXmlException) {
				SessionErrors.add(actionRequest, cause.getClass().getName());

				PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			}
			else {
				throw pe;
			}
		}
	}

}