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

package com.liferay.sampleservicebuilder.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.sampleservicebuilder.service.base.FooServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class FooServiceImpl extends FooServiceBaseImpl {

	public User getUser(long userId) throws PortalException {
		return UserLocalServiceUtil.getUserById(userId);
	}

	public List<Group> getUserSitesGroups() throws PortalException {
		return GroupServiceUtil.getUserSitesGroups();
	}

}