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

package com.liferay.stocks.portlet;

import com.liferay.portal.kernel.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.stocks.util.PortletKeys;

import java.io.IOException;

import java.util.Arrays;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ValidatorException;

/**
 * @author Brian Wing Shun Chan
 */
public class StocksPortlet extends MVCPortlet {

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

		PortletPreferences portletPreferences = null;

		try {
			portletPreferences =
				PortletPreferencesFactoryUtil.getPortletPreferences(
					PortalUtil.getHttpServletRequest(actionRequest),
					PortletKeys.STOCKS);
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		String[] symbols = StringUtil.split(
			StringUtil.toUpperCase(
				ParamUtil.getString(actionRequest, "symbols")),
			StringPool.SPACE);

		Arrays.sort(symbols);

		portletPreferences.setValues("symbols", symbols);

		try {
			portletPreferences.store();
		}
		catch (ValidatorException ve) {
			SessionErrors.add(
				actionRequest, ValidatorException.class.getName(), ve);

			return;
		}

		SessionMessages.add(
			actionRequest,
			PortalUtil.getPortletId(actionRequest) +
				SessionMessages.KEY_SUFFIX_UPDATED_PREFERENCES);
	}

}