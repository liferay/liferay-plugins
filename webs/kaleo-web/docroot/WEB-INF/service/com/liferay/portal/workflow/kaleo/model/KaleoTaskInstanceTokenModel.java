/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.workflow.kaleo.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This interface is a model that represents the Kaleo_KaleoTaskInstanceToken table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceToken
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoTaskInstanceTokenModelImpl
 * @generated
 */
public interface KaleoTaskInstanceTokenModel extends BaseModel<KaleoTaskInstanceToken> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getKaleoTaskInstanceTokenId();

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId);

	public long getGroupId();

	public void setGroupId(long groupId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	@AutoEscape
	public String getUserName();

	public void setUserName(String userName);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getKaleoDefinitionId();

	public void setKaleoDefinitionId(long kaleoDefinitionId);

	public long getKaleoInstanceId();

	public void setKaleoInstanceId(long kaleoInstanceId);

	public long getKaleoInstanceTokenId();

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId);

	public long getKaleoTaskId();

	public void setKaleoTaskId(long kaleoTaskId);

	@AutoEscape
	public String getKaleoTaskName();

	public void setKaleoTaskName(String kaleoTaskName);

	@AutoEscape
	public String getClassName();

	public void setClassName(String className);

	public long getClassPK();

	public void setClassPK(long classPK);

	public long getCompletionUserId();

	public void setCompletionUserId(long completionUserId);

	public String getCompletionUserUuid() throws SystemException;

	public void setCompletionUserUuid(String completionUserUuid);

	public boolean getCompleted();

	public boolean isCompleted();

	public void setCompleted(boolean completed);

	public Date getCompletionDate();

	public void setCompletionDate(Date completionDate);

	public Date getDueDate();

	public void setDueDate(Date dueDate);

	@AutoEscape
	public String getWorkflowContext();

	public void setWorkflowContext(String workflowContext);

	public KaleoTaskInstanceToken toEscapedModel();

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(KaleoTaskInstanceToken kaleoTaskInstanceToken);

	public int hashCode();

	public String toString();

	public String toXmlString();
}