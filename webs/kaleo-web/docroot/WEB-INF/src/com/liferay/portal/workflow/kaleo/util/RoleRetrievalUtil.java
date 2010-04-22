/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Role;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="RoleRetrievalUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class RoleRetrievalUtil {

	public static List<Long> getRoleIds(ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<Role> roles = RoleLocalServiceUtil.getUserRoles(
			serviceContext.getUserId());

		List<Group> groups = GroupLocalServiceUtil.getUserGroups(
			serviceContext.getUserId(), true);

		List<Role> relatedRoles = RoleLocalServiceUtil.getUserRelatedRoles(
			serviceContext.getUserId(), groups);

		List<Long> roleIds = new ArrayList<Long>(
			roles.size() + relatedRoles.size());

		for (Role role : roles) {
			roleIds.add(role.getRoleId());
		}

		for (Role relatedRole : relatedRoles) {
			roleIds.add(relatedRole.getRoleId());
		}

		return roleIds;
	}

}