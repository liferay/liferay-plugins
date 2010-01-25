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

package com.liferay.portal.workflow.jbpm;

import com.liferay.portal.kernel.workflow.WorkflowLog;

import java.io.Serializable;
import java.util.Date;

import org.jbpm.taskmgmt.exe.TaskInstance;

/**
 * <a href="WorkflowLogImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 */
public class WorkflowLogImpl implements WorkflowLog, Serializable {

	public String getComment() {
		return _comment;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getPreviousState() {
		return _previousState;
	}

	public long getPreviousUserId() {
		return _previousUserId;
	}

	public String getState() {
		return _state;
	}

	public TaskInstance getTaskInstance() {
		return _taskInstance;
	}

	public int getType() {
		return _type;
	}

	public long getUserId() {
		return _userId;
	}

	public long getWorkflowInstanceId() {
		return _taskInstance.getId();
	}

	public long getWorkflowLogId() {
		return _workflowLogId;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public void setPreviousState(String previousState) {
		_previousState = previousState;
	}

	public void setPreviousUserId(long previousUserId) {
		_previousUserId = previousUserId;
	}

	public void setState(String state) {
		_state = state;
	}

	public void setTaskInstance(TaskInstance taskInstance) {
		_taskInstance = taskInstance;
	}

	public void setType(int type) {
		_type = type;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setWorkflowLogId(long workflowLogId) {
		_workflowLogId = workflowLogId;
	}

	private String _comment;
	private Date _createDate;
	private String _previousState;
	private long _previousUserId;
	private String _state;
	private TaskInstance _taskInstance;
	private int _type;
	private long _userId;
	private long _workflowLogId;

}