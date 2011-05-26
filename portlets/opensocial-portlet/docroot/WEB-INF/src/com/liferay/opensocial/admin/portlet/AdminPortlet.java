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
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetLocalServiceUtil.deleteGadget(gadgetId);
	}

	public void deleteOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		long oAuthConsumerId = ParamUtil.getLong(
			actionRequest, "oAuthConsumerId");

		OAuthConsumerLocalServiceUtil.deleteOAuthConsumer(oAuthConsumerId);
	}

	public void updateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			doAddGadget(actionRequest, actionResponse);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			doUpdateGadget(actionRequest, actionResponse);
		}
	}

	public void updateOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			doAddOAuthConsumer(actionRequest, actionResponse);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			doUpdateOAuthConsumer(actionRequest, actionResponse);
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

	protected Gadget doAddGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String url = ParamUtil.getString(actionRequest, "url");
		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		return GadgetLocalServiceUtil.addGadget(
			themeDisplay.getCompanyId(), url, portletCategoryNames,
			serviceContext);
	}

	protected void doAddOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");
		String serviceName = ParamUtil.getString(actionRequest, "serviceName");
		String consumerKey = ParamUtil.getString(actionRequest, "consumerKey");
		String consumerSecret = ParamUtil.getString(
			actionRequest, "consumerSecret");
		String keyType = ParamUtil.getString(actionRequest, "keyType");

		OAuthConsumerLocalServiceUtil.addOAuthConsumer(
			themeDisplay.getCompanyId(), gadgetId, serviceName, consumerKey,
			consumerSecret, keyType);
	}

	protected void doUpdateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		GadgetLocalServiceUtil.updateGadget(gadgetId, portletCategoryNames);
	}

	protected void doUpdateOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long oAuthConsumerId = ParamUtil.getLong(
			actionRequest, "oAuthConsumerId");

		String consumerKey = ParamUtil.getString(actionRequest, "consumerKey");
		String consumerSecret = ParamUtil.getString(
			actionRequest, "consumerSecret");
		String keyType = ParamUtil.getString(actionRequest, "keyType");

		OAuthConsumerLocalServiceUtil.updateOAuthConsumer(
			oAuthConsumerId, consumerKey, consumerSecret, keyType,
			StringPool.BLANK, StringPool.BLANK);
	}

}