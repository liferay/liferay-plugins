/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.workflow.edoras.dao.model;

import com.liferay.portal.workflow.edoras.model.WorkflowLog;

import org.edorasframework.process.api.model.Activity;
import org.edorasframework.process.api.model.ProcessModel;
import org.edorasframework.process.core.log.model.DefaultActivityLog;

/**
 * <a href="ActivityLogBridge.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 */
public class ActivityLogBridge extends DefaultActivityLog
	implements WorkflowEntity, WorkflowEntityBridge<WorkflowLog> {

	public ActivityLogBridge() {
		_workflowLogDelegate = new WorkflowLogDelegate<DefaultActivityLog>();
	}

	public ActivityLogBridge(WorkflowLog workflowLog) {
		this();

		initializeFromReading(workflowLog);
	}

	public long getPrimaryKey() {
		return getId();
	}

	public WorkflowLog initializeForInsert() {
		WorkflowLog workflowLog = _workflowLogDelegate.unwrap();

		transferPropertiesForSaving();

		return workflowLog;
	}

	public WorkflowLog initializeForUpdate() {
		WorkflowLog workflowLog = _workflowLogDelegate.unwrap();

		transferPropertiesForSaving();

		return workflowLog;
	}

	public void initializeFromReading(WorkflowLog workflowLog) {
		_workflowLogDelegate.initializeFromReading(workflowLog, this);

		String activityName = workflowLog.getActivityName();

		if (activityName == null) {
			setActivity(null);
		}
		else {
			ProcessModel processModel = getProcessModel();

			setActivity((Activity)processModel.getElement(activityName));
		}
	}

	public boolean setNew(boolean isNew) {
		return _workflowLogDelegate.setNew(isNew);
	}

	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);

		_workflowLogDelegate.setPrimaryKey(primaryKey);
	}

	public void transferPropertiesForSaving() {
		WorkflowLog workflowLog = _workflowLogDelegate.unwrap();

		_workflowLogDelegate.transferPropertiesForSaving(this);

		workflowLog.setActivityName(getActivityName());
	}

	public WorkflowLog unwrap() {
		return _workflowLogDelegate.unwrap();
	}

	private WorkflowLogDelegate<DefaultActivityLog> _workflowLogDelegate;

}