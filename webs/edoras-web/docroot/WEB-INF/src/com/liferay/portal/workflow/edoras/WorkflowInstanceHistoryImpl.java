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

package com.liferay.portal.workflow.edoras;

import com.liferay.portal.kernel.workflow.WorkflowInstanceHistory;
import com.liferay.portal.workflow.edoras.model.WorkflowLog;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.edorasframework.process.api.log.ProcessLogType;

/**
 * <a href="WorkflowInstanceHistoryImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceHistoryImpl implements WorkflowInstanceHistory {

	public WorkflowInstanceHistoryImpl() {
	}

	public WorkflowInstanceHistoryImpl(WorkflowLog workflowLog) {
		_createDate = workflowLog.getCreateDate();
		_description = workflowLog.getDescription();
		_historyEntryId = workflowLog.getPrimaryKey();
		_type = ProcessLogType.getLogType(
			workflowLog.getLogEntityType()).name();
		_userId = workflowLog.getUserId();
		_workflowInstanceId = workflowLog.getWorkflowInstanceId();
	}

	public Map<String, Object> getAttributes() {
		return Collections.EMPTY_MAP;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public long getHistoryEntryId() {
		return _historyEntryId;
	}

	public String getType() {
		return _type;
	}

	public long getUserId() {
		return _userId;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	private Date _createDate;
	private String _description;
	private long _historyEntryId;
	private String _type;
	private Long _userId;
	private long _workflowInstanceId;

}