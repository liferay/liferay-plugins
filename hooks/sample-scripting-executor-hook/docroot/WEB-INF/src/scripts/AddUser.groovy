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

import com.liferay.scriptingexecutor.scripts.groovy.GroovyRole
import com.liferay.scriptingexecutor.scripts.groovy.GroovyScriptingContext
import com.liferay.scriptingexecutor.scripts.groovy.GroovySite
import com.liferay.scriptingexecutor.scripts.groovy.GroovyUser

 
GroovyScriptingContext scriptingContext = new GroovyScriptingContext();
 
def testRole = GroovyRole.portalRole("testRole", "Testing this role");
testRole.create(scriptingContext);
 
def site1 = GroovySite.openSite("HR GroovySite", "My first site");
site1.create(scriptingContext);
 
def user1 = new GroovyUser(
	"test.user@liferay.com", "test",  "Test", "User", "Web Admin");
 
user1.create(scriptingContext);
 
user1.addRoles(scriptingContext, testRole.name);
 
user1.joinSites(scriptingContext, site1.name);
