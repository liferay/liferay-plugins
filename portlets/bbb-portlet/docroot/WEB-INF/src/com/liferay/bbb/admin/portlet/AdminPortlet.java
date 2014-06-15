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

package com.liferay.bbb.admin.portlet;

import com.liferay.bbb.model.BBBServer;
import com.liferay.bbb.service.BBBServerLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Shinn Lok
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteBBBServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long bbbServerId = ParamUtil.getLong(actionRequest, "bbbServerId");

		BBBServerLocalServiceUtil.deleteBBBServer(bbbServerId);
	}

	public void updateBBBServer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long bbbServerId = ParamUtil.getLong(actionRequest, "bbbServerId");

		String name = ParamUtil.getString(actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");
		String secret = ParamUtil.getString(actionRequest, "secret");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			BBBServer.class.getName(), actionRequest);

		if (bbbServerId <= 0) {
			BBBServerLocalServiceUtil.addBBBServer(
				themeDisplay.getUserId(), name, url, secret, serviceContext);
		}
		else {
			BBBServerLocalServiceUtil.updateBBBServer(
				bbbServerId, name, url, secret, serviceContext);
		}
	}

}