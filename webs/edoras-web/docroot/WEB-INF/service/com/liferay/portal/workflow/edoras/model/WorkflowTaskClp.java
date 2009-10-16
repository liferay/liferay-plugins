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

package com.liferay.portal.workflow.edoras.model;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="WorkflowTaskClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskClp extends BaseModelImpl<WorkflowTask>
	implements WorkflowTask {
	public WorkflowTaskClp() {
	}

	public long getPrimaryKey() {
		return _workflowTaskId;
	}

	public void setPrimaryKey(long pk) {
		setWorkflowTaskId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_workflowTaskId);
	}

	public long getWorkflowTaskId() {
		return _workflowTaskId;
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowTaskId = workflowTaskId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getFriendlyId() {
		return _friendlyId;
	}

	public void setFriendlyId(String friendlyId) {
		_friendlyId = friendlyId;
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinitionId;
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinitionId = workflowDefinitionId;
	}

	public long getWorkflowInstanceId() {
		return _workflowInstanceId;
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstanceId = workflowInstanceId;
	}

	public String getMetaName() {
		return _metaName;
	}

	public void setMetaName(String metaName) {
		_metaName = metaName;
	}

	public String getRelation() {
		return _relation;
	}

	public void setRelation(String relation) {
		_relation = relation;
	}

	public Date getDueDate() {
		return _dueDate;
	}

	public void setDueDate(Date dueDate) {
		_dueDate = dueDate;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public boolean getCompleted() {
		return _completed;
	}

	public boolean isCompleted() {
		return _completed;
	}

	public void setCompleted(boolean completed) {
		_completed = completed;
	}

	public int getState() {
		return _state;
	}

	public void setState(int state) {
		_state = state;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public long getAssigneeUserId() {
		return _assigneeUserId;
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_assigneeUserId = assigneeUserId;
	}

	public String getAssigneeUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getAssigneeUserId(), "uuid",
			_assigneeUserUuid);
	}

	public void setAssigneeUserUuid(String assigneeUserUuid) {
		_assigneeUserUuid = assigneeUserUuid;
	}

	public String getAssigneeUserName() {
		return _assigneeUserName;
	}

	public void setAssigneeUserName(String assigneeUserName) {
		_assigneeUserName = assigneeUserName;
	}

	public String getAssignedGroup() {
		return _assignedGroup;
	}

	public void setAssignedGroup(String assignedGroup) {
		_assignedGroup = assignedGroup;
	}

	public long getRoleId() {
		return _roleId;
	}

	public void setRoleId(long roleId) {
		_roleId = roleId;
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance getWorkflowInstance() {
		throw new UnsupportedOperationException();
	}

	public WorkflowTask toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			WorkflowTask model = new WorkflowTaskClp();

			model.setEscapedModel(true);

			model.setWorkflowTaskId(getWorkflowTaskId());
			model.setCompanyId(getCompanyId());
			model.setCreateDate(getCreateDate());
			model.setFriendlyId(HtmlUtil.escape(getFriendlyId()));
			model.setWorkflowDefinitionId(getWorkflowDefinitionId());
			model.setWorkflowInstanceId(getWorkflowInstanceId());
			model.setMetaName(HtmlUtil.escape(getMetaName()));
			model.setRelation(HtmlUtil.escape(getRelation()));
			model.setDueDate(getDueDate());
			model.setCompletionDate(getCompletionDate());
			model.setCompleted(getCompleted());
			model.setState(getState());
			model.setPriority(getPriority());
			model.setAssigneeUserId(getAssigneeUserId());
			model.setAssigneeUserName(HtmlUtil.escape(getAssigneeUserName()));
			model.setAssignedGroup(HtmlUtil.escape(getAssignedGroup()));
			model.setRoleId(getRoleId());

			model = (WorkflowTask)Proxy.newProxyInstance(WorkflowTask.class.getClassLoader(),
					new Class[] { WorkflowTask.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		WorkflowTaskClp clone = new WorkflowTaskClp();

		clone.setWorkflowTaskId(getWorkflowTaskId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateDate(getCreateDate());
		clone.setFriendlyId(getFriendlyId());
		clone.setWorkflowDefinitionId(getWorkflowDefinitionId());
		clone.setWorkflowInstanceId(getWorkflowInstanceId());
		clone.setMetaName(getMetaName());
		clone.setRelation(getRelation());
		clone.setDueDate(getDueDate());
		clone.setCompletionDate(getCompletionDate());
		clone.setCompleted(getCompleted());
		clone.setState(getState());
		clone.setPriority(getPriority());
		clone.setAssigneeUserId(getAssigneeUserId());
		clone.setAssigneeUserName(getAssigneeUserName());
		clone.setAssignedGroup(getAssignedGroup());
		clone.setRoleId(getRoleId());

		return clone;
	}

	public int compareTo(WorkflowTask workflowTask) {
		long pk = workflowTask.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		WorkflowTaskClp workflowTask = null;

		try {
			workflowTask = (WorkflowTaskClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = workflowTask.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{workflowTaskId=");
		sb.append(getWorkflowTaskId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", friendlyId=");
		sb.append(getFriendlyId());
		sb.append(", workflowDefinitionId=");
		sb.append(getWorkflowDefinitionId());
		sb.append(", workflowInstanceId=");
		sb.append(getWorkflowInstanceId());
		sb.append(", metaName=");
		sb.append(getMetaName());
		sb.append(", relation=");
		sb.append(getRelation());
		sb.append(", dueDate=");
		sb.append(getDueDate());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append(", completed=");
		sb.append(getCompleted());
		sb.append(", state=");
		sb.append(getState());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append(", assigneeUserId=");
		sb.append(getAssigneeUserId());
		sb.append(", assigneeUserName=");
		sb.append(getAssigneeUserName());
		sb.append(", assignedGroup=");
		sb.append(getAssignedGroup());
		sb.append(", roleId=");
		sb.append(getRoleId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.edoras.model.WorkflowTask");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>workflowTaskId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowTaskId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>friendlyId</column-name><column-value><![CDATA[");
		sb.append(getFriendlyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowInstanceId</column-name><column-value><![CDATA[");
		sb.append(getWorkflowInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>metaName</column-name><column-value><![CDATA[");
		sb.append(getMetaName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>relation</column-name><column-value><![CDATA[");
		sb.append(getRelation());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>dueDate</column-name><column-value><![CDATA[");
		sb.append(getDueDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completed</column-name><column-value><![CDATA[");
		sb.append(getCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>state</column-name><column-value><![CDATA[");
		sb.append(getState());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeUserId</column-name><column-value><![CDATA[");
		sb.append(getAssigneeUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeUserName</column-name><column-value><![CDATA[");
		sb.append(getAssigneeUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assignedGroup</column-name><column-value><![CDATA[");
		sb.append(getAssignedGroup());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>roleId</column-name><column-value><![CDATA[");
		sb.append(getRoleId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _workflowTaskId;
	private long _companyId;
	private Date _createDate;
	private String _friendlyId;
	private long _workflowDefinitionId;
	private long _workflowInstanceId;
	private String _metaName;
	private String _relation;
	private Date _dueDate;
	private Date _completionDate;
	private boolean _completed;
	private int _state;
	private int _priority;
	private long _assigneeUserId;
	private String _assigneeUserUuid;
	private String _assigneeUserName;
	private String _assignedGroup;
	private long _roleId;
}