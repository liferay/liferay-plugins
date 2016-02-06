/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplesignin.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.NoRedirectActionResponse;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.struts.PortletActionInvoker;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;

/**
 * @author Brian Wing Shun Chan
 */
public class SignInPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String className = "com.liferay.portlet.login.action.LoginAction";
		PortletConfig portletConfig = getPortletConfig();
		NoRedirectActionResponse noRedirectActionResponse =
			new NoRedirectActionResponse(actionResponse);

		try {
			PortletActionInvoker.processAction(
				className, portletConfig, actionRequest,
				noRedirectActionResponse);
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		String login = ParamUtil.getString(actionRequest, "login");
		String password = ParamUtil.getString(actionRequest, "password");
		String rememberMe = ParamUtil.getString(actionRequest, "rememberMe");

		if (Validator.isNull(noRedirectActionResponse.getRedirectLocation())) {
			actionResponse.setRenderParameter("login", login);
			actionResponse.setRenderParameter("rememberMe", rememberMe);
		}
		else {
			String redirect =
				PortalUtil.getPathMain() + "/portal/login?login=" + login +
					"&password=" + password + "&rememberMe=" + rememberMe;

			actionResponse.sendRedirect(redirect);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SignInPortlet.class);

}