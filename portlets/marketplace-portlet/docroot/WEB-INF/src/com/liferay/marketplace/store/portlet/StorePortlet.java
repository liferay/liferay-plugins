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
import com.liferay.marketplace.util.MarketplaceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.expando.service.ExpandoValueLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author Ryan Park
 */
public class StorePortlet extends MVCPortlet {

	public void downloadApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");
		String version = ParamUtil.getString(actionRequest, "version");
		String url = ParamUtil.getString(actionRequest, "url");

		URL urlObj = new URL(url);

		InputStream inputStream = urlObj.openStream();

		App app = AppLocalServiceUtil.fetchRemoteApp(remoteAppId);

		if (app == null) {
			app = AppLocalServiceUtil.addApp(
				themeDisplay.getUserId(), remoteAppId, version,
				inputStream);
		}
		else {
			app = AppLocalServiceUtil.updateApp(
				app.getAppId(), version, inputStream);
		}

		JSONObject jsonObject = getAppJSONObject(app.getRemoteAppId());

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void getApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");

		JSONObject jsonObject = getAppJSONObject(remoteAppId);

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void getClientId(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String token = ParamUtil.getString(actionRequest, "token");

		String clientId = ExpandoValueLocalServiceUtil.getData(
			themeDisplay.getCompanyId(), User.class.getName(), "MP",
			"client-id", themeDisplay.getUserId(), "default-client-id");

		String encodedClientId = MarketplaceUtil.encodeClientId(
			clientId, token);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("clientId", encodedClientId);
		jsonObject.put("token", token);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void installApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");

		AppLocalServiceUtil.installApp(remoteAppId);

		JSONObject jsonObject = getAppJSONObject(remoteAppId);

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException {

		try {
			if (!isProcessActionRequest(actionRequest)) {
				return;
			}

			if (!callActionMethod(actionRequest, actionResponse)) {
				return;
			}
		}
		catch (PortletException pe) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("message", "fail");

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
	}

	public void updateApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");
		String version = ParamUtil.getString(actionRequest, "version");
		String url = ParamUtil.getString(actionRequest, "url");

		URL urlObj = new URL(url);

		InputStream inputStream = null;

		try {
			inputStream = urlObj.openStream();

			App app = AppLocalServiceUtil.fetchRemoteApp(remoteAppId);

			AppLocalServiceUtil.updateApp(app.getAppId(), version, inputStream);

			AppLocalServiceUtil.installApp(remoteAppId);

			JSONObject jsonObject = getAppJSONObject(remoteAppId);

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public void uninstallApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");

		AppLocalServiceUtil.uninstallApp(remoteAppId);

		JSONObject jsonObject = getAppJSONObject(remoteAppId);

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateClientId(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		if (!themeDisplay.isSignedIn()) {
			return;
		}

		String clientId = ParamUtil.getString(actionRequest, "clientId");
		String token = ParamUtil.getString(actionRequest, "token");

		String decodedClientId = MarketplaceUtil.decodeClientId(
			clientId, token);

		if (Validator.isNull(decodedClientId)) {
			return;
		}

		ExpandoValueLocalServiceUtil.addValue(
			themeDisplay.getCompanyId(), User.class.getName(), "MP",
			"client-id", themeDisplay.getUserId(), decodedClientId);
	}

	@Override
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
			else if (cmd.equals("getClientId")) {
				getClientId(actionRequest, actionResponse);
			}
			else if (cmd.equals("install")) {
				installApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("update")) {
				updateApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("updateClientId")) {
				updateClientId(actionRequest, actionResponse);
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

	protected JSONObject getAppJSONObject(long remoteAppId) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		App app = AppLocalServiceUtil.fetchRemoteApp(remoteAppId);

		if (app != null) {
			jsonObject.put("appId", app.getRemoteAppId());
			jsonObject.put("downloaded", app.isDownloaded());
			jsonObject.put("installed", app.isInstalled());
			jsonObject.put("version", app.getVersion());
		}
		else {
			jsonObject.put("appId", remoteAppId);
			jsonObject.put("downloaded", false);
			jsonObject.put("installed", false);
			jsonObject.put("version", StringPool.BLANK);
		}

		return jsonObject;
	}

}