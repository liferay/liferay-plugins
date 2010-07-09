/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.portlet;

import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Michael Young
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetLocalServiceUtil.deleteGadget(gadgetId);
	}

	public void updateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		try {
			doUpdateGadget(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass().getName());
		}
	}

	protected void checkPermissions(PortletRequest portletRequest)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		if (!permissionChecker.isCompanyAdmin()) {
			throw new PrincipalException();
		}
	}

	protected void doUpdateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		String name = ParamUtil.getString(actionRequest, "name");
		String url = ParamUtil.getString(actionRequest, "url");

		if (gadgetId <= 0) {
			GadgetLocalServiceUtil.addGadget(
				themeDisplay.getCompanyId(), name, url);
		}
		else {
			GadgetLocalServiceUtil.updateGadget(
				themeDisplay.getCompanyId(), gadgetId, name);
		}
	}

}