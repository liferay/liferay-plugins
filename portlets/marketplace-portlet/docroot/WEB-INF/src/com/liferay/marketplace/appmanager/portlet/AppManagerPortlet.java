/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.LayoutTemplate;
import com.liferay.portal.model.Plugin;
import com.liferay.portal.model.PluginSetting;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.Theme;
import com.liferay.portal.plugin.PluginPackageUtil;
import com.liferay.portal.security.lang.DoPrivilegedBean;
import com.liferay.portal.service.PluginSettingLocalServiceUtil;
import com.liferay.portal.service.PluginSettingServiceUtil;
import com.liferay.portal.service.PortletServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.tools.deploy.BaseDeployer;
import com.liferay.portal.upload.ProgressInputStream;
import com.liferay.portal.util.HttpImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Arrays;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * @author Ryan Park
 */
public class AppManagerPortlet extends MVCPortlet {

	public void installLocalApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);

		String fileName = null;

		String deploymentContext = ParamUtil.getString(
			actionRequest, "deploymentContext");

		if (Validator.isNotNull(deploymentContext)) {
			fileName =
				BaseDeployer.DEPLOY_TO_PREFIX + deploymentContext + ".war";
		}
		else {
			fileName = GetterUtil.getString(
				uploadPortletRequest.getFileName("file"));

			int pos = fileName.lastIndexOf(CharPool.PERIOD);

			if (pos != -1) {
				deploymentContext = fileName.substring(0, pos);
			}
		}

		File file = uploadPortletRequest.getFile("file");

		byte[] bytes = FileUtil.getBytes(file);

		if ((bytes == null) || (bytes.length == 0)) {
			SessionErrors.add(actionRequest, UploadException.class.getName());

			return;
		}

		try {
			PluginPackageUtil.registerPluginPackageInstallation(
				deploymentContext);

			String source = file.toString();

			String deployDir = PrefsPropsUtil.getString(
				PropsKeys.AUTO_DEPLOY_DEPLOY_DIR,
				PropsValues.AUTO_DEPLOY_DEPLOY_DIR);

			String destination = deployDir + StringPool.SLASH + fileName;

			FileUtil.copyFile(source, destination);

			SessionMessages.add(actionRequest, "pluginUploaded");
		}
		finally {
			PluginPackageUtil.endPluginPackageInstallation(deploymentContext);
		}
	}

	public void installRemoteApp(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			String url = ParamUtil.getString(actionRequest, "url");

			URL urlObj = new URL(url);

			String host = urlObj.getHost();

			if (host.endsWith(".sf.net") || host.endsWith(".sourceforge.net")) {
				installRemoteAppSourceForge(urlObj.getPath(), actionRequest);
			}
			else {
				installRemoteApp(url, urlObj, actionRequest, true);
			}
		}
		catch (MalformedURLException murle) {
			SessionErrors.add(actionRequest, "invalidUrl", murle);
		}
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

	protected int installRemoteApp(
			String url, URL urlObj, ActionRequest actionRequest,
			boolean failOnError)
		throws Exception {

		int responseCode = HttpServletResponse.SC_OK;

		GetMethod getMethod = null;

		String deploymentContext = ParamUtil.getString(
			actionRequest, "deploymentContext");

		try {
			HttpImpl httpImpl = null;

			Object httpObject = HttpUtil.getHttp();

			if (httpObject instanceof DoPrivilegedBean) {
				DoPrivilegedBean doPrivilegedBean =
					(DoPrivilegedBean)httpObject;

				httpImpl = (HttpImpl)doPrivilegedBean.getActualBean();
			}
			else {
				httpImpl = (HttpImpl)httpObject;
			}

			HostConfiguration hostConfiguration = httpImpl.getHostConfiguration(
				url);

			HttpClient httpClient = httpImpl.getClient(hostConfiguration);

			getMethod = new GetMethod(url);

			String fileName = null;

			if (Validator.isNotNull(deploymentContext)) {
				fileName =
					BaseDeployer.DEPLOY_TO_PREFIX + deploymentContext + ".war";
			}
			else {
				fileName = url.substring(url.lastIndexOf(CharPool.SLASH) + 1);

				int pos = fileName.lastIndexOf(CharPool.PERIOD);

				if (pos != -1) {
					deploymentContext = fileName.substring(0, pos);
				}
			}

			PluginPackageUtil.registerPluginPackageInstallation(
				deploymentContext);

			responseCode = httpClient.executeMethod(
				hostConfiguration, getMethod);

			if (responseCode != HttpServletResponse.SC_OK) {
				if (failOnError) {
					SessionErrors.add(
						actionRequest, "errorConnectingToUrl",
						new Object[] {String.valueOf(responseCode)});
				}

				return responseCode;
			}

			long contentLength = getMethod.getResponseContentLength();

			String progressId = ParamUtil.getString(
				actionRequest, Constants.PROGRESS_ID);

			ProgressInputStream pis = new ProgressInputStream(
				actionRequest, getMethod.getResponseBodyAsStream(),
				contentLength, progressId);

			String deployDir = PrefsPropsUtil.getString(
				PropsKeys.AUTO_DEPLOY_DEPLOY_DIR,
				PropsValues.AUTO_DEPLOY_DEPLOY_DIR);

			String tmpFilePath =
				deployDir + StringPool.SLASH + _DOWNLOAD_DIR +
					StringPool.SLASH + fileName;

			File tmpFile = new File(tmpFilePath);

			if (!tmpFile.getParentFile().exists()) {
				tmpFile.getParentFile().mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(tmpFile);

			try {
				pis.readAll(fos);

				if (_log.isInfoEnabled()) {
					_log.info(
						"Downloaded plugin from " + urlObj + " has " +
							pis.getTotalRead() + " bytes");
				}
			}
			finally {
				pis.clearProgress();
			}

			getMethod.releaseConnection();

			if (pis.getTotalRead() > 0) {
				String destination = deployDir + StringPool.SLASH + fileName;

				File destinationFile = new File(destination);

				boolean moved = FileUtil.move(tmpFile, destinationFile);

				if (!moved) {
					FileUtil.copyFile(tmpFile, destinationFile);
					FileUtil.delete(tmpFile);
				}

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
		finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}

			PluginPackageUtil.endPluginPackageInstallation(deploymentContext);
		}

		return responseCode;
	}

	protected void installRemoteAppSourceForge(
			String path, ActionRequest actionRequest)
		throws Exception {

		String[] sourceForgeMirrors = getSourceForgeMirrors();

		for (int i = 0; i < sourceForgeMirrors.length; i++) {
			try {
				String url = sourceForgeMirrors[i] + path;

				if (_log.isDebugEnabled()) {
					_log.debug("Downloading from SourceForge mirror " + url);
				}

				URL urlObj = new URL(url);

				boolean failOnError = false;

				if ((i + 1) == sourceForgeMirrors.length) {
					failOnError = true;
				}

				int responseCode = installRemoteApp(
					url, urlObj, actionRequest, failOnError);

				if (responseCode == HttpServletResponse.SC_OK) {
					return;
				}
			}
			catch (MalformedURLException murle) {
				SessionErrors.add(actionRequest, "invalidUrl", murle);
			}
		}
	}

}