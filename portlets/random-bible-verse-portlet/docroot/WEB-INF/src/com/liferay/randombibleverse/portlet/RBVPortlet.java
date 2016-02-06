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

package com.liferay.randombibleverse.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletConfig;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 */
public class RBVPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		PortletMode portletMode = actionRequest.getPortletMode();

		if (!portletMode.equals(PortletMode.EDIT)) {
			return;
		}

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (!cmd.equals(Constants.UPDATE)) {
			return;
		}

		String language = ParamUtil.getString(actionRequest, "language");

		PortletPreferences preferences = actionRequest.getPreferences();

		preferences.setValue("language", language);

		preferences.store();

		LiferayPortletConfig liferayPortletConfig =
			(LiferayPortletConfig)getPortletConfig();

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_PREFERENCES);
	}

}