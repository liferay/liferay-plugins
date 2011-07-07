/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.service.KaleoTaskAssignmentInstanceLocalServiceUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Marcellus Tavares
 */
public class TaskAssignerUtil {

	public static void reassignKaleoTask(
			List<KaleoTaskAssignment> kaleoTaskReassignments,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		List<KaleoTaskAssignment> kaleoTaskAssignments =
			new ArrayList<KaleoTaskAssignment>();

		for (KaleoTaskAssignment kaleoTaskReassignment :
				kaleoTaskReassignments) {

			Collection<KaleoTaskAssignment> calculatedKaleoTaskAssignments =
				_taskAssignmentSelector.calculateTaskAssignments(
					kaleoTaskReassignment, executionContext);

			kaleoTaskAssignments.addAll(calculatedKaleoTaskAssignments);
		}

		KaleoTaskInstanceToken kaleoTaskInstanceToken =
			executionContext.getKaleoTaskInstanceToken();

		KaleoTaskAssignmentInstanceLocalServiceUtil.
			deleteKaleoTaskAssignmentInstances(kaleoTaskInstanceToken);

		KaleoTaskAssignmentInstanceLocalServiceUtil.addTaskAssignmentInstances(
			kaleoTaskInstanceToken, kaleoTaskAssignments,
			executionContext.getWorkflowContext(),
			executionContext.getServiceContext());
	}

	public void setTaskAssignmentSelector(
		TaskAssignmentSelector taskAssignmentSelector) {

		_taskAssignmentSelector = taskAssignmentSelector;
	}

	private static TaskAssignmentSelector _taskAssignmentSelector;

}