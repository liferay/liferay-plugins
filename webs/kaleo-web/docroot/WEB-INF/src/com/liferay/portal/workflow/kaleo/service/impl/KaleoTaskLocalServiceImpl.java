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
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.definition.Assignment;
import com.liferay.portal.workflow.kaleo.definition.Task;
import com.liferay.portal.workflow.kaleo.model.KaleoTask;
import com.liferay.portal.workflow.kaleo.service.base.KaleoTaskLocalServiceBaseImpl;

import java.util.Date;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTaskLocalServiceImpl extends KaleoTaskLocalServiceBaseImpl {

	@Override
	public KaleoTask addKaleoTask(
			long kaleoDefinitionId, long kaleoNodeId, Task task,
			ServiceContext serviceContext)
		throws PortalException {

		// Kaleo task

		User user = userPersistence.findByPrimaryKey(
			serviceContext.getGuestOrUserId());
		Date now = new Date();

		long kaleoTaskId = counterLocalService.increment();

		KaleoTask kaleoTask = kaleoTaskPersistence.create(kaleoTaskId);

		kaleoTask.setCompanyId(user.getCompanyId());
		kaleoTask.setUserId(user.getUserId());
		kaleoTask.setUserName(user.getFullName());
		kaleoTask.setCreateDate(now);
		kaleoTask.setModifiedDate(now);
		kaleoTask.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoTask.setKaleoNodeId(kaleoNodeId);
		kaleoTask.setName(task.getName());

		kaleoTaskPersistence.update(kaleoTask);

		// Kaleo assignments

		Set<Assignment> assignments = task.getAssignments();

		for (Assignment assignment : assignments) {
			kaleoTaskAssignmentLocalService.addKaleoTaskAssignment(
				KaleoTask.class.getName(), kaleoTaskId, kaleoDefinitionId,
				assignment, serviceContext);
		}

		return kaleoTask;
	}

	@Override
	public void deleteCompanyKaleoTasks(long companyId) {

		// Kaleo tasks

		kaleoTaskPersistence.removeByCompanyId(companyId);

		// Kaleo task assignments

		kaleoTaskAssignmentLocalService.deleteCompanyKaleoTaskAssignments(
			companyId);
	}

	@Override
	public void deleteKaleoDefinitionKaleoTasks(long kaleoDefinitionId) {

		// Kaleo tasks

		kaleoTaskPersistence.removeByKaleoDefinitionId(kaleoDefinitionId);

		// Kaleo task assignments

		kaleoTaskAssignmentLocalService.
			deleteKaleoDefinitionKaleoTaskAssignments(kaleoDefinitionId);
	}

	@Override
	public KaleoTask getKaleoNodeKaleoTask(long kaleoNodeId)
		throws PortalException {

		return kaleoTaskPersistence.findByKaleoNodeId(kaleoNodeId);
	}

}