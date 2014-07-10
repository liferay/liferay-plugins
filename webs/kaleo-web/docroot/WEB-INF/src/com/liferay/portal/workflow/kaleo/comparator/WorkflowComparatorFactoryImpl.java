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

package com.liferay.portal.workflow.kaleo.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowTask;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactory;
import com.liferay.portal.kernel.workflow.comparator.WorkflowDefinitionNameComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowInstanceEndDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowInstanceStartDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowInstanceStateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowLogCreateDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowLogUserIdComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskCompletionDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskCreateDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskDueDateComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskNameComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowTaskUserIdComparator;

/**
 * @author Michael C. Han
 */
public class WorkflowComparatorFactoryImpl
	implements WorkflowComparatorFactory {

	@Override
	public OrderByComparator<WorkflowDefinition> getDefinitionNameComparator(
		boolean ascending) {

		return new WorkflowDefinitionNameComparator(
			ascending, "name ASC, version ASC", "name DESC, version DESC",
			new String[] {"name", "version"});
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceEndDateComparator(
		boolean ascending) {

		return new WorkflowInstanceEndDateComparator(
			ascending, "endDate ASC, kaleoInstanceId ASC",
			"endDate DESC, kaleoInstanceId DESC",
			new String[] {"endDate", "kaleoInstanceId"});
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceStartDateComparator(
		boolean ascending) {

		return new WorkflowInstanceStartDateComparator(
			ascending, "createDate ASC, kaleoInstanceId ASC",
			"createDate DESC, kaleoInstanceId DESC",
			new String[] {"createDate", "kaleoInstanceId"});
	}

	@Override
	public OrderByComparator<WorkflowInstance> getInstanceStateComparator(
		boolean ascending) {

		return new WorkflowInstanceStateComparator(
			ascending, "state ASC, kaleoInstanceId ASC",
			"state DESC, kaleoInstanceId DESC",
			new String[] {"state", "kaleoInstanceId"});
	}

	@Override
	public OrderByComparator<WorkflowLog> getLogCreateDateComparator(
		boolean ascending) {

		return new WorkflowLogCreateDateComparator(
			ascending, "createDate ASC, kaleoLogId ASC",
			"createDate DESC, kaleoLogId DESC",
			new String[] {"createDate", "kaleoLogId"});
	}

	@Override
	public OrderByComparator<WorkflowLog> getLogUserIdComparator(
		boolean ascending) {

		return new WorkflowLogUserIdComparator(
			ascending, "userId ASC, kaleoLogId ASC",
			"userId DESC, kaleoLogId DESC",
			new String[] {"userId", "kaleoLogId"});
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskCompletionDateComparator(
		boolean ascending) {

		return new WorkflowTaskCompletionDateComparator(
			ascending, "completionDate ASC, kaleoTaskId ASC",
			"completionDate DESC, kaleoTaskId DESC",
			new String[] {"completionDate", "kaleoTaskId"});
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskCreateDateComparator(
		boolean ascending) {

		return new WorkflowTaskCreateDateComparator(
			ascending, "createDate ASC, kaleoTaskInstanceTokenId ASC",
			"createDate DESC, kaleoTaskInstanceTokenId DESC",
			new String[] {"createDate", "kaleoTaskInstanceTokenId"});
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskDueDateComparator(
		boolean ascending) {

		return new WorkflowTaskDueDateComparator(
			ascending, "dueDate ASC, modifiedDate ASC, kaleoTaskId ASC",
			"dueDate DESC, modifiedDate DESC, kaleoTaskId DESC",
			new String[] {"dueDate", "modifiedDate", "kaleoTaskId"});
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskNameComparator(
		boolean ascending) {

		return new WorkflowTaskNameComparator(
			ascending, "name ASC, kaleoTaskId ASC",
			"name DESC, kaleoTaskId DESC",
			new String[] {"name", "kaleoTaskId"});
	}

	@Override
	public OrderByComparator<WorkflowTask> getTaskUserIdComparator(
		boolean ascending) {

		return new WorkflowTaskUserIdComparator(
			ascending, "userId ASC, kaleoTaskId ASC",
			"userId DESC, kaleoTaskId DESC",
			new String[] {"userId", "kaleoTaskId"});
	}

}