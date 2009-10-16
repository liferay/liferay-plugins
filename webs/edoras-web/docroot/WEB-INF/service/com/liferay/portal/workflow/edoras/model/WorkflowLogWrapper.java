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
 * <a href="WorkflowLogWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowLogWrapper implements WorkflowLog {
	public WorkflowLogWrapper(WorkflowLog workflowLog) {
		_workflowLog = workflowLog;
	}

	public long getPrimaryKey() {
		return _workflowLog.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_workflowLog.setPrimaryKey(pk);
	}

	public long getWorkflowLogId() {
		return _workflowLog.getWorkflowLogId();
	}

	public void setWorkflowLogId(long workflowLogId) {
		_workflowLog.setWorkflowLogId(workflowLogId);
	}

	public long getCompanyId() {
		return _workflowLog.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_workflowLog.setCompanyId(companyId);
	}

	public long getUserId() {
		return _workflowLog.getUserId();
	}

	public void setUserId(long userId) {
		_workflowLog.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _workflowLog.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_workflowLog.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _workflowLog.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_workflowLog.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _workflowLog.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_workflowLog.setCreateDate(createDate);
	}

	public long getWorkflowDefinitionId() {
		return _workflowLog.getWorkflowDefinitionId();
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowLog.setWorkflowDefinitionId(workflowDefinitionId);
	}

	public long getWorkflowInstanceId() {
		return _workflowLog.getWorkflowInstanceId();
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowLog.setWorkflowInstanceId(workflowInstanceId);
	}

	public long getWorkflowTaskId() {
		return _workflowLog.getWorkflowTaskId();
	}

	public void setWorkflowTaskId(long workflowTaskId) {
		_workflowLog.setWorkflowTaskId(workflowTaskId);
	}

	public int getLogEntityType() {
		return _workflowLog.getLogEntityType();
	}

	public void setLogEntityType(int logEntityType) {
		_workflowLog.setLogEntityType(logEntityType);
	}

	public java.lang.String getDescription() {
		return _workflowLog.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_workflowLog.setDescription(description);
	}

	public java.lang.String getActivityName() {
		return _workflowLog.getActivityName();
	}

	public void setActivityName(java.lang.String activityName) {
		_workflowLog.setActivityName(activityName);
	}

	public java.lang.String getOldState() {
		return _workflowLog.getOldState();
	}

	public void setOldState(java.lang.String oldState) {
		_workflowLog.setOldState(oldState);
	}

	public java.lang.String getNewState() {
		return _workflowLog.getNewState();
	}

	public void setNewState(java.lang.String newState) {
		_workflowLog.setNewState(newState);
	}

	public int getType() {
		return _workflowLog.getType();
	}

	public void setType(int type) {
		_workflowLog.setType(type);
	}

	public java.lang.String getComment() {
		return _workflowLog.getComment();
	}

	public void setComment(java.lang.String comment) {
		_workflowLog.setComment(comment);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowLog toEscapedModel() {
		return _workflowLog.toEscapedModel();
	}

	public boolean isNew() {
		return _workflowLog.isNew();
	}

	public boolean setNew(boolean n) {
		return _workflowLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _workflowLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_workflowLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _workflowLog.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_workflowLog.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowLog.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowLog.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _workflowLog.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.edoras.model.WorkflowLog workflowLog) {
		return _workflowLog.compareTo(workflowLog);
	}

	public int hashCode() {
		return _workflowLog.hashCode();
	}

	public java.lang.String toString() {
		return _workflowLog.toString();
	}

	public java.lang.String toXmlString() {
		return _workflowLog.toXmlString();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowTask getTaskInstance() {
		return _workflowLog.getTaskInstance();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance getWorkflowInstance() {
		return _workflowLog.getWorkflowInstance();
	}

	public WorkflowLog getWrappedWorkflowLog() {
		return _workflowLog;
	}

	private WorkflowLog _workflowLog;
}