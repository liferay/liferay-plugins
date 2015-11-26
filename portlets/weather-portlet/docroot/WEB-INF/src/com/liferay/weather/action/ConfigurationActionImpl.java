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

package com.liferay.weather.action;

import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.ValidatorException;

/**
 * @author Samuel Kong
 */
public class ConfigurationActionImpl extends DefaultConfigurationAction {

	@Override
	public void processAction(
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse)
		throws Exception {

		String apiKey = getParameter(actionRequest, "apiKey");

		setPreference(actionRequest, "apiKey", apiKey);

		String[] zips = StringUtil.split(
			getParameter(actionRequest, "zips"), StringPool.NEW_LINE);

		setPreference(actionRequest, "zips", zips);

		try {
			super.processAction(portletConfig, actionRequest, actionResponse);
		}
		catch (ValidatorException ve) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), ve);
		}
	}

}