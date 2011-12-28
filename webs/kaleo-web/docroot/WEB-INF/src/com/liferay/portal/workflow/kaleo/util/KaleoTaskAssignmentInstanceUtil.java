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

package com.liferay.portal.workflow.kaleo.util;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.workflow.WorkflowTaskAssignee;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class KaleoTaskAssignmentInstanceUtil {

	public static List<WorkflowTaskAssignee> getWorkflowTaskAssignees(
			KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws SystemException {

		List<KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances =
			kaleoTaskInstanceToken.getKaleoTaskAssignmentInstances();

		List<WorkflowTaskAssignee> workflowTaskAssignees =
			new ArrayList<WorkflowTaskAssignee>(
				kaleoTaskAssignmentInstances.size());

		for (KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance :
				kaleoTaskAssignmentInstances) {

			WorkflowTaskAssignee workflowTaskAssignee =
				new WorkflowTaskAssignee(
					kaleoTaskAssignmentInstance.getAssigneeClassName(),
					kaleoTaskAssignmentInstance.getAssigneeClassPK());

			workflowTaskAssignees.add(workflowTaskAssignee);
		}

		return workflowTaskAssignees;
	}

}