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

package com.liferay.portal.workflow.kaleo.comparator;

import com.liferay.portal.kernel.workflow.comparator.BaseWorkflowLogUserIdComparator;

/**
 * @author Shuyang Zhou
 */
public class WorkflowLogUserIdComparator
	extends BaseWorkflowLogUserIdComparator {

	public static String ORDER_BY_ASC = "userId ASC, kaleoLogId ASC";

	public static String ORDER_BY_DESC = "userId DESC, kaleoLogId DESC";

	public static String[] ORDER_BY_FIELDS = {"userId", "kaleoLogId"};

	public WorkflowLogUserIdComparator() {
		super();
	}

	public WorkflowLogUserIdComparator(boolean asc) {
		super(asc);
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