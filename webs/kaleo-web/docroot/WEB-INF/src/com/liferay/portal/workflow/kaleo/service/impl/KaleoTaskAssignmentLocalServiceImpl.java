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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.AssignmentType;
import com.liferay.portal.workflow.kaleo.definition.RoleAssignment;
import com.liferay.portal.workflow.kaleo.definition.UserAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskAssignmentLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;

/**
 * <a href="KaleoTaskAssignmentLocalServiceImpl.java.html"><b><i>View Source</i>
 * </b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskAssignmentLocalServiceImpl
	extends KaleoTaskAssignmentLocalServiceBaseImpl {

	public KaleoTaskAssignment addKaleoTaskAssignment(
			long kaleoDefinitionId, long kaleoNodeId, long kaleoTaskId,
			Assignment assignment, ServiceContext serviceContext)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getUserId());
		Date now = new Date();

		long kaleoTaskAssignmentId = counterLocalService.increment();

		KaleoTaskAssignment kaleoTaskAssignment =
			kaleoTaskAssignmentPersistence.create(kaleoTaskAssignmentId);

		kaleoTaskAssignment.setCompanyId(user.getCompanyId());
		kaleoTaskAssignment.setUserId(user.getUserId());
		kaleoTaskAssignment.setUserName(user.getFullName());
		kaleoTaskAssignment.setCreateDate(now);
		kaleoTaskAssignment.setModifiedDate(now);
		kaleoTaskAssignment.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTaskAssignment.setKaleoNodeId(kaleoNodeId);
		kaleoTaskAssignment.setKaleoTaskId(kaleoTaskId);
		kaleoTaskAssignment.setDefaultAssignment(assignment.isDefault());
		setAssignee(kaleoTaskAssignment, assignment, serviceContext);

		kaleoTaskAssignmentPersistence.update(kaleoTaskAssignment, false);

		return kaleoTaskAssignment;
	}

	public KaleoTaskAssignment getDefaultKaleoTaskAssignment(long kaleoTaskId)
		throws PortalException, SystemException {

		return kaleoTaskAssignmentPersistence.findByKTI_DA(kaleoTaskId, true);
	}

	public List<KaleoTaskAssignment> getKaleoTaskAssignments(long kaleoTaskId)
		throws SystemException {

		return kaleoTaskAssignmentPersistence.findByKaleoTaskId(kaleoTaskId);
	}

	public List<KaleoTaskAssignment> getKaleoTaskAssignments(
			String assigneeClassName, long assigneeClassPK)
		throws SystemException {

		return kaleoTaskAssignmentPersistence.findByACN_ACP(
			assigneeClassName, assigneeClassPK);
	}

	public int getKaleoTaskAssignmentsCount(long kaleoTaskId)
		throws SystemException {

		return kaleoTaskAssignmentPersistence.countByKaleoTaskId(kaleoTaskId);
	}

	public int getKaleoTaskAssignmentsCount(
			String assigneeClassName, long assigneeClassPK)
		throws SystemException {

		return kaleoTaskAssignmentPersistence.countByACN_ACP(
			assigneeClassName, assigneeClassPK);
	}

	protected void setAssignee(
			KaleoTaskAssignment kaleoTaskAssignment, Assignment assignment,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		AssignmentType assignmentType = assignment.getAssignmentType();

		if (assignmentType.equals(AssignmentType.ROLE)) {
			kaleoTaskAssignment.setAssigneeClassName(Role.class.getName());

			RoleAssignment roleAssignment = (RoleAssignment)assignment;

			Role role = null;
			if (Validator.isNotNull(roleAssignment.getRoleName())) {
				role = roleLocalService.getRole(
					serviceContext.getCompanyId(), roleAssignment.getRoleName());
			}
			else {
				role = roleLocalService.getRole(roleAssignment.getRoleId());
			}

			kaleoTaskAssignment.setAssigneeClassPK(role.getClassPK());
		}
		else if (assignmentType.equals(AssignmentType.USER)) {
			kaleoTaskAssignment.setAssigneeClassName(User.class.getName());

			UserAssignment userAssignment = (UserAssignment)assignment;

			User user = null;

			if (userAssignment.getUserId() > 0) {
				user = userLocalService.getUser(userAssignment.getUserId());
			}
			else if (Validator.isNotNull(userAssignment.getScreenName())) {
				user = userLocalService.getUserByScreenName(
					serviceContext.getCompanyId(),
					userAssignment.getScreenName());
			}
			else {
				user = userLocalService.getUserByEmailAddress(
					serviceContext.getCompanyId(),
					userAssignment.getEmailAddress());
			}

			kaleoTaskAssignment.setAssigneeClassPK(user.getUserId());
		}
	}

}