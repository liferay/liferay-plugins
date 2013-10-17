/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.scriptingexecutor.scripts.groovy

import com.liferay.portal.model.GroupConstants
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants
import com.liferay.portal.service.ResourceBlockLocalServiceUtil
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil
import com.liferay.portal.service.RoleLocalServiceUtil;

/**
 * @author Michael C. Han
 */
class GroovyRole {

	static GroovyRole organizationRole(String name, String description) {
		GroovyRole groovyRole = new GroovyRole();

		groovyRole.name = name;
		groovyRole.description = description;
		groovyRole.type = RoleConstants.TYPE_ORGANIZATION;

		return groovyRole;
	}

	static GroovyRole portalRole(String name, String description) {
		GroovyRole groovyRole = new GroovyRole();

		groovyRole.name = name;
		groovyRole.description = description;
		groovyRole.type = RoleConstants.TYPE_REGULAR;

		return groovyRole;
	}

	static GroovyRole siteRole(String name, String description) {
		GroovyRole groovyRole = new GroovyRole();

		groovyRole.name = name;
		groovyRole.description = description;
		groovyRole.type = RoleConstants.TYPE_SITE;

		return groovyRole;
	}

	void create(GroovyScriptingContext scriptingContext) {
		role = RoleLocalServiceUtil.fetchRole(scriptingContext.companyId, name);

		if (role != null) {
			return;
		}

		role = RoleLocalServiceUtil.addRole(
			scriptingContext.defaultUserId, null, 0, name,
			GroovyScriptingContext.getLocalizationMap(name),
			GroovyScriptingContext.getLocalizationMap(description), type, null,
			scriptingContext.serviceContext);
	}

	void updatePermissions(
		String resourceName, String[] resourceActions, boolean add,
		GroovyScriptingContext scriptingContext) {

		boolean resourceBlockSupported =
			ResourceBlockLocalServiceUtil.isSupported(resourceName);

		int scope = ResourceConstants.SCOPE_COMPANY;

		if ((role.getType() == RoleConstants.TYPE_SITE) ||
			(role.getType() == RoleConstants.TYPE_ORGANIZATION)) {

			scope = ResourceConstants.SCOPE_GROUP_TEMPLATE;
		}

		for (String resourceAction : resourceActions) {
			if (add) {
				if (resourceBlockSupported) {
					ResourceBlockLocalServiceUtil.addCompanyScopePermission(
						scriptingContext.companyId, resourceName,
						role.getRoleId(), resourceAction);
				}
				else {
					if (scope == ResourceConstants.SCOPE_COMPANY) {
						ResourcePermissionLocalServiceUtil.
							addResourcePermission(
								scriptingContext.companyId, resourceName, scope,
								String.valueOf(role.getCompanyId()),
								role.getRoleId(), resourceAction);
					}
					else if (scope == ResourceConstants.SCOPE_GROUP_TEMPLATE) {
						ResourcePermissionLocalServiceUtil.
							addResourcePermission(
								scriptingContext.companyId, resourceName, scope,
								String.valueOf(
									GroupConstants.DEFAULT_PARENT_GROUP_ID),
								role.getRoleId(), resourceAction);
					}
				}
			}
			else {
				if (resourceBlockSupported) {
					ResourceBlockLocalServiceUtil.removeCompanyScopePermission(
						scriptingContext.companyId, resourceName,
						role.getRoleId(), resourceAction);
				}
				else {
					ResourcePermissionLocalServiceUtil.
						removeResourcePermissions(
							scriptingContext.companyId, resourceName, scope,
							role.getRoleId(), resourceAction);
				}
			}
		}
	}

	String description;
	String name;
	Role role;
	int type;

}