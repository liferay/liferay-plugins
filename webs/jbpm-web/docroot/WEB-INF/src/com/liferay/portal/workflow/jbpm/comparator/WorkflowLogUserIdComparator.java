/**
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

package com.liferay.portal.workflow.jbpm.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowLog;

/**
 * <a href="WorkflowLogUserIdComparator.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowLogUserIdComparator extends OrderByComparator {

	public static String ORDER_BY_ASC = "userId ASC, workflowLogId ASC";

	public static String ORDER_BY_DESC = "userId DESC, workflowLogId DESC";

	public static String[] ORDER_BY_FIELDS = {"userId", "workflowLogId"};

	public WorkflowLogUserIdComparator() {
		this(false);
	}

	public WorkflowLogUserIdComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object obj1, Object obj2) {
		WorkflowLog workflowLog1 = (WorkflowLog)obj1;
		WorkflowLog workflowLog2 = (WorkflowLog)obj2;

		Long userId1 = workflowLog1.getUserId();
		Long userId2 = workflowLog2.getUserId();

		int value = userId1.compareTo(userId2);

		if (value == 0) {
			Long workflowLogId1 = workflowLog1.getWorkflowLogId();
			Long workflowLogId2 = workflowLog2.getWorkflowLogId();

			value = workflowLogId1.compareTo(workflowLogId2);
		}

		if (_asc) {
			return value;
		}
		else {
			return -value;
		}
	}

	public String getOrderBy() {
		if (_asc) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	public boolean isAscending() {
		return _asc;
	}

	private boolean _asc;

}