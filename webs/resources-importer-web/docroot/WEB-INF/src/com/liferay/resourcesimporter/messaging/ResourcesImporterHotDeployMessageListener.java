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

import com.liferay.exportimport.kernel.lar.ExportImportThreadLocal;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.HotDeployMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.resourcesimporter.util.Importer;
import com.liferay.resourcesimporter.util.ImporterException;
import com.liferay.resourcesimporter.util.ImporterFactory;
import com.liferay.resourcesimporter.util.PluginPackageProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
 * @author Raymond Augé
 */
public class ResourcesImporterHotDeployMessageListener
	extends HotDeployMessageListener {

	protected void initialize(Message message) throws Exception {
		String servletContextName = message.getString("servletContextName");

		ServletContext servletContext = ServletContextPool.get(
			servletContextName);

		if (servletContext == null) {
			return;
		}

		PluginPackageProperties pluginPackageProperties =
			new PluginPackageProperties(servletContext);

		String resourcesDir = pluginPackageProperties.getResourcesDir();

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
					company, servletContext, pluginPackageProperties,
					message.getResponseId());
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

	private void importResources(
			Company company, ServletContext servletContext,
			PluginPackageProperties pluginPackageProperties,
			String messageResponseId)
		throws Exception {

		long companyId = CompanyThreadLocal.getCompanyId();

		try {
			CompanyThreadLocal.setCompanyId(company.getCompanyId());

			ImporterFactory importerFactory = ImporterFactory.getInstance();

			Importer importer = importerFactory.createImporter(
				company.getCompanyId(), servletContext,
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

			Message message = new Message();

			message.put("companyId", company.getCompanyId());
			message.put(
				"servletContextName", servletContext.getServletContextName());
			message.put("targetClassName", importer.getTargetClassName());
			message.put("targetClassPK", importer.getTargetClassPK());

			if (Validator.isNotNull(messageResponseId)) {
				Map<String, Object> responseMap = new HashMap<>();

				responseMap.put("groupId", importer.getTargetClassPK());

				message.setPayload(responseMap);

				message.setResponseId(messageResponseId);
			}

			MessageBusUtil.sendMessage("liferay/resources_importer", message);
		}
		catch (ImporterException ie) {
			Message message = new Message();

			message.put("companyId", company.getCompanyId());
			message.put("error", ie.getMessage());
			message.put(
				"servletContextName", servletContext.getServletContextName());
			message.put(
				"targetClassName",
				pluginPackageProperties.getTargetClassName());
			message.put("targetClassPK", 0);

			MessageBusUtil.sendMessage("liferay/resources_importer", message);
		}
		finally {
			CompanyThreadLocal.setCompanyId(companyId);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		ResourcesImporterHotDeployMessageListener.class);

}