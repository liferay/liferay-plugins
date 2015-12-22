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

package com.liferay.marketplace.store.portlet;

import com.liferay.marketplace.configuration.PortletPropsValues;
import com.liferay.marketplace.constants.MarketplaceConstants;
import com.liferay.marketplace.constants.PortletKeys;
import com.liferay.marketplace.constants.WebKeys;
import com.liferay.marketplace.model.App;
import com.liferay.marketplace.oauth.util.OAuthUtil;
import com.liferay.marketplace.service.AppLocalServiceUtil;
import com.liferay.marketplace.service.AppServiceUtil;
import com.liferay.marketplace.util.MarketplaceLicenseUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.theme.ThemeDisplay;

import java.io.File;
import java.io.IOException;

import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.WindowState;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

/**
 * @author Ryan Park
 */
public class StorePortlet extends RemoteMVCPortlet {

	public void downloadApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long appPackageId = ParamUtil.getLong(actionRequest, "appPackageId");
		boolean unlicensed = ParamUtil.getBoolean(actionRequest, "unlicensed");

		File file = null;

		try {
			file = FileUtil.createTempFile();

			downloadApp(
				actionRequest, actionResponse, appPackageId, unlicensed, file);

			App app = AppServiceUtil.updateApp(file);

			JSONObject jsonObject = getAppJSONObject(app.getRemoteAppId());

			jsonObject.put("cmd", "downloadApp");
			jsonObject.put("message", "success");

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			if (file != null) {
				file.delete();
			}
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

	public void getPrepackagedApps(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		OAuthRequest oAuthRequest = new OAuthRequest(
			Verb.POST, getServerPortletURL());

		setBaseRequestParameters(actionRequest, actionResponse, oAuthRequest);

		addOAuthParameter(oAuthRequest, "p_p_lifecycle", "1");
		addOAuthParameter(
			oAuthRequest, "p_p_state", WindowState.NORMAL.toString());

		String serverNamespace = getServerNamespace();

		addOAuthParameter(
			oAuthRequest, serverNamespace.concat("compatibility"),
			String.valueOf(ReleaseInfo.getBuildNumber()));
		addOAuthParameter(
			oAuthRequest, serverNamespace.concat("javax.portlet.action"),
			"getPrepackagedApps");

		Map<String, String> prepackagedApps =
			AppLocalServiceUtil.getPrepackagedApps();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		Set<String> keys = prepackagedApps.keySet();

		for (String key : keys) {
			jsonObject.put(key, prepackagedApps.get(key));
		}

		addOAuthParameter(
			oAuthRequest, serverNamespace.concat("prepackagedApps"),
			jsonObject.toString());

		Response response = getResponse(themeDisplay.getUser(), oAuthRequest);

		JSONObject responseJSONObject = JSONFactoryUtil.createJSONObject(
			response.getBody());

		writeJSON(actionRequest, actionResponse, responseJSONObject);
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
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			super.render(renderRequest, renderResponse);
		}
		catch (PortletException pe) {
			include("/store/error.jsp", renderRequest, renderResponse);
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

		long appPackageId = ParamUtil.getLong(actionRequest, "appPackageId");
		boolean unlicensed = ParamUtil.getBoolean(actionRequest, "unlicensed");
		String orderUuid = ParamUtil.getString(actionRequest, "orderUuid");
		String productEntryName = ParamUtil.getString(
			actionRequest, "productEntryName");

		File file = null;

		try {
			file = FileUtil.createTempFile();

			downloadApp(
				actionRequest, actionResponse, appPackageId, unlicensed, file);

			App app = AppServiceUtil.updateApp(file);

			if (Validator.isNull(orderUuid) &&
				Validator.isNotNull(productEntryName)) {

				orderUuid = MarketplaceLicenseUtil.getOrder(productEntryName);
			}

			if (Validator.isNotNull(orderUuid)) {
				MarketplaceLicenseUtil.registerOrder(
					orderUuid, productEntryName);
			}

			AppServiceUtil.installApp(app.getRemoteAppId());

			JSONObject jsonObject = getAppJSONObject(app.getRemoteAppId());

			jsonObject.put("cmd", "updateApp");
			jsonObject.put("message", "success");

			writeJSON(actionRequest, actionResponse, jsonObject);
		}
		finally {
			if (file != null) {
				file.delete();
			}
		}
	}

	@Override
	protected void doDispatch(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		try {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			Token accessToken = OAuthUtil.getAccessToken(
				themeDisplay.getUser());

			if (accessToken == null) {
				include("/store/login.jsp", renderRequest, renderResponse);

				return;
			}
		}
		catch (Exception pe) {
			throw new PortletException(pe);
		}

		renderRequest.setAttribute(WebKeys.OAUTH_AUTHORIZED, Boolean.TRUE);

		super.doDispatch(renderRequest, renderResponse);
	}

	protected void downloadApp(
			PortletRequest portletRequest, PortletResponse portletResponse,
			long appPackageId, boolean unlicensed, File file)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		OAuthRequest oAuthRequest = new OAuthRequest(
			Verb.GET, getServerPortletURL());

		setBaseRequestParameters(portletRequest, portletResponse, oAuthRequest);

		String serverNamespace = getServerNamespace();

		addOAuthParameter(
			oAuthRequest, serverNamespace.concat("appPackageId"),
			String.valueOf(appPackageId));
		addOAuthParameter(oAuthRequest, "p_p_lifecycle", "2");

		if (unlicensed) {
			addOAuthParameter(
				oAuthRequest, "p_p_resource_id", "serveUnlicensedApp");
		}
		else {
			addOAuthParameter(oAuthRequest, "p_p_resource_id", "serveApp");
		}

		Response response = getResponse(themeDisplay.getUser(), oAuthRequest);

		FileUtil.write(file, response.getStream());
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

	@Override
	protected String getClientPortletId() {
		return PortletKeys.STORE;
	}

	@Override
	protected String getServerPortletId() {
		return PortletPropsValues.MARKETPLACE_PORTLET_ID;
	}

	@Override
	protected String getServerPortletURL() {
		return PortletPropsValues.MARKETPLACE_URL + "/osb-portlet/mp_server";
	}

	@Override
	protected void processPortletParameterMap(
		PortletRequest portletRequest, PortletResponse portletResponse,
		Map<String, String[]> parameterMap) {

		parameterMap.put(
			"clientBuild",
			new String[] {String.valueOf(MarketplaceConstants.CLIENT_BUILD)});

		if (!parameterMap.containsKey("compatibility")) {
			parameterMap.put(
				"compatibility",
				new String[] {String.valueOf(ReleaseInfo.getBuildNumber())});
		}

		parameterMap.put(
			"supportsHotDeploy",
			new String[] {
				String.valueOf(ServerDetector.isSupportsHotDeploy())
			});
	}

}