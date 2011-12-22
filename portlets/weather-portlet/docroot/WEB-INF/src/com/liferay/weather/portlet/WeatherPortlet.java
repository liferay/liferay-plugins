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

package com.liferay.weather.portlet;

import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class WeatherPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		updatePreferences(actionRequest, actionResponse);
	}

	protected void updatePreferences(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		PortletPreferences preferences = actionRequest.getPreferences();

		String[] zips = StringUtil.split(
			ParamUtil.getString(actionRequest, "zips"), "\n");

		boolean fahrenheit = ParamUtil.get(actionRequest, "fahrenheit", true);

		preferences.setValues("zips", zips);
		preferences.setValue("fahrenheit", String.valueOf(fahrenheit));

		try {
			preferences.store();
		}
		catch (ValidatorException ve) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), ve);

			return;
		}

		PortletConfig portletConfig = getPortletConfig();

		SessionMessages.add(
			actionRequest,
			portletConfig.getPortletName() +
				SessionMessages.KEY_SUFFIX_UPDATED_PREFERENCES);
	}

}