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
 * <a href="WorkflowInstanceWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowInstanceWrapper implements WorkflowInstance {
	public WorkflowInstanceWrapper(WorkflowInstance workflowInstance) {
		_workflowInstance = workflowInstance;
	}

	public long getPrimaryKey() {
		return _workflowInstance.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_workflowInstance.setPrimaryKey(pk);
	}

	public long getWorkflowInstanceId() {
		return _workflowInstance.getWorkflowInstanceId();
	}

	public void setWorkflowInstanceId(long workflowInstanceId) {
		_workflowInstance.setWorkflowInstanceId(workflowInstanceId);
	}

	public long getCompanyId() {
		return _workflowInstance.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_workflowInstance.setCompanyId(companyId);
	}

	public long getUserId() {
		return _workflowInstance.getUserId();
	}

	public void setUserId(long userId) {
		_workflowInstance.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _workflowInstance.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_workflowInstance.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _workflowInstance.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_workflowInstance.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _workflowInstance.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_workflowInstance.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _workflowInstance.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_workflowInstance.setModifiedDate(modifiedDate);
	}

	public java.lang.String getSetupId() {
		return _workflowInstance.getSetupId();
	}

	public void setSetupId(java.lang.String setupId) {
		_workflowInstance.setSetupId(setupId);
	}

	public java.lang.String getFriendlyId() {
		return _workflowInstance.getFriendlyId();
	}

	public void setFriendlyId(java.lang.String friendlyId) {
		_workflowInstance.setFriendlyId(friendlyId);
	}

	public long getWorkflowDefinitionId() {
		return _workflowInstance.getWorkflowDefinitionId();
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowInstance.setWorkflowDefinitionId(workflowDefinitionId);
	}

	public java.lang.String getWorkflowDefinitionName() {
		return _workflowInstance.getWorkflowDefinitionName();
	}

	public void setWorkflowDefinitionName(
		java.lang.String workflowDefinitionName) {
		_workflowInstance.setWorkflowDefinitionName(workflowDefinitionName);
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowInstance.getWorkflowDefinitionVersion();
	}

	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		_workflowInstance.setWorkflowDefinitionVersion(workflowDefinitionVersion);
	}

	public long getParentWorkflowInstanceId() {
		return _workflowInstance.getParentWorkflowInstanceId();
	}

	public void setParentWorkflowInstanceId(long parentWorkflowInstanceId) {
		_workflowInstance.setParentWorkflowInstanceId(parentWorkflowInstanceId);
	}

	public java.lang.String getRelationClassName() {
		return _workflowInstance.getRelationClassName();
	}

	public void setRelationClassName(java.lang.String relationClassName) {
		_workflowInstance.setRelationClassName(relationClassName);
	}

	public long getRelationClassPK() {
		return _workflowInstance.getRelationClassPK();
	}

	public void setRelationClassPK(long relationClassPK) {
		_workflowInstance.setRelationClassPK(relationClassPK);
	}

	public java.lang.String getAttributes() {
		return _workflowInstance.getAttributes();
	}

	public void setAttributes(java.lang.String attributes) {
		_workflowInstance.setAttributes(attributes);
	}

	public java.lang.String getNestedWorkflowDefinitionIds() {
		return _workflowInstance.getNestedWorkflowDefinitionIds();
	}

	public void setNestedWorkflowDefinitionIds(
		java.lang.String nestedWorkflowDefinitionIds) {
		_workflowInstance.setNestedWorkflowDefinitionIds(nestedWorkflowDefinitionIds);
	}

	public java.lang.String getNestedWorkflowDefinitionVersions() {
		return _workflowInstance.getNestedWorkflowDefinitionVersions();
	}

	public void setNestedWorkflowDefinitionVersions(
		java.lang.String nestedWorkflowDefinitionVersions) {
		_workflowInstance.setNestedWorkflowDefinitionVersions(nestedWorkflowDefinitionVersions);
	}

	public java.lang.String getNestedRelatedElements() {
		return _workflowInstance.getNestedRelatedElements();
	}

	public void setNestedRelatedElements(java.lang.String nestedRelatedElements) {
		_workflowInstance.setNestedRelatedElements(nestedRelatedElements);
	}

	public java.lang.String getCurrentElementName() {
		return _workflowInstance.getCurrentElementName();
	}

	public void setCurrentElementName(java.lang.String currentElementName) {
		_workflowInstance.setCurrentElementName(currentElementName);
	}

	public java.lang.String getRelatedElementName() {
		return _workflowInstance.getRelatedElementName();
	}

	public void setRelatedElementName(java.lang.String relatedElementName) {
		_workflowInstance.setRelatedElementName(relatedElementName);
	}

	public boolean getFinished() {
		return _workflowInstance.getFinished();
	}

	public boolean isFinished() {
		return _workflowInstance.isFinished();
	}

	public void setFinished(boolean finished) {
		_workflowInstance.setFinished(finished);
	}

	public java.util.Date getFinishedDated() {
		return _workflowInstance.getFinishedDated();
	}

	public void setFinishedDated(java.util.Date finishedDated) {
		_workflowInstance.setFinishedDated(finishedDated);
	}

	public boolean getActive() {
		return _workflowInstance.getActive();
	}

	public boolean isActive() {
		return _workflowInstance.isActive();
	}

	public void setActive(boolean active) {
		_workflowInstance.setActive(active);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance toEscapedModel() {
		return _workflowInstance.toEscapedModel();
	}

	public boolean isNew() {
		return _workflowInstance.isNew();
	}

	public boolean setNew(boolean n) {
		return _workflowInstance.setNew(n);
	}

	public boolean isCachedModel() {
		return _workflowInstance.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_workflowInstance.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _workflowInstance.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_workflowInstance.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowInstance.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowInstance.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowInstance.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _workflowInstance.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.edoras.model.WorkflowInstance workflowInstance) {
		return _workflowInstance.compareTo(workflowInstance);
	}

	public int hashCode() {
		return _workflowInstance.hashCode();
	}

	public java.lang.String toString() {
		return _workflowInstance.toString();
	}

	public java.lang.String toXmlString() {
		return _workflowInstance.toXmlString();
	}

	public java.util.List<com.liferay.portal.workflow.edoras.model.WorkflowInstance> getChildren() {
		return _workflowInstance.getChildren();
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowInstance getParent() {
		return _workflowInstance.getParent();
	}

	public WorkflowInstance getWrappedWorkflowInstance() {
		return _workflowInstance;
	}

	private WorkflowInstance _workflowInstance;
}