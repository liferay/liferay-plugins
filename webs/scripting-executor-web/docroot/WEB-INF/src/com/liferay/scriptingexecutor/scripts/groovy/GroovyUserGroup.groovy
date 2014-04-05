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

package com.liferay.scriptingexecutor.scripts.groovy;

import com.liferay.portal.NoSuchUserGroupException;
import com.liferay.portal.model.UserGroup;
import com.liferay.portal.service.UserGroupLocalServiceUtil;

/**
 * @author Michael C. Han
 */
class GroovyUserGroup {

	static UserGroup fetchUserGroup(
		GroovyScriptingContext scriptingContext, String name) {

		try {
			return UserGroupLocalServiceUtil.getUserGroup(
				scriptingContext.companyId, name);
		}
		catch (NoSuchUserGroupException nsuge) {
		}

		return null;
	}

}