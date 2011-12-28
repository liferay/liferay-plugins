/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

	public KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
			long groupId, KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String assigneeClassName, long assigneeClassPK,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

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
			kaleoTaskAssignmentInstance, false);

		return kaleoTaskAssignmentInstance;
	}

	public List<KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			Collection<KaleoTaskAssignment> kaleoTaskAssignments,
			Map<String, Serializable> workflowContext,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			new ArrayList<KaleoTaskAssignmentInstance>(
				kaleoTaskAssignments.size());

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

	public KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
			KaleoTaskInstanceToken kaleoTaskInstanceToken,
			String assigneeClassName, long assigneeClassPK,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);

		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance =
			addKaleoTaskAssignmentInstance(
				kaleoTaskInstanceToken.getGroupId(), kaleoTaskInstanceToken,
				assigneeClassName, assigneeClassPK, serviceContext);

		return kaleoTaskAssignmentInstance;
	}

	public KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
			long kaleoTaskInstanceTokenId, ServiceContext serviceContext)
		throws PortalException, SystemException {

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
			kaleoTaskAssignmentInstance, false);

		return kaleoTaskAssignmentInstance;
	}

	public void deleteCompanyKaleoTaskAssignmentInstances(long companyId)
		throws SystemException {

		kaleoTaskAssignmentInstancePersistence.removeByCompanyId(companyId);
	}

	public void deleteKaleoDefinitionKaleoTaskAssignmentInstances(
			long kaleoDefintionId)
		throws SystemException {

		kaleoTaskAssignmentInstancePersistence.removeByKaleoDefinitionId(
			kaleoDefintionId);
	}

	public void deleteKaleoInstanceKaleoTaskAssignmentInstances(
			long kaleoInstanceId)
		throws SystemException {

		kaleoTaskAssignmentInstancePersistence.removeByKaleoInstanceId(
			kaleoInstanceId);
	}

	public void deleteKaleoTaskAssignmentInstances(
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {

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

	public List<KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
			long kaleoTaskInstanceTokenId)
		throws SystemException {

		return kaleoTaskAssignmentInstancePersistence.
			findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

}