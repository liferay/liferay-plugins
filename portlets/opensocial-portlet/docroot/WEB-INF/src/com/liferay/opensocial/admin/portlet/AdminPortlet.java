/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.opensocial.admin.portlet;

import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.service.OAuthConsumerLocalServiceUtil;
import com.liferay.opensocial.service.permission.GadgetPermission;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.ActionKeys;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long groupId = themeDisplay.getScopeGroupId();

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetPermission.check(
			permissionChecker, groupId, gadgetId, ActionKeys.DELETE);

		GadgetLocalServiceUtil.deleteGadget(gadgetId);
	}

	public void deleteOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long groupId = themeDisplay.getScopeGroupId();

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetPermission.check(
			permissionChecker, groupId, gadgetId, ActionKeys.UPDATE);

		long oAuthConsumerId = ParamUtil.getLong(
			actionRequest, "oAuthConsumerId");

		OAuthConsumerLocalServiceUtil.deleteOAuthConsumer(oAuthConsumerId);
	}

	public void updateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			doAddGadget(actionRequest, actionResponse);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			doUpdateGadget(actionRequest, actionResponse);
		}
	}

	public void updateOAuthConsumers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long groupId = themeDisplay.getScopeGroupId();

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetPermission.check(
			permissionChecker, groupId, gadgetId, ActionKeys.UPDATE);

		ShindigUtil.updateOAuthConsumers(actionRequest, actionResponse);
	}

	protected Gadget doAddGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long groupId = themeDisplay.getScopeGroupId();

		GadgetPermission.check(
			permissionChecker, groupId, ActionKeys.PUBLISH_GADGET);

		String url = ParamUtil.getString(actionRequest, "url");
		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		return GadgetLocalServiceUtil.addGadget(
			themeDisplay.getCompanyId(), url, portletCategoryNames,
			serviceContext);
	}

	protected void doUpdateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long groupId = themeDisplay.getScopeGroupId();

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetPermission.check(
			permissionChecker, groupId, gadgetId, ActionKeys.UPDATE);

		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		GadgetLocalServiceUtil.updateGadget(gadgetId, portletCategoryNames);
	}

}