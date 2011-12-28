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

package com.liferay.portal.workflow.kaleo.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactory;

/**
 * @author Michael C. Han
 */
public class WorkflowComparatorFactoryImpl
	implements WorkflowComparatorFactory {

	public OrderByComparator getDefinitionNameComparator(boolean ascending) {
		return new WorkflowDefinitionNameComparator(ascending);
	}

	public OrderByComparator getInstanceEndDateComparator(boolean ascending) {
		return new WorkflowInstanceEndDateComparator(ascending);
	}

	public OrderByComparator getInstanceStartDateComparator(boolean ascending) {
		return new WorkflowInstanceStartDateComparator(ascending);
	}

	public OrderByComparator getInstanceStateComparator(boolean ascending) {
		return new WorkflowInstanceStateComparator(ascending);
	}

	public OrderByComparator getLogCreateDateComparator(boolean ascending) {
		return new WorkflowLogCreateDateComparator(ascending);
	}

	public OrderByComparator getLogUserIdComparator(boolean ascending) {
		return new WorkflowLogUserIdComparator(ascending);
	}

	public OrderByComparator getTaskCompletionDateComparator(
		boolean ascending) {

		return new WorkflowTaskCompletionDateComparator(ascending);
	}

	public OrderByComparator getTaskCreateDateComparator(boolean ascending) {
		return new WorkflowTaskCreateDateComparator(ascending);
	}

	public OrderByComparator getTaskDueDateComparator(boolean ascending) {
		return new WorkflowTaskDueDateComparator(ascending);
	}

	public OrderByComparator getTaskNameComparator(boolean ascending) {
		return new WorkflowTaskNameComparator(ascending);
	}

	public OrderByComparator getTaskUserIdComparator(boolean ascending) {
		return new WorkflowTaskUserIdComparator(ascending);
	}

}