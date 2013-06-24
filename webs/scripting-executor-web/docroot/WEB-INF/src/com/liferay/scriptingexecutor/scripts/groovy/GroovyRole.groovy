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

package com.liferay.scriptingexecutor.scripts.groovy;

import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
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
			scriptingContext.defaultUserId, scriptingContext.companyId, name,
			GroovyScriptingContext.getLocalizationMap(name),
			GroovyScriptingContext.getLocalizationMap(description), type);
	}

	String description;
	String name;
	Role role;
	int type;

}