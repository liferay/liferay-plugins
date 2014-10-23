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
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.resourcesimporter.util.Importer;
import com.liferay.resourcesimporter.util.ImporterException;
import com.liferay.resourcesimporter.util.ImporterFactory;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class ResourcesImporterHotDeployMessageListener
	extends HotDeployMessageListener {

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

	protected String getTargetClassName(Properties pluginPackageProperties) {
		return pluginPackageProperties.getProperty(
					"resources-importer-target-class-name",
					LayoutSetPrototype.class.getName());
	}

	protected void initialize(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		Properties pluginPackageProperties = getPluginPackageProperties(
			servletContext);

		String resourcesDir = pluginPackageProperties.getProperty(
			"resources-importer-external-dir");

		if ((servletContext.getResource(
				ImporterFactory.RESOURCES_DIR) == null) &&
			(servletContext.getResource(
				ImporterFactory.TEMPLATES_DIR) == null) &&
			Validator.isNull(resourcesDir)) {

			return;
		}

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		try {
			ExportImportThreadLocal.setLayoutImportInProcess(true);
			ExportImportThreadLocal.setPortletImportInProcess(true);

			for (Company company : companies) {
				importResources(
					company, servletContext, message, pluginPackageProperties,
					resourcesDir);
			}
		}
		finally {
			ExportImportThreadLocal.setLayoutImportInProcess(false);
			ExportImportThreadLocal.setPortletImportInProcess(false);
		}
	}

	@Override
	protected void onDeploy(Message message) throws Exception {
		initialize(message);
	}

	private void configureImporter(
			long companyId, Importer importer, ServletContext servletContext,
			Properties pluginPackageProperties)
		throws Exception {

		boolean appendVersion = GetterUtil.getBoolean(
			pluginPackageProperties.getProperty(
				"resources-importer-append-version"),
			true);

		importer.setAppendVersion(appendVersion);

		importer.setCompanyId(companyId);

		boolean developerModeEnabled = GetterUtil.getBoolean(
			pluginPackageProperties.getProperty(
				"resources-importer-developer-mode-enabled")) ||
			PortalRunMode.isTestMode();

		importer.setDeveloperModeEnabled(developerModeEnabled);

		importer.setServletContext(servletContext);
		importer.setServletContextName(servletContext.getServletContextName());

		importer.setTargetClassName(
			getTargetClassName(pluginPackageProperties));

		String targetValue = pluginPackageProperties.getProperty(
			"resources-importer-target-value");

		if (Validator.isNull(targetValue)) {
			targetValue = TextFormatter.format(
				servletContext.getServletContextName(), TextFormatter.J);
		}

		importer.setTargetValue(targetValue);

		boolean updateModeEnabled = GetterUtil.getBoolean(
			pluginPackageProperties.getProperty(
				"resources-importer-update-mode-enabled"));

		importer.setUpdateModeEnabled(updateModeEnabled);

		PluginPackage pluginPackage =
			DeployManagerUtil.getInstalledPluginPackage(
				servletContext.getServletContextName());

		importer.setVersion(pluginPackage.getVersion());

		importer.afterPropertiesSet();
	}

	private void importResources(
			Company company, ServletContext servletContext, Message message,
			Properties pluginPackageProperties, String resourcesDir)
		throws Exception {

		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			CompanyThreadLocal.setCompanyId(company.getCompanyId());

			ImporterFactory importerFactory = ImporterFactory.getInstance();

			Importer importer = importerFactory.createImporter(
				company.getCompanyId(), servletContext, resourcesDir);

			configureImporter(
				company.getCompanyId(), importer, servletContext,
				pluginPackageProperties);

			if (!importer.isDeveloperModeEnabled() && importer.isExisting() &&
				!importer.isCompanyGroup()) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Group or layout set prototype already exists " +
							"for company " + company.getWebId());
				}

				return;
			}

			long startTime = 0;

			if (_log.isInfoEnabled()) {
				startTime = System.currentTimeMillis();
			}

			importer.importResources();

			if (_log.isInfoEnabled()) {
				long endTime = System.currentTimeMillis() - startTime;

				_log.info(
					"Importing resources from " +
						servletContext.getServletContextName() +
						" to group " + importer.getGroupId() + " takes " +
							endTime + " ms");
			}

			Message newMessage = new Message();

			newMessage.put("companyId", company.getCompanyId());
			newMessage.put(
				"servletContextName", servletContext.getServletContextName());
			newMessage.put("targetClassName", importer.getTargetClassName());
			newMessage.put("targetClassPK", importer.getTargetClassPK());

			if (message.getResponseId() != null) {
				Map<String, Object> responseMap = new HashMap<String, Object>();

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
			newMessage.put(
				"servletContextName", servletContext.getServletContextName());

			newMessage.put(
				"targetClassName", getTargetClassName(pluginPackageProperties));
			newMessage.put("targetClassPK", 0);

			MessageBusUtil.sendMessage(
				"liferay/resources_importer", newMessage);
		}
		finally {
			CompanyThreadLocal.setCompanyId(companyId);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ResourcesImporterHotDeployMessageListener.class);

}