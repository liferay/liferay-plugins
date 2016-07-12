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

package com.liferay.sync.devices.portlet;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.sync.admin.portlet.AdminPortlet;
import com.liferay.sync.model.SyncDevice;
import com.liferay.sync.service.SyncDeviceLocalServiceUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Jonathan McCann
 */
public class DevicesPortlet extends AdminPortlet {

	public void deleteDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		checkSyncDeviceUserId(syncDeviceId, themeDisplay.getUserId());

		super.deleteDevice(actionRequest, actionResponse);
	}

	public void updateDevice(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long syncDeviceId = ParamUtil.getLong(actionRequest, "syncDeviceId");

		checkSyncDeviceUserId(syncDeviceId, themeDisplay.getUserId());

		super.updateDevice(actionRequest, actionResponse);
	}

	protected void checkSyncDeviceUserId(long syncDeviceId, long userId)
		throws Exception {

		SyncDevice syncDevice = SyncDeviceLocalServiceUtil.getSyncDevice(
			syncDeviceId);

		if (userId != syncDevice.getUserId()) {
			throw new PrincipalException();
		}
	}

}