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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowLog;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.jbpm.graph.exe.Token;
import org.jbpm.logging.log.ProcessLog;

/**
 * <a href="WorkflowLogImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogImpl implements WorkflowLog {

	public WorkflowLogImpl(ProcessLog processLog) {
		Token token = processLog.getToken();

		_createDate = processLog.getDate();
		_description = processLog.toString();
		_userId = GetterUtil.getLong(processLog.getActorId(), -1);
		_workflowInstanceId = token.getId();
		_workflowLogId = processLog.getId();
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public String getDescription() {
		return _description;
	}

	public Map<String, Object> getOptionalAttributes() {
		return Collections.EMPTY_MAP;
	}

	public long getUserId() {
		return _userId;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public long getWorkflowLogId() {
		return _workflowLogId;
	}

	private Date _createDate;
	private String _description;
	private Long _userId;
	private long _workflowInstanceId;
	private long _workflowLogId;

}