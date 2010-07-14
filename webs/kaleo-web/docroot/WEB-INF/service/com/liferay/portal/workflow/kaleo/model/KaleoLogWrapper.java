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


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoLog}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLog
 * @generated
 */
public class KaleoLogWrapper implements KaleoLog {
	public KaleoLogWrapper(KaleoLog kaleoLog) {
		_kaleoLog = kaleoLog;
	}

	public long getPrimaryKey() {
		return _kaleoLog.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoLog.setPrimaryKey(pk);
	}

	public long getKaleoLogId() {
		return _kaleoLog.getKaleoLogId();
	}

	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLog.setKaleoLogId(kaleoLogId);
	}

	public long getGroupId() {
		return _kaleoLog.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoLog.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoLog.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoLog.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoLog.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoLog.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLog.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoLog.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoLog.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoLog.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoLog.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoLog.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoLog.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoLog.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoLog.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoLog.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoInstanceId() {
		return _kaleoLog.getKaleoInstanceId();
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoLog.setKaleoInstanceId(kaleoInstanceId);
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoLog.getKaleoInstanceTokenId();
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoLog.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoLog.getKaleoTaskInstanceTokenId();
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoLog.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public long getKaleoNodeId() {
		return _kaleoLog.getKaleoNodeId();
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoLog.setKaleoNodeId(kaleoNodeId);
	}

	public java.lang.String getKaleoNodeName() {
		return _kaleoLog.getKaleoNodeName();
	}

	public void setKaleoNodeName(java.lang.String kaleoNodeName) {
		_kaleoLog.setKaleoNodeName(kaleoNodeName);
	}

	public boolean getTerminalKaleoNode() {
		return _kaleoLog.getTerminalKaleoNode();
	}

	public boolean isTerminalKaleoNode() {
		return _kaleoLog.isTerminalKaleoNode();
	}

	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_kaleoLog.setTerminalKaleoNode(terminalKaleoNode);
	}

	public long getKaleoActionId() {
		return _kaleoLog.getKaleoActionId();
	}

	public void setKaleoActionId(long kaleoActionId) {
		_kaleoLog.setKaleoActionId(kaleoActionId);
	}

	public java.lang.String getKaleoActionName() {
		return _kaleoLog.getKaleoActionName();
	}

	public void setKaleoActionName(java.lang.String kaleoActionName) {
		_kaleoLog.setKaleoActionName(kaleoActionName);
	}

	public java.lang.String getKaleoActionDescription() {
		return _kaleoLog.getKaleoActionDescription();
	}

	public void setKaleoActionDescription(
		java.lang.String kaleoActionDescription) {
		_kaleoLog.setKaleoActionDescription(kaleoActionDescription);
	}

	public long getPreviousKaleoNodeId() {
		return _kaleoLog.getPreviousKaleoNodeId();
	}

	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_kaleoLog.setPreviousKaleoNodeId(previousKaleoNodeId);
	}

	public java.lang.String getPreviousKaleoNodeName() {
		return _kaleoLog.getPreviousKaleoNodeName();
	}

	public void setPreviousKaleoNodeName(java.lang.String previousKaleoNodeName) {
		_kaleoLog.setPreviousKaleoNodeName(previousKaleoNodeName);
	}

	public java.lang.String getPreviousAssigneeClassName() {
		return _kaleoLog.getPreviousAssigneeClassName();
	}

	public void setPreviousAssigneeClassName(
		java.lang.String previousAssigneeClassName) {
		_kaleoLog.setPreviousAssigneeClassName(previousAssigneeClassName);
	}

	public long getPreviousAssigneeClassPK() {
		return _kaleoLog.getPreviousAssigneeClassPK();
	}

	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_kaleoLog.setPreviousAssigneeClassPK(previousAssigneeClassPK);
	}

	public java.lang.String getCurrentAssigneeClassName() {
		return _kaleoLog.getCurrentAssigneeClassName();
	}

	public void setCurrentAssigneeClassName(
		java.lang.String currentAssigneeClassName) {
		_kaleoLog.setCurrentAssigneeClassName(currentAssigneeClassName);
	}

	public long getCurrentAssigneeClassPK() {
		return _kaleoLog.getCurrentAssigneeClassPK();
	}

	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_kaleoLog.setCurrentAssigneeClassPK(currentAssigneeClassPK);
	}

	public java.lang.String getType() {
		return _kaleoLog.getType();
	}

	public void setType(java.lang.String type) {
		_kaleoLog.setType(type);
	}

	public java.lang.String getComment() {
		return _kaleoLog.getComment();
	}

	public void setComment(java.lang.String comment) {
		_kaleoLog.setComment(comment);
	}

	public java.util.Date getStartDate() {
		return _kaleoLog.getStartDate();
	}

	public void setStartDate(java.util.Date startDate) {
		_kaleoLog.setStartDate(startDate);
	}

	public java.util.Date getEndDate() {
		return _kaleoLog.getEndDate();
	}

	public void setEndDate(java.util.Date endDate) {
		_kaleoLog.setEndDate(endDate);
	}

	public long getDuration() {
		return _kaleoLog.getDuration();
	}

	public void setDuration(long duration) {
		_kaleoLog.setDuration(duration);
	}

	public java.lang.String getWorkflowContext() {
		return _kaleoLog.getWorkflowContext();
	}

	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoLog.setWorkflowContext(workflowContext);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog toEscapedModel() {
		return _kaleoLog.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoLog.isNew();
	}

	public void setNew(boolean n) {
		_kaleoLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoLog.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoLog.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoLog.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoLog.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoLog.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return _kaleoLog.compareTo(kaleoLog);
	}

	public int hashCode() {
		return _kaleoLog.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoLog.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoLog.toXmlString();
	}

	public KaleoLog getWrappedKaleoLog() {
		return _kaleoLog;
	}

	private KaleoLog _kaleoLog;
}