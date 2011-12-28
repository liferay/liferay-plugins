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

package com.liferay.calendar.workflow;

import com.liferay.portal.kernel.workflow.WorkflowConstants;

/**
 * @author Michael C. Han
 */
public class CalendarBookingWorkflowConstants extends WorkflowConstants {

	public static final String LABEL_ACCEPTED = "accepted";

	public static final String LABEL_DECLINED = "declined";

	public static String toLabel(int status) {
		if (status == STATUS_APPROVED) {
			return LABEL_ACCEPTED;
		}
		else if (status == STATUS_DENIED) {
			return LABEL_DECLINED;
		}
		else {
			return WorkflowConstants.toLabel(status);
		}
	}

	public static int toStatus(String label) {
		if (label.equals(LABEL_ACCEPTED)) {
			return STATUS_APPROVED;
		}
		else if (label.equals(LABEL_DECLINED)) {
			return STATUS_DENIED;
		}
		else {
			return WorkflowConstants.toStatus(label);
		}
	}

}