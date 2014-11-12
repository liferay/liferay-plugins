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

package com.liferay.scriptingexecutor.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.ClassLoaderPool;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Michael C. Han
 */
public class ScriptingExecutorHotDeployMessageListener
	extends HotDeployMessageListener {

	public ScriptingExecutorHotDeployMessageListener() {
	}

	public ScriptingExecutorHotDeployMessageListener(
		String... servletContextNames) {

		super(servletContextNames);
	}

	protected void executeScripts(
		ServletContext servletContext, String language,
		String requiredDeploymentContexts) {

		InputStream inputStream = null;

		Set<String> resourcePaths = servletContext.getResourcePaths(
			_SCRIPTS_DIR);

		Set<String> servletContextNames = SetUtil.fromArray(
			StringUtil.split(requiredDeploymentContexts));

		servletContextNames.add(servletContext.getServletContextName());
		servletContextNames.add(
			ClassLoaderPool.getContextName(
				PortalClassLoaderUtil.getClassLoader()));

		for (String resourcePath : resourcePaths) {
			if (resourcePath.endsWith(StringPool.SLASH)) {
				if (_log.isInfoEnabled()) {
					_log.info("Skipping directory " + resourcePath);
				}

				continue;
			}

			try {
				inputStream = servletContext.getResourceAsStream(resourcePath);

				ScriptingUtil.exec(
					null, new HashMap<String, Object>(), language,
					StringUtil.read(inputStream),
					servletContextNames.toArray(
						new String[servletContextNames.size()]));
			}
			catch (Exception e) {
				_log.error("Unable to execute script " + resourcePath, e);
			}
			finally {
				StreamUtil.cleanUp(inputStream);
			}
		}
	}

	protected String getLanguage(Properties pluginPackageProperties) {
		return pluginPackageProperties.getProperty(
			"scripting-executor-language");
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

	@Override
	protected void onDeploy(Message message) throws Exception {
		ServletContext servletContext = ServletContextPool.get(
			message.getString("servletContextName"));

		URL url = servletContext.getResource(_SCRIPTS_DIR);

		if (url == null) {
			return;
		}

		Set<String> supportedLanguages = ScriptingUtil.getSupportedLanguages();

		Properties pluginPackageProperties = getPluginPackageProperties(
			servletContext);

		String language = getLanguage(pluginPackageProperties);

		if (!supportedLanguages.contains(language)) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unsupported language " + language);
			}

			return;
		}

		String requiredDeploymentContexts = pluginPackageProperties.getProperty(
			"required-deployment-contexts");

		if (Validator.isNull(requiredDeploymentContexts)) {
			return;
		}

		executeScripts(servletContext, language, requiredDeploymentContexts);
	}

	@Override
	protected void onUndeploy(Message message) throws Exception {
		ServletContext servletContext = ServletContextPool.get(
			message.getString("servletContextName"));

		if (servletContext != null) {
			Properties pluginPackageProperties = getPluginPackageProperties(
				servletContext);

			String language = getLanguage(pluginPackageProperties);

			if (Validator.isNotNull(language)) {
				ScriptingUtil.clearCache(language);
			}
		}
		else {
			Set<String> supportedLanguages =
				ScriptingUtil.getSupportedLanguages();

			for (String supportedLanguage : supportedLanguages) {
				ScriptingUtil.clearCache(supportedLanguage);
			}
		}
	}

	private static final String _SCRIPTS_DIR = "/WEB-INF/classes/scripts/";

	private static Log _log = LogFactoryUtil.getLog(
		ScriptingExecutorHotDeployMessageListener.class);

}