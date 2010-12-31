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

package com.liferay.portal.workflow.jbpm.comparator;

import com.liferay.portal.kernel.workflow.comparator.BaseWorkflowInstanceStateComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceStateComparator
	extends BaseWorkflowInstanceStateComparator {

	public static String ORDER_BY_ASC = "state ASC, workflowInstanceId ASC";

	public static String ORDER_BY_DESC = "state DESC, workflowInstanceId DESC";

	public static String[] ORDER_BY_FIELDS = {
		"state", "workflowInstanceId"
	};

	public WorkflowInstanceStateComparator() {
		super();
	}

	public WorkflowInstanceStateComparator(boolean ascending) {
		super(ascending);
	}

	public String getOrderBy() {
		if (isAscending()) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

}