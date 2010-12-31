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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.workflow.DefaultWorkflowLog;

import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowLogImpl extends DefaultWorkflowLog {

	public TaskInstance getTaskInstance() {
		return _taskInstance;
	}

	public long getWorkflowTaskId() {
		return _taskInstance.getId();
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		_taskInstance = taskInstance;
	}

	private TaskInstance _taskInstance;

}