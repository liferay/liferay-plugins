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
import com.liferay.portal.kernel.workflow.comparator.WorkflowComparatorFactory;

/**
 * @author Michael C. Han
 */
public class WorkflowComparatorFactoryImpl
	implements WorkflowComparatorFactory {

	@Override
	public OrderByComparator getDefinitionNameComparator(boolean ascending) {
		return new WorkflowDefinitionNameComparator(ascending);
	}

	@Override
	public OrderByComparator getInstanceEndDateComparator(boolean ascending) {
		return new WorkflowInstanceEndDateComparator(ascending);
	}

	@Override
	public OrderByComparator getInstanceStartDateComparator(boolean ascending) {
		return new WorkflowInstanceStartDateComparator(ascending);
	}

	@Override
	public OrderByComparator getInstanceStateComparator(boolean ascending) {
		return new WorkflowInstanceStateComparator(ascending);
	}

	@Override
	public OrderByComparator getLogCreateDateComparator(boolean ascending) {
		return new WorkflowLogCreateDateComparator(ascending);
	}

	@Override
	public OrderByComparator getLogUserIdComparator(boolean ascending) {
		return new WorkflowLogUserIdComparator(ascending);
	}

	@Override
	public OrderByComparator getTaskCompletionDateComparator(
		boolean ascending) {

		return new WorkflowTaskCompletionDateComparator(ascending);
	}

	@Override
	public OrderByComparator getTaskCreateDateComparator(boolean ascending) {
		return new WorkflowTaskCreateDateComparator(ascending);
	}

	@Override
	public OrderByComparator getTaskDueDateComparator(boolean ascending) {
		return new WorkflowTaskDueDateComparator(ascending);
	}

	@Override
	public OrderByComparator getTaskNameComparator(boolean ascending) {
		return new WorkflowTaskNameComparator(ascending);
	}

	@Override
	public OrderByComparator getTaskUserIdComparator(boolean ascending) {
		return new WorkflowTaskUserIdComparator(ascending);
	}

}