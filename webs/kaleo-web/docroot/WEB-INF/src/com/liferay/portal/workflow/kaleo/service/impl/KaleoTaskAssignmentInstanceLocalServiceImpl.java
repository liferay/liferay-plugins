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

package com.liferay.portal.workflow.kaleo.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskAssignmentInstanceLocalServiceBaseImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class KaleoTaskAssignmentInstanceLocalServiceImpl
	extends KaleoTaskAssignmentInstanceLocalServiceBaseImpl {

	@Override
	public KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
			long groupId, KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String assigneeClassName, long assigneeClassPK,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoTaskAssignmentInstanceId = counterLocalService.increment();

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
			kaleoTaskAssignmentInstancePersistence.create(
				kaleoTaskAssignmentInstanceId);

		kaleoTaskAssignmentInstance.setGroupId(groupId);
		kaleoTaskAssignmentInstance.setCompanyId(user.getCompanyId());
		kaleoTaskAssignmentInstance.setUserId(user.getUserId());
		kaleoTaskAssignmentInstance.setUserName(user.getFullName());
		kaleoTaskAssignmentInstance.setCreateDate(now);
		kaleoTaskAssignmentInstance.setModifiedDate(now);
		kaleoTaskAssignmentInstance.setKaleoDefinitionId(
			kaleoTaskInstanceToken.getKaleoDefinitionId());
		kaleoTaskAssignmentInstance.setKaleoInstanceId(
			kaleoTaskInstanceToken.getKaleoInstanceId());
		kaleoTaskAssignmentInstance.setKaleoTaskInstanceTokenId(
			kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());
		kaleoTaskAssignmentInstance.setKaleoTaskId(
			kaleoTaskInstanceToken.getKaleoTaskId());
		kaleoTaskAssignmentInstance.setKaleoTaskName(
			kaleoTaskInstanceToken.getKaleoTaskName());
		kaleoTaskAssignmentInstance.setAssigneeClassName(assigneeClassName);

		if ((assigneeClassPK == 0) &&
			assigneeClassName.equals(User.class.getName())) {

			KaleoInstance kaleoInstance =
				kaleoInstancePersistence.findByPrimaryKey(
					kaleoTaskInstanceToken.getKaleoInstanceId());

			kaleoTaskAssignmentInstance.setAssigneeClassPK(
				kaleoInstance.getUserId());
		}
		else {
			kaleoTaskAssignmentInstance.setAssigneeClassPK(assigneeClassPK);
		}

		kaleoTaskAssignmentInstance.setCompleted(false);

		kaleoTaskAssignmentInstancePersistence.update(
			kaleoTaskAssignmentInstance);

		return kaleoTaskAssignmentInstance;
	}

	@Override
	public List<KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTaskAssignment> kaleoTaskAssignments,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException {

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			new ArrayList<>(kaleoTaskAssignments.size());

		for (KaleoTaskAssignment kaleoTaskAssignment : kaleoTaskAssignments) {
			long groupId = kaleoTaskAssignment.getGroupId();

			if (groupId <= 0) {
				groupId = kaleoTaskInstanceToken.getGroupId();
			}

			KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
				addKaleoTaskAssignmentInstance(
					groupId, kaleoTaskInstanceToken,
					kaleoTaskAssignment.getAssigneeClassName(),
					kaleoTaskAssignment.getAssigneeClassPK(), serviceContext);

			kaleoTaskAssignmentInstances.add(kaleoTaskAssignmentInstance);
		}

		return kaleoTaskAssignmentInstances;
	}

	@Override
	public KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String assigneeClassName, long assigneeClassPK,
			ServiceContext serviceContext)
		throws PortalException {

		deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
			addKaleoTaskAssignmentInstance(
				kaleoTaskInstanceToken.getGroupId(), kaleoTaskInstanceToken,
				assigneeClassName, assigneeClassPK, serviceContext);

		return kaleoTaskAssignmentInstance;
	}

	@Override
	public KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException {

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskAssignmentInstancePersistence.
				findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);

		if (kaleoTaskAssignmentInstances.size() > 1) {
			throw new WorkflowException(
				"Cannot complete a task that is not assigned to an " +
					"individual user");
		}

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
			kaleoTaskAssignmentInstances.get(0);

		kaleoTaskAssignmentInstance.setCompleted(true);
		kaleoTaskAssignmentInstance.setCompletionDate(new Date());

		kaleoTaskAssignmentInstancePersistence.update(
			kaleoTaskAssignmentInstance);

		return kaleoTaskAssignmentInstance;
	}

	@Override
	public void deleteCompanyKaleoTaskAssignmentInstances(long companyId) {
		kaleoTaskAssignmentInstancePersistence.removeByCompanyId(companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoTaskAssignmentInstances(
		long kaleoDefintionId) {

		kaleoTaskAssignmentInstancePersistence.removeByKaleoDefinitionId(
			kaleoDefintionId);
	}

	@Override
	public void deleteKaleoInstanceKaleoTaskAssignmentInstances(
		long kaleoInstanceId) {

		kaleoTaskAssignmentInstancePersistence.removeByKaleoInstanceId(
			kaleoInstanceId);
	}

	@Override
	public void deleteKaleoTaskAssignmentInstances(
		KaleoTaskInstanceToken kaleoTaskInstanceToken) {

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskAssignmentInstancePersistence.
				findBykaleoTaskInstanceTokenId(
					kaleoTaskInstanceToken.getKaleoTaskInstanceTokenId());

		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance :
				kaleoTaskAssignmentInstances) {

			kaleoTaskAssignmentInstancePersistence.remove(
				kaleoTaskAssignmentInstance);
		}
	}

	@Override
	public List<KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long kaleoTaskInstanceTokenId) {

		return kaleoTaskAssignmentInstancePersistence.
			findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

}