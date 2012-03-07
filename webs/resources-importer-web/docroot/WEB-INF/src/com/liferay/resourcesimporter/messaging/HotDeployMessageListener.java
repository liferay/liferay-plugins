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
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.TextFormatter;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.resourcesimporter.util.FileSystemImporter;
import com.liferay.resourcesimporter.util.LARImporter;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

/**
 * @author Ryan Park
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

		File resourcesDir = new File(
			servletContext.getRealPath("/WEB-INF/classes/resources-importer"));

		if (!resourcesDir.exists()) {
			return;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Importing resources from " + servletContextName);
		}

		String layoutSetPrototypeName = TextFormatter.format(
			servletContextName, TextFormatter.J);

		File larFile = new File(resourcesDir, "/archive.lar");

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

			Map<Locale, String> layoutSetPrototypeNameMap =
				new HashMap<Locale, String>();

			Locale locale = LocaleUtil.getDefault();

			layoutSetPrototypeNameMap.put(locale, layoutSetPrototypeName);

			if (larFile.exists()) {
				LARImporter larImporter = getLARImporter();

				larImporter.importResources(
					company.getCompanyId(), layoutSetPrototypeNameMap, larFile);
			}
			else {
				FileSystemImporter fileSystemImporter = getFileSystemImporter();

				fileSystemImporter.importResources(
					company.getCompanyId(), layoutSetPrototypeNameMap,
					resourcesDir);
			}
		}
	}

	protected FileSystemImporter getFileSystemImporter() {
		return new FileSystemImporter();
	}

	protected LARImporter getLARImporter() {
		return new LARImporter();
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

}