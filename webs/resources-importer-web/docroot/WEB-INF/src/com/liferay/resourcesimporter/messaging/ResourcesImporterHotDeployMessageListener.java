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

package com.liferay.resourcesimporter.messaging;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.resourcesimporter.util.FileSystemImporter;
import com.liferay.resourcesimporter.util.Importer;
import com.liferay.resourcesimporter.util.ImporterException;
import com.liferay.resourcesimporter.util.LARImporter;
import com.liferay.resourcesimporter.util.ResourceImporter;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class ResourcesImporterHotDeployMessageListener
	extends HotDeployMessageListener {

	protected FileSystemImporter getFileSystemImporter() {
		return new FileSystemImporter();
	}

	protected LARImporter getLARImporter() {
		return new LARImporter();
	}

	protected Properties getPluginPackageProperties(
		ServletContext servletContext) {

		Properties properties = new Properties();

		try {
			String propertiesString = StringUtil.read(
				servletContext.getResourceAsStream(
					"/WEB-INF/liferay-plugin-package.properties"));

			if (propertiesString == null) {
				return properties;
			}

			String contextPath = servletContext.getRealPath(StringPool.SLASH);

			contextPath = StringUtil.replace(
				contextPath, StringPool.BACK_SLASH, StringPool.SLASH);

			propertiesString = propertiesString.replace(
				"${context.path}", contextPath);

			PropertiesUtil.load(properties, propertiesString);
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return properties;
	}

	protected ResourceImporter getResourceImporter() {
		return new ResourceImporter();
	}

	protected void initialize(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		Properties pluginPackageProperties = getPluginPackageProperties(
			servletContext);

		String resourcesDir = pluginPackageProperties.getProperty(
			"resources-importer-external-dir");

		if ((servletContext.getResource(_RESOURCES_DIR) == null) &&
			(servletContext.getResource(_TEMPLATES_DIR) == null) &&
			Validator.isNull(resourcesDir)) {

			return;
		}

		String targetClassName = pluginPackageProperties.getProperty(
			"resources-importer-target-class-name",
			LayoutSetPrototype.class.getName());

		Set<String> resourcePaths = servletContext.getResourcePaths(
			_RESOURCES_DIR);
		Set<String> templatePaths = servletContext.getResourcePaths(
			_TEMPLATES_DIR);

		URL privateLARURL = null;
		URL publicLARURL = servletContext.getResource(
			_RESOURCES_DIR.concat("archive.lar"));

		if (publicLARURL == null) {
			privateLARURL = servletContext.getResource(
				_RESOURCES_DIR.concat("private.lar"));
			publicLARURL = servletContext.getResource(
				_RESOURCES_DIR.concat("public.lar"));
		}

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			long companyId = CompanyThreadLocal.getCompanyId();

			ExportImportThreadLocal.setLayoutImportInProcess(true);
			ExportImportThreadLocal.setPortletImportInProcess(true);

			try {
				CompanyThreadLocal.setCompanyId(company.getCompanyId());

				Importer importer = null;

				if ((privateLARURL != null) || (publicLARURL != null)) {
					LARImporter larImporter = getLARImporter();

					URLConnection privateLARURLConnection = null;

					if (privateLARURL != null) {
						privateLARURLConnection =
							privateLARURL.openConnection();

						larImporter.setPrivateLARInputStream(
							privateLARURLConnection.getInputStream());
					}

					URLConnection publicLARURLConnection = null;

					if (publicLARURL != null) {
						publicLARURLConnection = publicLARURL.openConnection();

						larImporter.setPublicLARInputStream(
							publicLARURLConnection.getInputStream());
					}

					importer = larImporter;
				}
				else if ((resourcePaths != null) && !resourcePaths.isEmpty()) {
					importer = getResourceImporter();

					importer.setResourcesDir(_RESOURCES_DIR);
				}
				else if ((templatePaths != null) && !templatePaths.isEmpty()) {
					importer = getResourceImporter();

					Group group = GroupLocalServiceUtil.getCompanyGroup(
						company.getCompanyId());

					importer.setGroupId(group.getGroupId());
					importer.setResourcesDir(_TEMPLATES_DIR);
				}
				else if (Validator.isNotNull(resourcesDir)) {
					importer = getFileSystemImporter();

					importer.setResourcesDir(resourcesDir);
				}

				if (importer == null) {
					throw new ImporterException("No valid importer found");
				}

				importer.setCompanyId(company.getCompanyId());
				importer.setServletContext(servletContext);
				importer.setServletContextName(servletContextName);
				importer.setTargetClassName(targetClassName);

				String targetValue = pluginPackageProperties.getProperty(
					"resources-importer-target-value");

				if (Validator.isNull(targetValue)) {
					targetValue = TextFormatter.format(
						servletContextName, TextFormatter.J);
				}

				importer.setTargetValue(targetValue);

				PluginPackage pluginPackage =
					DeployManagerUtil.getInstalledPluginPackage(
						servletContextName);

				importer.setVersion(pluginPackage.getVersion());

				boolean developerModeEnabled = GetterUtil.getBoolean(
					pluginPackageProperties.getProperty(
						"resources-importer-developer-mode-enabled"));

				importer.setDeveloperModeEnabled(developerModeEnabled);

				boolean updateModeEnabled = GetterUtil.getBoolean(
					pluginPackageProperties.getProperty(
						"resources-importer-update-mode-enabled"));

				importer.setUpdateModeEnabled(updateModeEnabled);

				importer.afterPropertiesSet();

				if (!developerModeEnabled && importer.isExisting() &&
					!importer.isCompanyGroup()) {

					if (_log.isInfoEnabled()) {
						_log.info(
							"Group or layout set prototype already exists " +
								"for company " + company.getWebId());
					}

					continue;
				}

				long startTime = 0;

				if (_log.isInfoEnabled()) {
					startTime = System.currentTimeMillis();
				}

				importer.importResources();

				if (_log.isInfoEnabled()) {
					long endTime = System.currentTimeMillis() - startTime;

					_log.info(
						"Importing resources from " + servletContextName +
							" to group " + importer.getGroupId() + " takes " +
								endTime + " ms");
				}

				Message newMessage = new Message();

				newMessage.put("companyId", company.getCompanyId());
				newMessage.put("servletContextName", servletContextName);
				newMessage.put("targetClassName", targetClassName);
				newMessage.put("targetClassPK", importer.getTargetClassPK());

				if (message.getResponseId() != null) {
					Map<String, Object> responseMap =
						new HashMap<String, Object>();

					responseMap.put("groupId", importer.getTargetClassPK());

					newMessage.setPayload(responseMap);

					newMessage.setResponseId(message.getResponseId());
				}

				MessageBusUtil.sendMessage(
					"liferay/resources_importer", newMessage);
			}
			catch (ImporterException ie) {
				Message newMessage = new Message();

				newMessage.put("companyId", company.getCompanyId());
				newMessage.put("error", ie.getMessage());
				newMessage.put("servletContextName", servletContextName);
				newMessage.put("targetClassName", targetClassName);
				newMessage.put("targetClassPK", 0);

				MessageBusUtil.sendMessage(
					"liferay/resources_importer", newMessage);
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);

				ExportImportThreadLocal.setLayoutImportInProcess(false);
				ExportImportThreadLocal.setPortletImportInProcess(false);
			}
		}
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		initialize(message);
	}

	private static final String _RESOURCES_DIR =
		"/WEB-INF/classes/resources-importer/";

	private static final String _TEMPLATES_DIR =
		"/WEB-INF/classes/templates-importer/";

	private static Log _log = LogFactoryUtil.getLog(
		ResourcesImporterHotDeployMessageListener.class);

}