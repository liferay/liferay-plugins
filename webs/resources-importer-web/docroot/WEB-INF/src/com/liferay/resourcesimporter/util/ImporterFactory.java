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

package com.liferay.resourcesimporter.util;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.GroupLocalServiceUtil;

import java.net.URL;
import java.net.URLConnection;

import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class ImporterFactory {

	public static final String RESOURCES_DIR =
		"/WEB-INF/classes/resources-importer/";

	public static final String TEMPLATES_DIR =
		"/WEB-INF/classes/templates-importer/";

	public static ImporterFactory getInstance() {
		return _instance;
	}

	public static String getTargetClassName(
		Properties pluginPackageProperties) {

		return pluginPackageProperties.getProperty(
					"resources-importer-target-class-name",
					LayoutSetPrototype.class.getName());
	}

	public Importer createImporter(
			long companyId, ServletContext servletContext,
			Properties pluginPackageProperties, String resourcesDir)
		throws Exception {

		Set<String> resourcePaths = servletContext.getResourcePaths(
			RESOURCES_DIR);
		Set<String> templatePaths = servletContext.getResourcePaths(
			TEMPLATES_DIR);

		URL privateLARURL = null;
		URL publicLARURL = servletContext.getResource(
			RESOURCES_DIR.concat("archive.lar"));

		if (publicLARURL == null) {
			privateLARURL = servletContext.getResource(
				RESOURCES_DIR.concat("private.lar"));

			publicLARURL = servletContext.getResource(
				RESOURCES_DIR.concat("public.lar"));
		}

		Importer importer = null;

		if ((privateLARURL != null) || (publicLARURL != null)) {
			LARImporter larImporter = getLARImporter();

			URLConnection privateLARURLConnection = null;

			if (privateLARURL != null) {
				privateLARURLConnection = privateLARURL.openConnection();

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

			importer.setResourcesDir(RESOURCES_DIR);
		}
		else if ((templatePaths != null) && !templatePaths.isEmpty()) {
			importer = getResourceImporter();

			Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

			importer.setGroupId(group.getGroupId());
			importer.setResourcesDir(TEMPLATES_DIR);
		}
		else if (Validator.isNotNull(resourcesDir)) {
			importer = getFileSystemImporter();

			importer.setResourcesDir(resourcesDir);
		}

		if (importer == null) {
			throw new ImporterException("No valid importer found");
		}

		configureImporter(
			companyId, importer, servletContext, pluginPackageProperties);

		return importer;
	}

	protected void configureImporter(
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

	protected FileSystemImporter getFileSystemImporter() {
		return new FileSystemImporter();
	}

	protected LARImporter getLARImporter() {
		return new LARImporter();
	}

	protected ResourceImporter getResourceImporter() {
		return new ResourceImporter();
	}

	private static ImporterFactory _instance = new ImporterFactory();

}