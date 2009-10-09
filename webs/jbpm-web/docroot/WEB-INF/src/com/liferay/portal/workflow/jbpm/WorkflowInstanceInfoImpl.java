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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.workflow.WorkflowInstanceInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.graph.exe.Token;

/**
 * <a href="WorkflowInstanceInfoImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceInfoImpl implements WorkflowInstanceInfo {

	public WorkflowInstanceInfoImpl(Token token){
		ProcessInstance processInstance = token.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		_children = new ArrayList<WorkflowInstanceInfo>();

		_context = processInstance.getContextInstance().getVariables(token);

		if (_context == null) {
			_context = new HashMap<String, Object>();
		}

		_context = Collections.unmodifiableMap(_context);

		_currentNodeName = token.getNode().getName();
		_endDate = token.getEnd();
		_startDate = token.getStart();
		_workflowDefinitionName = processDefinition.getName();
		_workflowDefinitionVersion = processDefinition.getVersion();
		_workflowInstanceId = token.getId();
	}

	public void addChild(WorkflowInstanceInfo child) {
		_children.add(child);
	}

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
		return _RELATION_ID;
	}

	public String getRelationType() {
		return _RELATION_TYPE;
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

	public void setParent(WorkflowInstanceInfo parent) {
		_parent = parent;
	}

	private static final long _RELATION_ID = -1;

	private static final String _RELATION_TYPE = StringPool.BLANK;

	private List<WorkflowInstanceInfo> _children;
	private Map<String, Object> _context;
	private String _currentNodeName;
	private Date _endDate;
	private WorkflowInstanceInfo _parent;
	private Date _startDate;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;

}