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

/**
 * <a href="WorkflowTaskWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowTaskWrapper implements WorkflowTask {
	public WorkflowTaskWrapper(WorkflowTask workflowTask) {
		_workflowTask = workflowTask;
	}

	public long getPrimaryKey() {
		return _workflowTask.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_workflowTask.setPrimaryKey(pk);
	}

	public long getWorkflowTaskId() {
		return _workflowTask.getWorkflowTaskId();
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowTask.setWorkflowTaskId(workflowTaskId);
	}

	public long getCompanyId() {
		return _workflowTask.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_workflowTask.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _workflowTask.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_workflowTask.setCreateDate(createDate);
	}

	public java.lang.String getFriendlyId() {
		return _workflowTask.getFriendlyId();
	}

	public void setFriendlyId(java.lang.String friendlyId) {
		_workflowTask.setFriendlyId(friendlyId);
	}

	public long getWorkflowDefinitionId() {
		return _workflowTask.getWorkflowDefinitionId();
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowTask.setWorkflowDefinitionId(workflowDefinitionId);
	}

	public long getWorkflowInstanceId() {
		return _workflowTask.getWorkflowInstanceId();
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowTask.setWorkflowInstanceId(workflowInstanceId);
	}

	public java.lang.String getMetaName() {
		return _workflowTask.getMetaName();
	}

	public void setMetaName(java.lang.String metaName) {
		_workflowTask.setMetaName(metaName);
	}

	public java.lang.String getRelation() {
		return _workflowTask.getRelation();
	}

	public void setRelation(java.lang.String relation) {
		_workflowTask.setRelation(relation);
	}

	public java.util.Date getDueDate() {
		return _workflowTask.getDueDate();
	}

	public void setDueDate(java.util.Date dueDate) {
		_workflowTask.setDueDate(dueDate);
	}

	public java.util.Date getCompletionDate() {
		return _workflowTask.getCompletionDate();
	}

	public void setCompletionDate(java.util.Date completionDate) {
		_workflowTask.setCompletionDate(completionDate);
	}

	public int getState() {
		return _workflowTask.getState();
	}

	public void setState(int state) {
		_workflowTask.setState(state);
	}

	public int getPriority() {
		return _workflowTask.getPriority();
	}

	public void setPriority(int priority) {
		_workflowTask.setPriority(priority);
	}

	public long getAssigneeUserId() {
		return _workflowTask.getAssigneeUserId();
	}

	public void setAssigneeUserId(long assigneeUserId) {
		_workflowTask.setAssigneeUserId(assigneeUserId);
	}

	public java.lang.String getAssigneeUserUuid()
		throws com.liferay.portal.SystemException {
		return _workflowTask.getAssigneeUserUuid();
	}

	public void setAssigneeUserUuid(java.lang.String assigneeUserUuid) {
		_workflowTask.setAssigneeUserUuid(assigneeUserUuid);
	}

	public java.lang.String getAssigneeUserName() {
		return _workflowTask.getAssigneeUserName();
	}

	public void setAssigneeUserName(java.lang.String assigneeUserName) {
		_workflowTask.setAssigneeUserName(assigneeUserName);
	}

	public long getRoleId() {
		return _workflowTask.getRoleId();
	}

	public void setRoleId(long roleId) {
		_workflowTask.setRoleId(roleId);
	}

	public WorkflowTask toEscapedModel() {
		return _workflowTask.toEscapedModel();
	}

	public boolean isNew() {
		return _workflowTask.isNew();
	}

	public boolean setNew(boolean n) {
		return _workflowTask.setNew(n);
	}

	public boolean isCachedModel() {
		return _workflowTask.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_workflowTask.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _workflowTask.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_workflowTask.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowTask.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowTask.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowTask.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _workflowTask.clone();
	}

	public int compareTo(WorkflowTask workflowTask) {
		return _workflowTask.compareTo(workflowTask);
	}

	public int hashCode() {
		return _workflowTask.hashCode();
	}

	public java.lang.String toString() {
		return _workflowTask.toString();
	}

	public java.lang.String toXmlString() {
		return _workflowTask.toXmlString();
	}

	public WorkflowTask getWrappedWorkflowTask() {
		return _workflowTask;
	}

	private WorkflowTask _workflowTask;
}