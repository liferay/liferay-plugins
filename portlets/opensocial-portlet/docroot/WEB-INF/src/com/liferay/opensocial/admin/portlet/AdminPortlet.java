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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
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

		try {
			doUpdateGadget(actionRequest, actionResponse);
		}
		catch (PortalException pe) {
			SessionErrors.add(actionRequest, pe.getClass().getName());
		}
	}

	public void updateOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		checkPermissions(actionRequest);

		try {
			doUpdateOAuthConsumer(actionRequest, actionResponse);
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

		String url = ParamUtil.getString(actionRequest, "url");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		GadgetLocalServiceUtil.addGadget(
			themeDisplay.getCompanyId(), url, serviceContext);
	}

	protected void doUpdateOAuthConsumer(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long oAuthConsumerId = ParamUtil.getLong(
			actionRequest, "oAuthConsumerId");

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");
		String serviceName = ParamUtil.getString(actionRequest, "serviceName");
		String consumerKey = ParamUtil.getString(actionRequest, "consumerKey");
		String consumerSecret = ParamUtil.getString(
			actionRequest, "consumerSecret");
		String keyType = ParamUtil.getString(actionRequest, "keyType");

		if (oAuthConsumerId <= 0) {
			OAuthConsumerLocalServiceUtil.addOAuthConsumer(
				themeDisplay.getCompanyId(), gadgetId, serviceName, consumerKey,
				consumerSecret, keyType, StringPool.BLANK, StringPool.BLANK);
		}
		else {
			OAuthConsumerLocalServiceUtil.updateOAuthConsumer(
				oAuthConsumerId, consumerKey, consumerSecret, keyType,
				StringPool.BLANK, StringPool.BLANK);
		}
	}

}