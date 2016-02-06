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

import com.liferay.portal.kernel.model.LayoutSetPrototype;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalRunMode;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class PluginPackageProperties {

	public PluginPackageProperties(ServletContext servletContext)
		throws IOException {

		InputStream inputStream = servletContext.getResourceAsStream(
			"/WEB-INF/liferay-plugin-package.properties");

		if (inputStream == null) {
			return;
		}

		String propertiesString = StringUtil.read(inputStream);

		String contextPath = servletContext.getRealPath(StringPool.SLASH);

		contextPath = StringUtil.replace(
			contextPath, StringPool.BACK_SLASH, StringPool.SLASH);

		propertiesString = propertiesString.replace(
			"${context.path}", contextPath);

		PropertiesUtil.load(_properties, propertiesString);
	}

	public String getProperty(String key) {
		return _properties.getProperty(key);
	}

	public String getResourcesDir() {
		return _properties.getProperty("resources-importer-external-dir");
	}

	public String getTargetClassName() {
		return _properties.getProperty(
			"resources-importer-target-class-name",
			LayoutSetPrototype.class.getName());
	}

	public String getTargetValue() {
		return _properties.getProperty("resources-importer-target-value");
	}

	public boolean isAppendVersion() {
		return GetterUtil.getBoolean(
			_properties.getProperty("resources-importer-append-version"), true);
	}

	public boolean isDeveloperModeEnabled() {
		return GetterUtil.getBoolean(
			_properties.getProperty(
				"resources-importer-developer-mode-enabled")) ||
			PortalRunMode.isTestMode();
	}

	public boolean isUpdateModeEnabled() {
		return GetterUtil.getBoolean(
			_properties.getProperty("resources-importer-update-mode-enabled"));
	}

	private Properties _properties = new Properties();

}