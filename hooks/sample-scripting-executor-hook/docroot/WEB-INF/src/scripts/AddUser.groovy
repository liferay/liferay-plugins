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

import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.util.PortletKeys;

import com.liferay.scriptingexecutor.scripts.groovy.GroovyRole;
import com.liferay.scriptingexecutor.scripts.groovy.GroovyScriptingContext;
import com.liferay.scriptingexecutor.scripts.groovy.GroovySite;
import com.liferay.scriptingexecutor.scripts.groovy.GroovyUser;

GroovyScriptingContext groovyScriptingContext = new GroovyScriptingContext();

GroovyRole groovyRole = GroovyRole.portalRole(
	"Sample Groovy Role", "This is a sample Groovy role.");

groovyRole.create(groovyScriptingContext);

String[] resourceActions = [ActionKeys.CONFIGURATION, ActionKeys.VIEW];

groovyRole.updatePermissions(
	PortletKeys.ACTIVITIES, resourceActions, true, groovyScriptingContext);

GroovySite groovySite = GroovySite.openSite(
	"Sample Groovy Site", "This is a sample Groovy site.");

groovySite.create(groovyScriptingContext);

GroovyUser groovyUser = new GroovyUser(
	"test.user@liferay.com", "test",  "Test", "User", "Web Admin");

groovyUser.create(groovyScriptingContext);

groovyUser.addRoles(groovyScriptingContext, groovyRole.name);

groovyUser.joinSites(groovyScriptingContext, groovySite.name);