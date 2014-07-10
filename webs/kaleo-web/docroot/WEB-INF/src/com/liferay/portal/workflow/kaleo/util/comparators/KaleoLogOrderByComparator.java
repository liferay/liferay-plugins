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

package com.liferay.portal.workflow.kaleo.util.comparators;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorAdapter;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.util.WorkflowModelUtil;

/**
 * @author William Newbury
 */
public class KaleoLogOrderByComparator
	extends OrderByComparatorAdapter<KaleoLog, WorkflowLog> {

	public static OrderByComparator<KaleoLog> getOrderByComparator(
		OrderByComparator<WorkflowLog> orderByComparator) {

		if (orderByComparator == null) {
			return null;
		}

		return new KaleoLogOrderByComparator(orderByComparator);
	}

	@Override
	public WorkflowLog adapt(KaleoLog kaleoLog) {
		return WorkflowModelUtil.toWorkflowLog(kaleoLog);
	}

	private KaleoLogOrderByComparator(
		OrderByComparator<WorkflowLog> orderByComparator) {

		super(orderByComparator);
	}

}