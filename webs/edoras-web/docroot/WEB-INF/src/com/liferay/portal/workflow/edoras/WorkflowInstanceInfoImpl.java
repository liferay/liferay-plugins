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
import com.liferay.portal.workflow.edoras.dao.model.WorkflowInstanceBridge;
import com.liferay.portal.workflow.edoras.model.WorkflowInstance;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.edorasframework.process.api.entity.ProcessInstance;

/**
 * <a href="WorkflowInstanceInfoImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Micha Kiener
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceInfoImpl implements WorkflowInstanceInfo {

	public WorkflowInstanceInfoImpl() {
	}

	public WorkflowInstanceInfoImpl(
		ProcessInstance processInstance, boolean includeChildren) {

		initializeFromProcessInstance(processInstance, includeChildren);
	}

	public WorkflowInstanceInfoImpl(
		WorkflowInstance workflowInstance, boolean includeChildren) {

		ProcessInstance processInstance = new WorkflowInstanceBridge(
			workflowInstance, null, includeChildren);

		initializeFromProcessInstance(processInstance, includeChildren);
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

	protected void initializeFromProcessInstance(
		ProcessInstance processInstance, boolean includeChildren) {

		if (includeChildren) {
			List<ProcessInstance> childProcessInstances =
				processInstance.getChildren();

			_children = new ArrayList<WorkflowInstanceInfo>(
				childProcessInstances.size());

			for (ProcessInstance childProcessInstance : childProcessInstances) {
				_children.add(
					new WorkflowInstanceInfoImpl(childProcessInstance, true));
			}
		}

		_context = new HashMap<String, Object>();

		processInstance.getAttributeMap().copyTo(_context);

		_currentNodeName = processInstance.getCurrentElement();
		_endDate = processInstance.getFinishedAt();

		ProcessInstance parentProcessInstance = processInstance.getParent();

		if (parentProcessInstance != null) {
			_parent = new WorkflowInstanceInfoImpl(
				parentProcessInstance, false);
		}

		_relationId = processInstance.getRelationId();
		_relationType = processInstance.getRelationType();
		_startDate = processInstance.getCreatedAt();
		_workflowDefinitionName = processInstance.getProcessModelId();
		_workflowDefinitionVersion = processInstance.getProcessModelVersion();
		_workflowInstanceId = processInstance.getPrimaryKey();
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