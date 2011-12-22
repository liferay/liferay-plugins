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

package com.liferay.marketplace.service.impl;

import com.liferay.marketplace.model.Module;
import com.liferay.marketplace.service.base.ModuleLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Ryan Park
 */
public class ModuleLocalServiceImpl extends ModuleLocalServiceBaseImpl {

	public Module addModule(long userId, long appId, String contextName)
		throws SystemException {

		long moduleId = counterLocalService.increment();

		Module module = modulePersistence.create(moduleId);

		module.setModuleId(moduleId);
		module.setAppId(appId);
		module.setContextName(contextName);

		modulePersistence.update(module, false);

		return module;
	}

	public Module fetchModule(long appId, String contextName)
		throws SystemException {

		return modulePersistence.fetchByA_C(appId, contextName);
	}

	public List<Module> getModules(long appId) throws SystemException {
		return modulePersistence.findByAppId(appId);
	}

}