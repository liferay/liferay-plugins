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

package com.liferay.resourcesimporter.messaging;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.resourcesimporter.util.FileSystemImporter;
import com.liferay.resourcesimporter.util.Importer;
import com.liferay.resourcesimporter.util.LARImporter;
import com.liferay.resourcesimporter.util.ResourceImporter;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class HotDeployMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		String command = message.getString("command");

		if (!command.equals("deploy")) {
			return;
		}

		String servletContextName = message.getString("servletContextName");

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		URL resourcesDirURL = servletContext.getResource(_RESOURCES_DIR);

		if (resourcesDirURL == null) {
			return;
		}

		String layoutSetPrototypeName = TextFormatter.format(
			servletContextName, TextFormatter.J);

		//layoutSetPrototypeName += " " + System.currentTimeMillis();

		Set<String> resourcePaths = servletContext.getResourcePaths(
			_RESOURCES_DIR);
		URL larURL = servletContext.getResource(
			_RESOURCES_DIR.concat("archive.lar"));

		List<Company> companies = CompanyLocalServiceUtil.getCompanies();

		for (Company company : companies) {
			if (hasLayoutSetPrototype(
					company.getCompanyId(), layoutSetPrototypeName)) {

				if (_log.isInfoEnabled()) {
					_log.info(
						"Layout set prototype already exists for company " +
							company.getWebId());
				}

				continue;
			}

			long companyId = CompanyThreadLocal.getCompanyId();

			try {
				CompanyThreadLocal.setCompanyId(company.getCompanyId());

				Importer importer = null;

				if (larURL != null) {
					LARImporter larImporter = getLARImporter();

					URLConnection urlConnection = larURL.openConnection();

					larImporter.setLARInputStream(
						urlConnection.getInputStream());

					importer = larImporter;
				}
				else if ((resourcePaths != null) &&
						 (!resourcePaths.isEmpty()) && (larURL == null)) {

					importer = getResourceImporter();

					importer.setResourcesDir(_RESOURCES_DIR);
				}
				else {
					Properties pluginProperties = getPluginProperties(
						servletContext);

					String resourcesDir = pluginProperties.getProperty(
							"resources-importer-external-dir");

					if (Validator.isNotNull(resourcesDir)) {
						importer = getFileSystemImporter();

						importer.setResourcesDir(resourcesDir);
					}
				}

				if (importer == null) {
					Message newMessage = new Message();

					newMessage.put("companyId", company.getCompanyId());
					newMessage.put(
						"layoutSetPrototypeId", 0);
					newMessage.put("servletContextName", servletContextName);
					newMessage.put("error", "no valid importer found");

					MessageBusUtil.sendMessage(
						"liferay/resources_importer", newMessage);

					return;
				}

				importer.setCompanyId(company.getCompanyId());

				Map<Locale, String> layoutSetPrototypeNameMap =
					new HashMap<Locale, String>();

				Locale locale = LocaleUtil.getDefault();

				layoutSetPrototypeNameMap.put(locale, layoutSetPrototypeName);

				importer.setLayoutSetPrototypeNameMap(
					layoutSetPrototypeNameMap);

				importer.setServletContext(servletContext);
				importer.setServletContextName(servletContextName);

				importer.afterPropertiesSet();

				if (_log.isInfoEnabled()) {
					_log.info(
						"Importing resources from " + servletContextName +
							" to group " + importer.getGroupId());
				}

				importer.importResources();

				Message newMessage = new Message();

				newMessage.put("companyId", company.getCompanyId());
				newMessage.put(
					"layoutSetPrototypeId", importer.getLayoutSetPrototypeId());
				newMessage.put("servletContextName", servletContextName);

				MessageBusUtil.sendMessage(
					"liferay/resources_importer", newMessage);
			}
			finally {
				CompanyThreadLocal.setCompanyId(companyId);
			}
		}
	}

	protected FileSystemImporter getFileSystemImporter() {
		return new FileSystemImporter();
	}

	protected LARImporter getLARImporter() {
		return new LARImporter();
	}

	protected Properties getPluginProperties(ServletContext servletContext) {
		Properties properties = null;

		try {
			String propertiesString = StringUtil.read(
				servletContext.getResourceAsStream(
					"/WEB-INF/liferay-plugin-package.properties"));

			if (propertiesString != null) {
				properties = PropertiesUtil.load(propertiesString);
			}
		}
		catch (IOException e) {
			_log.error(e, e);
		}

		if (properties == null) {
			properties = new Properties();
		}

		return properties;
	}

	protected ResourceImporter getResourceImporter() {
		return new ResourceImporter();
	}

	protected boolean hasLayoutSetPrototype(long companyId, String name)
		throws Exception {

		Locale locale = LocaleUtil.getDefault();

		List<LayoutSetPrototype> layoutSetPrototypes =
			LayoutSetPrototypeLocalServiceUtil.search(
				companyId, null, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (LayoutSetPrototype layoutSetPrototype : layoutSetPrototypes) {
			if (name.equals(layoutSetPrototype.getName(locale))) {
				return true;
			}
		}

		return false;
	}

	private static Log _log = LogFactoryUtil.getLog(
		HotDeployMessageListener.class);

	private static final String _RESOURCES_DIR =
		"/WEB-INF/classes/resources-importer/";

}