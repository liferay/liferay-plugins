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

package com.liferay.portal.workflow.jbpm.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.jbpm.Assignee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jbpm.taskmgmt.exe.PooledActor;

/**
 * <a href="AssigneeRetrievalUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Marcellus Tavares
 */
public class AssigneeRetrievalUtil {

	public static List<Assignee> getAssignees(
			long companyId, String actorId, Set<PooledActor> pooledActors)
		throws PortalException, SystemException {

		List<Assignee> assignees = new ArrayList<Assignee>();

		if ((pooledActors != null) && !pooledActors.isEmpty()) {
			Iterator<PooledActor> it = pooledActors.iterator();

			while (it.hasNext()) {
				PooledActor pooledActor = it.next();

				Role role = null;

				if (Validator.isNumber(pooledActor.getActorId())) {
					role = RoleLocalServiceUtil.getRole(
						GetterUtil.getLong(pooledActor.getActorId()));
				}
				else {
					ServiceContext serviceContext = new ServiceContext();

					serviceContext.setCompanyId(companyId);

					role = RoleRetrievalUtil.getRole(
						pooledActor.getActorId(), true, serviceContext);
				}

				assignees.add(
					new Assignee(Role.class.getName(), role.getRoleId()));
			}
		}
		else {
			User user = null;

			if (Validator.isEmailAddress(actorId)) {
				user = UserLocalServiceUtil.getUserByEmailAddress(
					companyId, actorId);
			}
			else {
				user = UserLocalServiceUtil.getUser(
					GetterUtil.getLong(actorId));
			}

			assignees.add(
				new Assignee(User.class.getName(), user.getUserId()));
		}

		return assignees;
	}

}