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
 * <a href="WorkflowTaskModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public interface WorkflowTaskModel extends BaseModel<WorkflowTask> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getWorkflowTaskId();

	public void setWorkflowTaskId(long workflowTaskId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public String getFriendlyId();

	public void setFriendlyId(String friendlyId);

	public long getWorkflowDefinitionId();

	public void setWorkflowDefinitionId(long workflowDefinitionId);

	public long getWorkflowInstanceId();

	public void setWorkflowInstanceId(long workflowInstanceId);

	public String getMetaName();

	public void setMetaName(String metaName);

	public String getRelation();

	public void setRelation(String relation);

	public Date getDueDate();

	public void setDueDate(Date dueDate);

	public Date getCompletionDate();

	public void setCompletionDate(Date completionDate);

	public boolean getCompleted();

	public boolean isCompleted();

	public void setCompleted(boolean completed);

	public int getState();

	public void setState(int state);

	public int getPriority();

	public void setPriority(int priority);

	public long getAssigneeUserId();

	public void setAssigneeUserId(long assigneeUserId);

	public String getAssigneeUserUuid() throws SystemException;

	public void setAssigneeUserUuid(String assigneeUserUuid);

	public String getAssigneeUserName();

	public void setAssigneeUserName(String assigneeUserName);

	public String getAssignedGroup();

	public void setAssignedGroup(String assignedGroup);

	public long getRoleId();

	public void setRoleId(long roleId);

	public WorkflowTask toEscapedModel();

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

	public int compareTo(WorkflowTask workflowTask);

	public int hashCode();

	public String toString();

	public String toXmlString();
}