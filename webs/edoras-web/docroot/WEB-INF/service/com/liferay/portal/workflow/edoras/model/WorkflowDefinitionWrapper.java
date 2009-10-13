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
 * <a href="WorkflowDefinitionWrapper.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class WorkflowDefinitionWrapper implements WorkflowDefinition {
	public WorkflowDefinitionWrapper(WorkflowDefinition workflowDefinition) {
		_workflowDefinition = workflowDefinition;
	}

	public long getPrimaryKey() {
		return _workflowDefinition.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_workflowDefinition.setPrimaryKey(pk);
	}

	public long getWorkflowDefinitionId() {
		return _workflowDefinition.getWorkflowDefinitionId();
	}

	public void setWorkflowDefinitionId(long workflowDefinitionId) {
		_workflowDefinition.setWorkflowDefinitionId(workflowDefinitionId);
	}

	public long getCompanyId() {
		return _workflowDefinition.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_workflowDefinition.setCompanyId(companyId);
	}

	public long getUserId() {
		return _workflowDefinition.getUserId();
	}

	public void setUserId(long userId) {
		_workflowDefinition.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.SystemException {
		return _workflowDefinition.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_workflowDefinition.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _workflowDefinition.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_workflowDefinition.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _workflowDefinition.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_workflowDefinition.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _workflowDefinition.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_workflowDefinition.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _workflowDefinition.getName();
	}

	public void setName(java.lang.String name) {
		_workflowDefinition.setName(name);
	}

	public int getVersion() {
		return _workflowDefinition.getVersion();
	}

	public void setVersion(int version) {
		_workflowDefinition.setVersion(version);
	}

	public java.lang.String getDesignerVersion() {
		return _workflowDefinition.getDesignerVersion();
	}

	public void setDesignerVersion(java.lang.String designerVersion) {
		_workflowDefinition.setDesignerVersion(designerVersion);
	}

	public java.lang.String getModelXml() {
		return _workflowDefinition.getModelXml();
	}

	public void setModelXml(java.lang.String modelXml) {
		_workflowDefinition.setModelXml(modelXml);
	}

	public java.lang.String getGraphicalXml() {
		return _workflowDefinition.getGraphicalXml();
	}

	public void setGraphicalXml(java.lang.String graphicalXml) {
		_workflowDefinition.setGraphicalXml(graphicalXml);
	}

	public boolean getPersistent() {
		return _workflowDefinition.getPersistent();
	}

	public boolean isPersistent() {
		return _workflowDefinition.isPersistent();
	}

	public void setPersistent(boolean persistent) {
		_workflowDefinition.setPersistent(persistent);
	}

	public com.liferay.portal.workflow.edoras.model.WorkflowDefinition toEscapedModel() {
		return _workflowDefinition.toEscapedModel();
	}

	public boolean isNew() {
		return _workflowDefinition.isNew();
	}

	public boolean setNew(boolean n) {
		return _workflowDefinition.setNew(n);
	}

	public boolean isCachedModel() {
		return _workflowDefinition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_workflowDefinition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _workflowDefinition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_workflowDefinition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _workflowDefinition.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _workflowDefinition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_workflowDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _workflowDefinition.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.edoras.model.WorkflowDefinition workflowDefinition) {
		return _workflowDefinition.compareTo(workflowDefinition);
	}

	public int hashCode() {
		return _workflowDefinition.hashCode();
	}

	public java.lang.String toString() {
		return _workflowDefinition.toString();
	}

	public java.lang.String toXmlString() {
		return _workflowDefinition.toXmlString();
	}

	public java.lang.String getGraphicalProcessModelAsXML() {
		return _workflowDefinition.getGraphicalProcessModelAsXML();
	}

	public java.lang.String getModelDesignerVersion() {
		return _workflowDefinition.getModelDesignerVersion();
	}

	public java.lang.String getProcessModelAsXML() {
		return _workflowDefinition.getProcessModelAsXML();
	}

	public java.lang.String getProcessModelId() {
		return _workflowDefinition.getProcessModelId();
	}

	public int getProcessModelVersion() {
		return _workflowDefinition.getProcessModelVersion();
	}

	public java.lang.Long getTenantId() {
		return _workflowDefinition.getTenantId();
	}

	public void setGraphicalProcessModelAsXML(java.lang.String xmlModel) {
		_workflowDefinition.setGraphicalProcessModelAsXML(xmlModel);
	}

	public void setModelDesignerVersion(java.lang.String designerVersion) {
		_workflowDefinition.setModelDesignerVersion(designerVersion);
	}

	public void setProcessModelAsXML(java.lang.String xmlModel) {
		_workflowDefinition.setProcessModelAsXML(xmlModel);
	}

	public void setProcessModelId(java.lang.String modelId) {
		_workflowDefinition.setProcessModelId(modelId);
	}

	public void setProcessModelVersion(int modelVersion) {
		_workflowDefinition.setProcessModelVersion(modelVersion);
	}

	public void setTenantId(java.lang.Long tenantId) {
		_workflowDefinition.setTenantId(tenantId);
	}

	public WorkflowDefinition getWrappedWorkflowDefinition() {
		return _workflowDefinition;
	}

	private WorkflowDefinition _workflowDefinition;
}