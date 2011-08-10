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

package com.liferay.marketplace.store.portlet;

import com.liferay.marketplace.model.App;
import com.liferay.marketplace.service.AppLocalServiceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 */
public class StorePortlet extends MVCPortlet {

	public void downloadApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long marketplaceAppId = ParamUtil.getLong(actionRequest, "appId");
		String version = ParamUtil.getString(actionRequest, "version");

		String url = ParamUtil.getString(actionRequest, "url");

		URL marketplaceURL = new URL(url);

		InputStream is = marketplaceURL.openStream();

		App app = AppLocalServiceUtil.fetchApp(marketplaceAppId);

		if (app == null) {
			app = AppLocalServiceUtil.addApp(
				themeDisplay.getUserId(), marketplaceAppId, version, is);
		}
		else {
			app = AppLocalServiceUtil.updateApp(app.getAppId(), version, is);
		}

		sendJSONResponse(actionResponse, "success");
	}

	public void getApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long marketplaceAppId = ParamUtil.getLong(actionRequest, "appId");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		App app = AppLocalServiceUtil.fetchApp(marketplaceAppId);

		if (app != null) {
			jsonObject.put("appId", app.getMarketplaceAppId());
			jsonObject.put("downloaded", app.isDownloaded());
			jsonObject.put("installed", app.isInstalled());
			jsonObject.put("version", app.getVersion());
		}
		else {
			jsonObject.put("appId", marketplaceAppId);
			jsonObject.put("downloaded", false);
			jsonObject.put("installed", false);
			jsonObject.put("version", StringPool.BLANK);
		}

		sendJSONResponse(actionResponse, jsonObject);
	}

	public void installApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long marketplaceAppId = ParamUtil.getLong(actionRequest, "appId");

		AppLocalServiceUtil.installApp(marketplaceAppId);

		sendJSONResponse(actionResponse, "success");
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, PortletException {

		try {
			if (!isProcessActionRequest(actionRequest)) {
				return;
			}

			if (!callActionMethod(actionRequest, actionResponse)) {
				return;
			}
		}
		catch (PortletException pe) {
			sendJSONResponse(actionResponse, "fail");
		}
	}

	public void uninstallApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long marketplaceAppId = ParamUtil.getLong(actionRequest, "appId");

		AppLocalServiceUtil.uninstallApp(marketplaceAppId);

		sendJSONResponse(actionResponse, "success");
	}

	protected boolean callActionMethod(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		if (Validator.isNull(cmd)) {
			return super.callActionMethod(actionRequest, actionResponse);
		}

		try {
			if (cmd.equals("download")) {
				downloadApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("get")) {
				getApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("install")) {
				installApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("uninstall")) {
				uninstallApp(actionRequest, actionResponse);
			}
			else {
				return super.callActionMethod(actionRequest, actionResponse);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}

		return true;
	}

	private void sendJSONResponse(ActionResponse actionResponse, String message)
		throws IOException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("message", message);

		sendJSONResponse(actionResponse, jsonObject);
	}

	private void sendJSONResponse(
			ActionResponse actionResponse, JSONObject jsonObject)
		throws IOException {

		HttpServletResponse response = PortalUtil.getHttpServletResponse(
			actionResponse);

		ServletResponseUtil.write(response, jsonObject.toString());
	}

}