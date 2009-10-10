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

import com.liferay.portal.kernel.workflow.WorkflowInstanceInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <a href="WorkflowInstanceInfoImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceInfoImpl implements WorkflowInstanceInfo {

	public List<WorkflowInstanceInfo> getChildren() {
		return _children;
	}

	public Map<String, Object> getContext() {
		return _context;
	}

	public String getCurrentNodeName() {
		return _currentNodeName;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public WorkflowInstanceInfo getParent() {
		return _parent;
	}

	public long getRelationId() {
		return _relationId;
	}

	public String getRelationType() {
		return _relationType;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	private List<WorkflowInstanceInfo> _children;
	private Map<String, Object> _context;
	private String _currentNodeName;
	private Date _endDate;
	private WorkflowInstanceInfo _parent;
	private long _relationId;
	private String _relationType;
	private Date _startDate;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;

}