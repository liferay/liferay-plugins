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

package com.liferay.marketplace.appmanager.portlet;

import com.liferay.marketplace.service.AppServiceUtil;
import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Theme;
import com.liferay.portal.service.PluginSettingLocalServiceUtil;
import com.liferay.portal.service.PluginSettingServiceUtil;
import com.liferay.portal.service.PortletServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ryan Park
 */
public class AppManagerPortlet extends MVCPortlet {

	public void installApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		String installMethod = ParamUtil.getString(
			uploadPortletRequest, "installMethod");

		if (installMethod.equals("local")) {
			String fileName = GetterUtil.getString(
				uploadPortletRequest.getFileName("file"));

			File file = uploadPortletRequest.getFile("file");

			byte[] bytes = FileUtil.getBytes(file);

			if (ArrayUtil.isEmpty(bytes)) {
				SessionErrors.add(
					actionRequest, UploadException.class.getName());
			}
			else {
				String deployDir = PrefsPropsUtil.getString(
					PropsKeys.AUTO_DEPLOY_DEPLOY_DIR);

				FileUtil.copyFile(
					file.toString(), deployDir + StringPool.SLASH + fileName);

				SessionMessages.add(actionRequest, "pluginUploaded");
			}
		}
		else {
			try {
				String url = ParamUtil.getString(uploadPortletRequest, "url");

				URL urlObj = new URL(url);

				String host = urlObj.getHost();

				if (host.endsWith("sf.net") ||
					host.endsWith("sourceforge.net")) {

					doInstallSourceForgeApp(
						urlObj.getPath(), uploadPortletRequest, actionRequest);
				}
				else {
					doInstallRemoteApp(
						url, uploadPortletRequest, actionRequest, true);
				}
			}
			catch (MalformedURLException murle) {
				SessionErrors.add(actionRequest, "invalidUrl", murle);
			}
		}

		String redirect = ParamUtil.getString(uploadPortletRequest, "redirect");

		actionResponse.sendRedirect(redirect);
	}

	public void uninstallApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long remoteAppId = ParamUtil.getLong(actionRequest, "remoteAppId");

		if (remoteAppId > 0) {
			AppServiceUtil.uninstallApp(remoteAppId);
		}
		else {
			String[] contextNames = StringUtil.split(
				ParamUtil.getString(actionRequest, "contextNames"));

			for (String contextName : contextNames) {
				DeployManagerUtil.undeploy(contextName);
			}
		}

		SessionMessages.add(actionRequest, "triggeredPortletUndeploy");
	}

	public void updatePluginSetting(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String pluginId = ParamUtil.getString(actionRequest, "pluginId");
		String pluginType = ParamUtil.getString(actionRequest, "pluginType");

		String[] roles = StringUtil.split(
			ParamUtil.getString(actionRequest, "roles"), CharPool.NEW_LINE);

		Arrays.sort(roles);

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		if (pluginType.equals(Plugin.TYPE_PORTLET)) {
			PortletServiceUtil.updatePortlet(
				themeDisplay.getCompanyId(), pluginId, StringPool.BLANK,
				active);
		}
		else {
			if (roles.length == 0) {
				PluginSetting pluginSetting =
					PluginSettingLocalServiceUtil.getPluginSetting(
						themeDisplay.getCompanyId(), pluginId, pluginType);

				roles = StringUtil.split(pluginSetting.getRoles());
			}

			PluginSettingServiceUtil.updatePluginSetting(
				themeDisplay.getCompanyId(), pluginId, pluginType,
				StringUtil.merge(roles), active);
		}
	}

	public void updatePluginSettings(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String[] contextNames = StringUtil.split(
			ParamUtil.getString(actionRequest, "contextNames"));

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		for (String contextName : contextNames) {
			ServletContext servletContext = ServletContextPool.get(contextName);

			List<LayoutTemplate> layoutTemplates =
				(List<LayoutTemplate>)servletContext.getAttribute(
					WebKeys.PLUGIN_LAYOUT_TEMPLATES);

			if (layoutTemplates != null) {
				for (LayoutTemplate layoutTemplate : layoutTemplates) {
					PluginSetting pluginSetting =
						PluginSettingLocalServiceUtil.getPluginSetting(
							themeDisplay.getCompanyId(),
							layoutTemplate.getLayoutTemplateId(),
							Plugin.TYPE_LAYOUT_TEMPLATE);

					PluginSettingServiceUtil.updatePluginSetting(
						themeDisplay.getCompanyId(),
						layoutTemplate.getLayoutTemplateId(),
						Plugin.TYPE_LAYOUT_TEMPLATE, pluginSetting.getRoles(),
						active);
				}
			}

			List<Portlet> portlets = (List<Portlet>)servletContext.getAttribute(
				WebKeys.PLUGIN_PORTLETS);

			if (portlets != null) {
				for (Portlet portlet : portlets) {
					PortletServiceUtil.updatePortlet(
						themeDisplay.getCompanyId(), portlet.getPortletId(),
						StringPool.BLANK, active);
				}
			}

			List<Theme> themes = (List<Theme>)servletContext.getAttribute(
				WebKeys.PLUGIN_THEMES);

			if (themes != null) {
				for (Theme theme : themes) {
					PluginSetting pluginSetting =
						PluginSettingLocalServiceUtil.getPluginSetting(
							themeDisplay.getCompanyId(), theme.getThemeId(),
							Plugin.TYPE_THEME);

					PluginSettingServiceUtil.updatePluginSetting(
						themeDisplay.getCompanyId(), theme.getThemeId(),
						Plugin.TYPE_THEME, pluginSetting.getRoles(), active);
				}
			}
		}
	}

	protected int doInstallRemoteApp(
			String url, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest, boolean failOnError)
		throws Exception {

		int responseCode = HttpServletResponse.SC_OK;

		String deploymentContext = ParamUtil.getString(
			uploadPortletRequest, "deploymentContext");

		try {
			String fileName = null;

			if (Validator.isNotNull(deploymentContext)) {
				fileName = DEPLOY_TO_PREFIX + deploymentContext + ".war";
			}
			else {
				fileName = url.substring(url.lastIndexOf(CharPool.SLASH) + 1);

				int pos = fileName.lastIndexOf(CharPool.PERIOD);

				if (pos != -1) {
					deploymentContext = fileName.substring(0, pos);
				}
			}

			Http.Options options = new Http.Options();

			options.setFollowRedirects(false);
			options.setLocation(url);
			options.setPortletRequest(actionRequest);
			options.setPost(false);

			String progressId = ParamUtil.getString(
				uploadPortletRequest, Constants.PROGRESS_ID);

			options.setProgressId(progressId);

			byte[] bytes = HttpUtil.URLtoByteArray(options);

			Http.Response response = options.getResponse();

			responseCode = response.getResponseCode();

			if ((responseCode == HttpServletResponse.SC_OK) &&
				(bytes.length > 0)) {

				String deployDir = PrefsPropsUtil.getString(
					PropsKeys.AUTO_DEPLOY_DEPLOY_DIR);

				String destination = deployDir + StringPool.SLASH + fileName;

				File destinationFile = new File(destination);

				FileUtil.write(destinationFile, bytes);

				SessionMessages.add(actionRequest, "pluginDownloaded");
			}
			else {
				if (failOnError) {
					SessionErrors.add(
						actionRequest, UploadException.class.getName());
				}

				responseCode = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
			}
		}
		catch (MalformedURLException murle) {
			SessionErrors.add(actionRequest, "invalidUrl", murle);
		}
		catch (IOException ioe) {
			SessionErrors.add(actionRequest, "errorConnectingToUrl", ioe);
		}

		return responseCode;
	}

	protected void doInstallSourceForgeApp(
			String path, UploadPortletRequest uploadPortletRequest,
			ActionRequest actionRequest)
		throws Exception {

		String[] sourceForgeMirrors = PropsUtil.getArray(
			PropsKeys.SOURCE_FORGE_MIRRORS);

		for (int i = 0; i < sourceForgeMirrors.length; i++) {
			try {
				String url = sourceForgeMirrors[i] + path;

				boolean failOnError = false;

				if ((i + 1) == sourceForgeMirrors.length) {
					failOnError = true;
				}

				int responseCode = doInstallRemoteApp(
					url, uploadPortletRequest, actionRequest, failOnError);

				if (responseCode == HttpServletResponse.SC_OK) {
					return;
				}
			}
			catch (MalformedURLException murle) {
				SessionErrors.add(actionRequest, "invalidUrl", murle);
			}
		}
	}

	private final String DEPLOY_TO_PREFIX = "DEPLOY_TO__";

}