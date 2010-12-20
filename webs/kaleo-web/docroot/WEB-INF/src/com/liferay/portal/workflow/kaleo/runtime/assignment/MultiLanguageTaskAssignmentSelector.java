/*
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
			KaleoTaskAssignment configuredKaleoTaskAssignment,
			ExecutionContext executionContext)
		throws PortalException, SystemException {

		String assigneeClassName =
			configuredKaleoTaskAssignment.getAssigneeClassName();

		TaskAssignmentSelector assignmentEvaluator = null;

		if (assigneeClassName.equals(ResourceAction.class.getName())) {
			assignmentEvaluator = _taskAssignmentSelectors.get(
				assigneeClassName);
		}
		else {
			String scriptLanguage =
				configuredKaleoTaskAssignment.getAssigneeScriptLanguage();

			assignmentEvaluator = _taskAssignmentSelectors.get(scriptLanguage);

		}

		if (assignmentEvaluator == null) {
			throw new IllegalArgumentException(
				"No assignment evaluator found for " +
					configuredKaleoTaskAssignment.toXmlString());
		}

		return assignmentEvaluator.calculateTaskAssignments(
			configuredKaleoTaskAssignment, executionContext);
	}

	public void setTaskAssignmentSelectors(
		Map<String, TaskAssignmentSelector> conditionEvaluators) {

		for (Map.Entry<String, TaskAssignmentSelector> entry :
				conditionEvaluators.entrySet()) {

			_taskAssignmentSelectors.put(entry.getKey(), entry.getValue());
		}
	}

	private Map<String, TaskAssignmentSelector> _taskAssignmentSelectors =
		new HashMap<String, TaskAssignmentSelector>();

}