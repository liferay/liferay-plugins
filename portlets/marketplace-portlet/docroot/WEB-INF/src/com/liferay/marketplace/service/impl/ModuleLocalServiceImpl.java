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

package com.liferay.marketplace.service.impl;

import com.liferay.marketplace.ModuleNamespaceException;
import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.base.ModuleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
public class ModuleLocalServiceImpl extends ModuleLocalServiceBaseImpl {

	@Override
	public Module addModule(
			long userId, long appId, String bundleSymbolicName,
			String bundleVersion, String contextName)
		throws PortalException {

		Module module = fetchModule(
			appId, bundleSymbolicName, bundleVersion, contextName);

		if (module != null) {
			return module;
		}

		validate(bundleSymbolicName, contextName);

		long moduleId = counterLocalService.increment();

		module = modulePersistence.create(moduleId);

		module.setModuleId(moduleId);
		module.setAppId(appId);
		module.setBundleSymbolicName(bundleSymbolicName);
		module.setBundleVersion(bundleVersion);
		module.setContextName(contextName);

		modulePersistence.update(module);

		return module;
	}

	@Override
	public Module fetchModule(
		long appId, String bundleSymbolicName, String bundleVersion,
		String contextName) {

		if (Validator.isNotNull(bundleSymbolicName)) {
			return modulePersistence.fetchByA_BSN_BV(
				appId, bundleSymbolicName, bundleVersion);
		}
		else if (Validator.isNotNull(contextName)) {
			return modulePersistence.fetchByA_CN(appId, contextName);
		}

		return null;
	}

	@Override
	public List<Module> getModules(long appId) {
		return modulePersistence.findByAppId(appId);
	}

	protected void validate(String bundleSymbolicName, String contextName)
		throws PortalException {

		if (Validator.isNull(bundleSymbolicName) &&
			Validator.isNull(contextName)) {

			throw new ModuleNamespaceException();
		}
	}

}