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
 * <a href="WorkflowJobWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowJobWrapper implements WorkflowJob {
	public WorkflowJobWrapper(WorkflowJob workflowJob) {
		_workflowJob = workflowJob;
	}

	public long getPrimaryKey() {
		return _workflowJob.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_workflowJob.setPrimaryKey(pk);
	}

	public long getWorkflowJobId() {
		return _workflowJob.getWorkflowJobId();
	}

	public void setWorkflowJobId(long workflowJobId) {
		_workflowJob.setWorkflowJobId(workflowJobId);
	}

	public long getCompanyId() {
		return _workflowJob.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_workflowJob.setCompanyId(companyId);
	}

	public java.util.Date getCreateDate() {
		return _workflowJob.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_workflowJob.setCreateDate(createDate);
	}

	public java.lang.String getSetupId() {
		return _workflowJob.getSetupId();
	}

	public void setSetupId(java.lang.String setupId) {
		_workflowJob.setSetupId(setupId);
	}

	public long getWorkflowDefinitionId() {
		return _workflowJob.getWorkflowDefinitionId();
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowJob.setWorkflowDefinitionId(workflowDefinitionId);
	}

	public long getWorkflowInstanceId() {
		return _workflowJob.getWorkflowInstanceId();
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowJob.setWorkflowInstanceId(workflowInstanceId);
	}

	public java.lang.String getElementName() {
		return _workflowJob.getElementName();
	}

	public void setElementName(java.lang.String elementName) {
		_workflowJob.setElementName(elementName);
	}

	public java.lang.String getCause() {
		return _workflowJob.getCause();
	}

	public void setCause(java.lang.String cause) {
		_workflowJob.setCause(cause);
	}

	public java.util.Date getDueDate() {
		return _workflowJob.getDueDate();
	}

	public void setDueDate(java.util.Date dueDate) {
		_workflowJob.setDueDate(dueDate);
	}

	public java.util.Date getNotBeforeDate() {
		return _workflowJob.getNotBeforeDate();
	}

	public void setNotBeforeDate(java.util.Date notBeforeDate) {
		_workflowJob.setNotBeforeDate(notBeforeDate);
	}

	public int getExceptionCount() {
		return _workflowJob.getExceptionCount();
	}

	public void setExceptionCount(int exceptionCount) {
		_workflowJob.setExceptionCount(exceptionCount);
	}

	public WorkflowJob toEscapedModel() {
		return _workflowJob.toEscapedModel();
	}

	public boolean isNew() {
		return _workflowJob.isNew();
	}

	public boolean setNew(boolean n) {
		return _workflowJob.setNew(n);
	}

	public boolean isCachedModel() {
		return _workflowJob.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_workflowJob.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _workflowJob.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_workflowJob.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowJob.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowJob.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowJob.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _workflowJob.clone();
	}

	public int compareTo(WorkflowJob workflowJob) {
		return _workflowJob.compareTo(workflowJob);
	}

	public int hashCode() {
		return _workflowJob.hashCode();
	}

	public java.lang.String toString() {
		return _workflowJob.toString();
	}

	public java.lang.String toXmlString() {
		return _workflowJob.toXmlString();
	}

	public WorkflowJob getWrappedWorkflowJob() {
		return _workflowJob;
	}

	private WorkflowJob _workflowJob;
}