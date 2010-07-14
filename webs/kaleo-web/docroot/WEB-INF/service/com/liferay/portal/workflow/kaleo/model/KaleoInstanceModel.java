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
 * This interface is a model that represents the Kaleo_KaleoInstance table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstance
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoInstanceModelImpl
 * @generated
 */
public interface KaleoInstanceModel extends BaseModel<KaleoInstance> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getKaleoInstanceId();

	public void setKaleoInstanceId(long kaleoInstanceId);

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

	@AutoEscape
	public String getKaleoDefinitionName();

	public void setKaleoDefinitionName(String kaleoDefinitionName);

	public int getKaleoDefinitionVersion();

	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion);

	public long getRootKaleoInstanceTokenId();

	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId);

	@AutoEscape
	public String getClassName();

	public void setClassName(String className);

	public long getClassPK();

	public void setClassPK(long classPK);

	public boolean getCompleted();

	public boolean isCompleted();

	public void setCompleted(boolean completed);

	public Date getCompletionDate();

	public void setCompletionDate(Date completionDate);

	@AutoEscape
	public String getWorkflowContext();

	public void setWorkflowContext(String workflowContext);

	public KaleoInstance toEscapedModel();

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

	public int compareTo(KaleoInstance kaleoInstance);

	public int hashCode();

	public String toString();

	public String toXmlString();
}