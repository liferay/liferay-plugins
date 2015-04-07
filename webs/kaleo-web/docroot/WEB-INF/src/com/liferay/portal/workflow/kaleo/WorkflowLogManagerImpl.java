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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManager;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.util.WorkflowModelUtil;
import com.liferay.portal.workflow.kaleo.util.comparators.KaleoLogOrderByComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael C. Han
 */
public class WorkflowLogManagerImpl implements WorkflowLogManager {

	@Override
	public int getWorkflowLogCountByWorkflowInstance(
			long companyId, long workflowInstanceId, List<Integer> logTypes)
		throws WorkflowException {

		try {
			return KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogsCount(
				workflowInstanceId, logTypes);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public int getWorkflowLogCountByWorkflowTask(
			long companyId, long workflowTaskId, List<Integer> logTypes)
		throws WorkflowException {

		try {
			return KaleoLogLocalServiceUtil.
				getKaleoTaskInstanceTokenKaleoLogsCount(
					workflowTaskId, logTypes);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
			long companyId, long workflowInstanceId, List<Integer> logTypes,
			int start, int end,
			OrderByComparator<WorkflowLog> orderByComparator)
		throws WorkflowException {

		try {
			List<KaleoLog> kaleoLogs =
				KaleoLogLocalServiceUtil.getKaleoInstanceKaleoLogs(
					workflowInstanceId, logTypes, start, end,
					KaleoLogOrderByComparator.getOrderByComparator(
						orderByComparator));

			return toWorkflowLogs(kaleoLogs);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	@Override
	public List<WorkflowLog> getWorkflowLogsByWorkflowTask(
			long companyId, long workflowTaskId, List<Integer> logTypes,
			int start, int end,
			OrderByComparator<WorkflowLog> orderByComparator)
		throws WorkflowException {

		try {
			List<KaleoLog> kaleoLogs =
				KaleoLogLocalServiceUtil.getKaleoTaskInstanceTokenKaleoLogs(
					workflowTaskId, logTypes, start, end,
					KaleoLogOrderByComparator.getOrderByComparator(
						orderByComparator));

			return toWorkflowLogs(kaleoLogs);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	protected List<WorkflowLog> toWorkflowLogs(List<KaleoLog> kaleoLogs) {
		List<WorkflowLog> workflowLogs = new ArrayList<>(kaleoLogs.size());

		for (KaleoLog kaleoLog : kaleoLogs) {
			workflowLogs.add(WorkflowModelUtil.toWorkflowLog(kaleoLog));
		}

		return workflowLogs;
	}

}