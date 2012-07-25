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

package com.liferay.marketplace.store.portlet;

import com.liferay.marketplace.model.App;
import com.liferay.marketplace.service.AppLocalServiceUtil;
import com.liferay.marketplace.service.AppServiceUtil;
import com.liferay.marketplace.util.MarketplaceUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
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

		String token = ParamUtil.getString(actionRequest, "token");
		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");
		String url = ParamUtil.getString(actionRequest, "url");
		String version = ParamUtil.getString(actionRequest, "version");

		url = getRemoteAppPackageURL(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(), token, url);

		URL urlObj = new URL(url);

		InputStream inputStream = null;

		try {
			inputStream = urlObj.openStream();

			App app = AppLocalServiceUtil.fetchRemoteApp(remoteAppId);

			if (app == null) {
				app = AppServiceUtil.addApp(remoteAppId, version, inputStream);
			}
			else {
				app = AppServiceUtil.updateApp(
					app.getAppId(), version, inputStream);
			}

			JSONObject jsonObject = getAppJSONObject(app.getRemoteAppId());

			jsonObject.put("cmd", "downloadApp");
			jsonObject.put("message", "success");

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	public void getApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");

		JSONObject jsonObject = getAppJSONObject(remoteAppId);

		jsonObject.put("cmd", "getApp");
		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void getClientId(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String token = ParamUtil.getString(actionRequest, "token");

		String encodedClientId = MarketplaceUtil.encodeClientId(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(), token);

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("cmd", "getClientId");
		jsonObject.put("clientId", encodedClientId);
		jsonObject.put("token", token);

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void installApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");

		AppServiceUtil.installApp(remoteAppId);

		JSONObject jsonObject = getAppJSONObject(remoteAppId);

		jsonObject.put("cmd", "installApp");
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

	public void uninstallApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");

		AppServiceUtil.uninstallApp(remoteAppId);

		JSONObject jsonObject = getAppJSONObject(remoteAppId);

		jsonObject.put("cmd", "uninstallApp");
		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
	}

	public void updateApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String token = ParamUtil.getString(actionRequest, "token");
		long remoteAppId = ParamUtil.getLong(actionRequest, "appId");
		String version = ParamUtil.getString(actionRequest, "version");
		String url = ParamUtil.getString(actionRequest, "url");

		url = getRemoteAppPackageURL(
			themeDisplay.getCompanyId(), themeDisplay.getUserId(), token, url);

		URL urlObj = new URL(url);

		InputStream inputStream = null;

		try {
			inputStream = urlObj.openStream();

			App app = AppLocalServiceUtil.fetchRemoteApp(remoteAppId);

			if (app == null) {
				app = AppServiceUtil.addApp(remoteAppId, version, inputStream);
			}
			else {
				app = AppServiceUtil.updateApp(
					app.getAppId(), version, inputStream);
			}

			AppServiceUtil.installApp(remoteAppId);

			JSONObject jsonObject = getAppJSONObject(remoteAppId);

			jsonObject.put("cmd", "updateApp");
			jsonObject.put("message", "success");

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
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

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		jsonObject.put("cmd", "updateClientId");

		if (Validator.isNull(decodedClientId)) {
			jsonObject.put("message", "fail");

			writeJSON(actionRequest, actionResponse, jsonObject);

			return;
		}

		ExpandoValueLocalServiceUtil.addValue(
			themeDisplay.getCompanyId(), User.class.getName(), "MP",
			"client-id", themeDisplay.getUserId(), decodedClientId);

		jsonObject.put("message", "success");

		writeJSON(actionRequest, actionResponse, jsonObject);
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
			if (cmd.equals("downloadApp")) {
				downloadApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("getApp")) {
				getApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("getClientId")) {
				getClientId(actionRequest, actionResponse);
			}
			else if (cmd.equals("installApp")) {
				installApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("updateApp")) {
				updateApp(actionRequest, actionResponse);
			}
			else if (cmd.equals("updateClientId")) {
				updateClientId(actionRequest, actionResponse);
			}
			else if (cmd.equals("uninstallApp")) {
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

	protected String getRemoteAppPackageURL(
			long companyId, long userId, String token, String url)
		throws Exception {

		String encodedClientId = MarketplaceUtil.encodeClientId(
			companyId, userId, token);

		url = HttpUtil.addParameter(
			url, _PORTLET_NAMESPACE.concat("clientId"), encodedClientId);
		url = HttpUtil.addParameter(
			url, _PORTLET_NAMESPACE.concat("token"), token);

		return url;
	}

	private static final String _PORTLET_NAMESPACE =
		PortalUtil.getPortletNamespace("12_WAR_osbportlet");

}