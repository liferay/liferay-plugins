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
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <a href="WorkflowInstanceModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WorkflowInstanceModel extends BaseModel<WorkflowInstance> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getWorkflowInstanceId();

	public void setWorkflowInstanceId(long workflowInstanceId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	public String getUserName();

	public void setUserName(String userName);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public String getSetupId();

	public void setSetupId(String setupId);

	public String getFriendlyId();

	public void setFriendlyId(String friendlyId);

	public long getWorkflowDefinitionId();

	public void setWorkflowDefinitionId(long workflowDefinitionId);

	public String getWorkflowDefinitionName();

	public void setWorkflowDefinitionName(String workflowDefinitionName);

	public int getWorkflowDefinitionVersion();

	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion);

	public long getParentWorkflowInstanceId();

	public void setParentWorkflowInstanceId(long parentWorkflowInstanceId);

	public String getRelationClassName();

	public void setRelationClassName(String relationClassName);

	public long getRelationClassPK();

	public void setRelationClassPK(long relationClassPK);

	public String getAttributes();

	public void setAttributes(String attributes);

	public String getNestedWorkflowDefinitionIds();

	public void setNestedWorkflowDefinitionIds(
		String nestedWorkflowDefinitionIds);

	public String getNestedWorkflowDefinitionVersions();

	public void setNestedWorkflowDefinitionVersions(
		String nestedWorkflowDefinitionVersions);

	public String getNestedRelatedElements();

	public void setNestedRelatedElements(String nestedRelatedElements);

	public String getCurrentElementName();

	public void setCurrentElementName(String currentElementName);

	public String getRelatedElementName();

	public void setRelatedElementName(String relatedElementName);

	public boolean getFinished();

	public boolean isFinished();

	public void setFinished(boolean finished);

	public Date getFinishedDated();

	public void setFinishedDated(Date finishedDated);

	public boolean getActive();

	public boolean isActive();

	public void setActive(boolean active);

	public WorkflowInstance toEscapedModel();

	public boolean isNew();

	public boolean setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(WorkflowInstance workflowInstance);

	public int hashCode();

	public String toString();

	public String toXmlString();
}