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

package com.liferay.portal.workflow.kaleo.runtime.assignment;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.ResourceAction;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class MultiLanguageTaskAssignmentSelector
	extends BaseTaskAssignmentSelector {

	public Collection<KaleoTaskAssignment> calculateTaskAssignments(
			KaleoTaskAssignment kaleoTaskAssignment,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		String assigneeClassName = kaleoTaskAssignment.getAssigneeClassName();

		TaskAssignmentSelector taskAssignmentSelector = null;

		if (assigneeClassName.equals(ResourceAction.class.getName())) {
			taskAssignmentSelector = _taskAssignmentSelectors.get(
				assigneeClassName);
		}
		else {
			String assigneeScriptLanguage =
				kaleoTaskAssignment.getAssigneeScriptLanguage();

			taskAssignmentSelector = _taskAssignmentSelectors.get(
				assigneeScriptLanguage);
		}

		if (taskAssignmentSelector == null) {
			throw new IllegalArgumentException(
				"No task assignment selector found for " +
					kaleoTaskAssignment.toXmlString());
		}

		return taskAssignmentSelector.calculateTaskAssignments(
			kaleoTaskAssignment, executionContext);
	}

	public void setTaskAssignmentSelectors(
		Map<String, TaskAssignmentSelector> taskAssignmentSelectors) {

		_taskAssignmentSelectors.putAll(taskAssignmentSelectors);
	}

	private Map<String, TaskAssignmentSelector> _taskAssignmentSelectors =
		new HashMap<String, TaskAssignmentSelector>();

}