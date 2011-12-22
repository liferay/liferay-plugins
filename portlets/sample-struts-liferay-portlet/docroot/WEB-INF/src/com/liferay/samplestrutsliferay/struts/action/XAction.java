/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.samplestrutsliferay.struts.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.struts.PortletAction;
import com.liferay.samplestrutsliferay.SampleException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author Brian Wing Shun Chan
 */
public class XAction extends PortletAction {

	@Override
	public void processAction(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("processAction");
		}

		actionResponse.setRenderParameter("x_param", "x_value");

		String exception = actionRequest.getParameter("action_exception");

		if ((exception != null) && (exception.equals("true"))) {
			throw new SampleException();
		}

		setForward(actionRequest, "portlet.sample_struts_liferay_portlet.x");
	}

	@Override
	public ActionForward render(
			ActionMapping mapping, ActionForm form, PortletConfig portletConfig,
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws Exception {

		if (_log.isInfoEnabled()) {
			_log.info("render " + renderRequest.getParameter("x_param"));
		}

		String exception = renderRequest.getParameter("render_exception");

		if ((exception != null) && (exception.equals("true"))) {
			throw new SampleException();
		}

		return mapping.findForward(getForward(
			renderRequest, "portlet.sample_struts_liferay_portlet.x"));
	}

	private static Log _log = LogFactoryUtil.getLog(XAction.class);

}