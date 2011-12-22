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

package com.liferay.portal.workflow.kaleo;

import com.liferay.portal.kernel.workflow.DefaultWorkflowLog;
import com.liferay.portal.model.Role;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;
import com.liferay.portal.workflow.kaleo.util.KaleoLogUtil;

/**
 * @author Michael C. Han
 */
public class WorkflowLogAdapter extends DefaultWorkflowLog {

	public WorkflowLogAdapter(KaleoLog kaleoLogEntry) {
		setAuditUserId(kaleoLogEntry.getUserId());
		setComment(kaleoLogEntry.getComment());
		setCreateDate(kaleoLogEntry.getCreateDate());
		setPreviousState(kaleoLogEntry.getPreviousKaleoNodeName());

		long previousAssigneeClassPK =
			kaleoLogEntry.getPreviousAssigneeClassPK();

		if (previousAssigneeClassPK > 0) {
			String previousAssigneeClassName =
				kaleoLogEntry.getPreviousAssigneeClassName();

			if (previousAssigneeClassName.equals(Role.class.getName())) {
				setPreviousRoleId(previousAssigneeClassPK);
			}
			else {
				setPreviousUserId(previousAssigneeClassPK);
			}
		}

		long currentAssigneeClassPK = kaleoLogEntry.getCurrentAssigneeClassPK();

		if (currentAssigneeClassPK > 0) {
			String currentAssigneeClassName =
				kaleoLogEntry.getCurrentAssigneeClassName();

			if (currentAssigneeClassName.equals(Role.class.getName())) {
				setRoleId(currentAssigneeClassPK);
			}
			else {
				setUserId(currentAssigneeClassPK);
			}
		}

		setState(kaleoLogEntry.getKaleoNodeName());
		setType(KaleoLogUtil.convert(kaleoLogEntry.getType()));
		setWorkflowLogId(kaleoLogEntry.getKaleoLogId());
		setWorkflowTaskId(kaleoLogEntry.getKaleoTaskInstanceTokenId());
	}

}