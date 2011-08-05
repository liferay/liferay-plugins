/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Park
 */
public class ModuleLocalServiceImpl extends ModuleLocalServiceBaseImpl {

	public Module addModule(
			long userId, long appId, String contextName)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);

		Date now = new Date();

		long moduleId = counterLocalService.increment();

		Module module = modulePersistence.create(moduleId);

		module.setModuleId(moduleId);
		module.setAppId(appId);
		module.setContextName(contextName);

		modulePersistence.update(module, false);

		return module;
	}

	public Module fetchModule(long appId, String contextName)
		throws PortalException, SystemException {

		return modulePersistence.fetchByA_C(appId, contextName);
	}

	public List<Module> getAppModules(long appId)
		throws PortalException, SystemException {

		return modulePersistence.findByAppId(appId);
	}

	public List<Module> getAppModules(long appId, int start, int end)
		throws PortalException, SystemException {

		return modulePersistence.findByAppId(appId, start, end);
	}

	public int getAppModulesCount(long appId) throws SystemException {
		return modulePersistence.countByAppId(appId);
	}

	public Module getModule(long appId, String contextName)
		throws PortalException, SystemException {

		return modulePersistence.findByA_C(appId, contextName);
	}

	public List<Module> getModulesByContextName(String contextName)
		throws PortalException, SystemException {

		return modulePersistence.findByContextName(contextName);
	}

	public int getModulesByContextNameCount(String contextName)
		throws SystemException {

		return modulePersistence.countByContextName(contextName);
	}

}