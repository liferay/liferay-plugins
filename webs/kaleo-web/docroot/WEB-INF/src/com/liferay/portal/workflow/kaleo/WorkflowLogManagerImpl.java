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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.kernel.workflow.WorkflowLogManager;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="WorkflowLogManagerImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class WorkflowLogManagerImpl implements WorkflowLogManager {

	public int getWorkflowLogCount(long companyId, long workflowTaskInstanceId)
		throws WorkflowException {

		try {
			return KaleoLogLocalServiceUtil.getKaleoLogsCount(
				workflowTaskInstanceId);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public int getWorkflowLogCountByWorkflowInstance(
			long companyId, long workflowInstanceId)
		throws WorkflowException {

		try {
			return KaleoLogLocalServiceUtil.getKaleoLogsCount(
				workflowInstanceId);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowLog> getWorkflowLogs(
			long companyId, long workflowTaskInstanceId, int start, int end,
			OrderByComparator orderByComparator)
		throws WorkflowException {

		try {
			List<KaleoLog> kaleoLogs = KaleoLogLocalServiceUtil.getKaleoLogs(
				workflowTaskInstanceId, start, end, orderByComparator);

			return toWorkflowLogs(kaleoLogs);
		}
		catch (Exception e) {
			throw new WorkflowException(e);
		}
	}

	public List<WorkflowLog> getWorkflowLogsByWorkflowInstance(
		long companyId, long workflowInstanceId, int start, int end,
		OrderByComparator orderByComparator) throws WorkflowException {
		throw new UnsupportedOperationException();

	}

	protected List<WorkflowLog> toWorkflowLogs(List<KaleoLog> kaleoLogs) {
		List<WorkflowLog> workflowLogs = new ArrayList<WorkflowLog>(
			kaleoLogs.size());

		for (KaleoLog kaleoLog : kaleoLogs) {
			workflowLogs.add(new WorkflowLogAdapter(kaleoLog));
		}

		return workflowLogs;
	}

}