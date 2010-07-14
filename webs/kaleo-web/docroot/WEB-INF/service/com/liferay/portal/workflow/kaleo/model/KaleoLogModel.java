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
 * This interface is a model that represents the Kaleo_KaleoLog table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLog
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoLogImpl
 * @see       com.liferay.portal.workflow.kaleo.model.impl.KaleoLogModelImpl
 * @generated
 */
public interface KaleoLogModel extends BaseModel<KaleoLog> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getKaleoLogId();

	public void setKaleoLogId(long kaleoLogId);

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

	public long getKaleoTaskInstanceTokenId();

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId);

	public long getKaleoNodeId();

	public void setKaleoNodeId(long kaleoNodeId);

	@AutoEscape
	public String getKaleoNodeName();

	public void setKaleoNodeName(String kaleoNodeName);

	public boolean getTerminalKaleoNode();

	public boolean isTerminalKaleoNode();

	public void setTerminalKaleoNode(boolean terminalKaleoNode);

	public long getKaleoActionId();

	public void setKaleoActionId(long kaleoActionId);

	@AutoEscape
	public String getKaleoActionName();

	public void setKaleoActionName(String kaleoActionName);

	@AutoEscape
	public String getKaleoActionDescription();

	public void setKaleoActionDescription(String kaleoActionDescription);

	public long getPreviousKaleoNodeId();

	public void setPreviousKaleoNodeId(long previousKaleoNodeId);

	@AutoEscape
	public String getPreviousKaleoNodeName();

	public void setPreviousKaleoNodeName(String previousKaleoNodeName);

	@AutoEscape
	public String getPreviousAssigneeClassName();

	public void setPreviousAssigneeClassName(String previousAssigneeClassName);

	public long getPreviousAssigneeClassPK();

	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK);

	@AutoEscape
	public String getCurrentAssigneeClassName();

	public void setCurrentAssigneeClassName(String currentAssigneeClassName);

	public long getCurrentAssigneeClassPK();

	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK);

	@AutoEscape
	public String getType();

	public void setType(String type);

	@AutoEscape
	public String getComment();

	public void setComment(String comment);

	public Date getStartDate();

	public void setStartDate(Date startDate);

	public Date getEndDate();

	public void setEndDate(Date endDate);

	public long getDuration();

	public void setDuration(long duration);

	@AutoEscape
	public String getWorkflowContext();

	public void setWorkflowContext(String workflowContext);

	public KaleoLog toEscapedModel();

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

	public int compareTo(KaleoLog kaleoLog);

	public int hashCode();

	public String toString();

	public String toXmlString();
}