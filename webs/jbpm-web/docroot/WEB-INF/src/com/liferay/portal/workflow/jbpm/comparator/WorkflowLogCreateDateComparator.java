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

import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.comparator.BaseWorkflowLogCreateDateComparator;

import java.util.Date;

/**
 * <a href="WorkflowLogCreateDateComparator.java.html"><b><i>View Source</i></b>
 * </a>
 *
 * @author Shuyang Zhou
 */
public class WorkflowLogCreateDateComparator
	extends BaseWorkflowLogCreateDateComparator {

	public static String ORDER_BY_ASC = "createDate ASC, workflowLogId ASC";

	public static String ORDER_BY_DESC = "createDate DESC, workflowLogId DESC";

	public static String[] ORDER_BY_FIELDS = {"createDate", "workflowLogId"};

	public WorkflowLogCreateDateComparator() {
		this(false);
	}

	public WorkflowLogCreateDateComparator(boolean asc) {
		_asc = asc;
	}

	public int compare(Object obj1, Object obj2) {
		WorkflowLog workflowLog1 = (WorkflowLog)obj1;
		WorkflowLog workflowLog2 = (WorkflowLog)obj2;

		Date createDate1 = workflowLog1.getCreateDate();
		Date createDate2 = workflowLog2.getCreateDate();

		int value = createDate1.compareTo(createDate2);

		if (value != 0) {
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