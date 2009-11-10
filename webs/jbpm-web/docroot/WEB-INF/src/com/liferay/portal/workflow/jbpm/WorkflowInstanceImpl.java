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

import com.liferay.portal.kernel.workflow.WorkflowInstance;

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
 * <a href="WorkflowInstanceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceImpl implements WorkflowInstance {

	public WorkflowInstanceImpl(Token token) {
		ProcessInstance processInstance = token.getProcessInstance();
		ProcessDefinition processDefinition =
			processInstance.getProcessDefinition();

		_childrenWorkflowInstances = new ArrayList<WorkflowInstance>();

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

	public void addChildWorkflowInstance(
		WorkflowInstance childWorkflowInstance) {

		_childrenWorkflowInstances.add(childWorkflowInstance);
	}

	public int getChildrenWorkflowInstanceCount() {
		return _childrenWorkflowInstances.size();
	}

	public List<WorkflowInstance> getChildrenWorkflowInstances() {
		return _childrenWorkflowInstances;
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

	public WorkflowInstance getParentWorkflowInstance() {
		return _parentWorkflowInstance;
	}

	public long getParentWorkflowInstanceId() {
		if (_parentWorkflowInstance != null) {
			return _parentWorkflowInstance.getWorkflowInstanceId();
		}
		else {
			return 0;
		}
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

	public void setParentWorkflowInstance(
		WorkflowInstance parentWorkflowInstance) {

		_parentWorkflowInstance = parentWorkflowInstance;
	}

	private List<WorkflowInstance> _childrenWorkflowInstances;
	private Map<String, Object> _context;
	private String _currentNodeName;
	private Date _endDate;
	private WorkflowInstance _parentWorkflowInstance;
	private Date _startDate;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;
	private long _workflowInstanceId;

}