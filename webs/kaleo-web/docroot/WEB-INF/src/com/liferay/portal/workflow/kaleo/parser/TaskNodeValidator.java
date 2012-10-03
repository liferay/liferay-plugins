/*
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

package com.liferay.portal.workflow.kaleo.parser;

import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.workflow.kaleo.definition.Definition;
import com.liferay.portal.workflow.kaleo.definition.Task;

/**
 * @author Michael C. Han
 */
public class TaskNodeValidator extends BaseNodeValidator<Task> {

	@Override
	protected void doValidate(Definition definition, Task task)
		throws WorkflowException {

		if (task.getIncomingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No incoming transition found for task " + task.getName());
		}

		if (task.getOutgoingTransitionsCount() == 0) {
			throw new WorkflowException(
				"No outgoing transition found for task " + task.getName());
		}

		if (task.getAssignments().size() == 0) {
			throw new WorkflowException(
				"No assignments for task " + task.getName());
		}
	}
}