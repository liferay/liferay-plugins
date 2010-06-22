/*
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

package com.liferay.portal.workflow.kaleo.util;

import com.liferay.portal.kernel.workflow.WorkflowLog;
import com.liferay.portal.workflow.kaleo.definition.LogType;

/**
 * <a href="KaleoLogUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Michael C. Han
 */
public class KaleoLogUtil {
	public static int convert(String type) {
		LogType logType = LogType.valueOf(type);

		if (logType.equals(LogType.TASK_ASSIGNMENT)) {
			return WorkflowLog.TASK_ASSIGN;
		}

		if (logType.equals(LogType.TASK_COMPLETION)) {
			return WorkflowLog.TASK_COMPLETION;
		}

		if (logType.equals(LogType.TASK_UPDATE)) {
			return WorkflowLog.TASK_UPDATE;
		}

		if (logType.equals(LogType.NODE_EXIT)) {
			return WorkflowLog.TRANSITION;
		}

		return -1;
	}

	public static String convert(int type) {
		if (type == WorkflowLog.TASK_ASSIGN) {
			return LogType.TASK_ASSIGNMENT.name();
		}

		if (type == WorkflowLog.TASK_UPDATE) {
			return LogType.TASK_UPDATE.name();
		}

		if (type == WorkflowLog.TASK_COMPLETION) {
			return LogType.TASK_COMPLETION.name();
		}

		if (type == WorkflowLog.TRANSITION) {
			return LogType.NODE_EXIT.name();
		}

		return null;
	}
}