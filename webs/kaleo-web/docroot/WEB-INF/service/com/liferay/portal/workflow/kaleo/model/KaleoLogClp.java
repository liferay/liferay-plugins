/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoLogClp extends BaseModelImpl<KaleoLog> implements KaleoLog {
	public KaleoLogClp() {
	}

	public Class<?> getModelClass() {
		return KaleoLog.class;
	}

	public String getModelClassName() {
		return KaleoLog.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoLogId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoLogId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoLogId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getKaleoLogId() {
		return _kaleoLogId;
	}

	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLogId = kaleoLogId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;
	}

	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;
	}

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;
	}

	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;
	}

	public String getKaleoNodeName() {
		return _kaleoNodeName;
	}

	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	public boolean getTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	public boolean isTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_terminalKaleoNode = terminalKaleoNode;
	}

	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;
	}

	public String getKaleoActionName() {
		return _kaleoActionName;
	}

	public void setKaleoActionName(String kaleoActionName) {
		_kaleoActionName = kaleoActionName;
	}

	public String getKaleoActionDescription() {
		return _kaleoActionDescription;
	}

	public void setKaleoActionDescription(String kaleoActionDescription) {
		_kaleoActionDescription = kaleoActionDescription;
	}

	public long getPreviousKaleoNodeId() {
		return _previousKaleoNodeId;
	}

	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_previousKaleoNodeId = previousKaleoNodeId;
	}

	public String getPreviousKaleoNodeName() {
		return _previousKaleoNodeName;
	}

	public void setPreviousKaleoNodeName(String previousKaleoNodeName) {
		_previousKaleoNodeName = previousKaleoNodeName;
	}

	public String getPreviousAssigneeClassName() {
		return _previousAssigneeClassName;
	}

	public void setPreviousAssigneeClassName(String previousAssigneeClassName) {
		_previousAssigneeClassName = previousAssigneeClassName;
	}

	public long getPreviousAssigneeClassPK() {
		return _previousAssigneeClassPK;
	}

	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_previousAssigneeClassPK = previousAssigneeClassPK;
	}

	public String getCurrentAssigneeClassName() {
		return _currentAssigneeClassName;
	}

	public void setCurrentAssigneeClassName(String currentAssigneeClassName) {
		_currentAssigneeClassName = currentAssigneeClassName;
	}

	public long getCurrentAssigneeClassPK() {
		return _currentAssigneeClassPK;
	}

	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_currentAssigneeClassPK = currentAssigneeClassPK;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getComment() {
		return _comment;
	}

	public void setComment(String comment) {
		_comment = comment;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getDuration() {
		return _duration;
	}

	public void setDuration(long duration) {
		_duration = duration;
	}

	public String getWorkflowContext() {
		return _workflowContext;
	}

	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	public void persist() throws SystemException {
		KaleoLogLocalServiceUtil.updateKaleoLog(this);
	}

	@Override
	public KaleoLog toEscapedModel() {
		return (KaleoLog)Proxy.newProxyInstance(KaleoLog.class.getClassLoader(),
			new Class[] { KaleoLog.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoLogClp clone = new KaleoLogClp();

		clone.setKaleoLogId(getKaleoLogId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoClassName(getKaleoClassName());
		clone.setKaleoClassPK(getKaleoClassPK());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setKaleoNodeName(getKaleoNodeName());
		clone.setTerminalKaleoNode(getTerminalKaleoNode());
		clone.setKaleoActionId(getKaleoActionId());
		clone.setKaleoActionName(getKaleoActionName());
		clone.setKaleoActionDescription(getKaleoActionDescription());
		clone.setPreviousKaleoNodeId(getPreviousKaleoNodeId());
		clone.setPreviousKaleoNodeName(getPreviousKaleoNodeName());
		clone.setPreviousAssigneeClassName(getPreviousAssigneeClassName());
		clone.setPreviousAssigneeClassPK(getPreviousAssigneeClassPK());
		clone.setCurrentAssigneeClassName(getCurrentAssigneeClassName());
		clone.setCurrentAssigneeClassPK(getCurrentAssigneeClassPK());
		clone.setType(getType());
		clone.setComment(getComment());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setDuration(getDuration());
		clone.setWorkflowContext(getWorkflowContext());

		return clone;
	}

	public int compareTo(KaleoLog kaleoLog) {
		int value = 0;

		if (getKaleoLogId() < kaleoLog.getKaleoLogId()) {
			value = -1;
		}
		else if (getKaleoLogId() > kaleoLog.getKaleoLogId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KaleoLogClp kaleoLog = null;

		try {
			kaleoLog = (KaleoLogClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoLog.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{kaleoLogId=");
		sb.append(getKaleoLogId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", kaleoClassName=");
		sb.append(getKaleoClassName());
		sb.append(", kaleoClassPK=");
		sb.append(getKaleoClassPK());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
		sb.append(", kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append(", kaleoNodeName=");
		sb.append(getKaleoNodeName());
		sb.append(", terminalKaleoNode=");
		sb.append(getTerminalKaleoNode());
		sb.append(", kaleoActionId=");
		sb.append(getKaleoActionId());
		sb.append(", kaleoActionName=");
		sb.append(getKaleoActionName());
		sb.append(", kaleoActionDescription=");
		sb.append(getKaleoActionDescription());
		sb.append(", previousKaleoNodeId=");
		sb.append(getPreviousKaleoNodeId());
		sb.append(", previousKaleoNodeName=");
		sb.append(getPreviousKaleoNodeName());
		sb.append(", previousAssigneeClassName=");
		sb.append(getPreviousAssigneeClassName());
		sb.append(", previousAssigneeClassPK=");
		sb.append(getPreviousAssigneeClassPK());
		sb.append(", currentAssigneeClassName=");
		sb.append(getCurrentAssigneeClassName());
		sb.append(", currentAssigneeClassPK=");
		sb.append(getCurrentAssigneeClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", duration=");
		sb.append(getDuration());
		sb.append(", workflowContext=");
		sb.append(getWorkflowContext());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(94);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoLogId</column-name><column-value><![CDATA[");
		sb.append(getKaleoLogId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassName</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassPK</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>terminalKaleoNode</column-name><column-value><![CDATA[");
		sb.append(getTerminalKaleoNode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionName</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionDescription</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getPreviousKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getPreviousKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousAssigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getPreviousAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousAssigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getPreviousAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentAssigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getCurrentAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentAssigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getCurrentAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>duration</column-name><column-value><![CDATA[");
		sb.append(getDuration());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowContext</column-name><column-value><![CDATA[");
		sb.append(getWorkflowContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoLogId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private String _kaleoNodeName;
	private boolean _terminalKaleoNode;
	private long _kaleoActionId;
	private String _kaleoActionName;
	private String _kaleoActionDescription;
	private long _previousKaleoNodeId;
	private String _previousKaleoNodeName;
	private String _previousAssigneeClassName;
	private long _previousAssigneeClassPK;
	private String _currentAssigneeClassName;
	private long _currentAssigneeClassPK;
	private String _type;
	private String _comment;
	private Date _startDate;
	private Date _endDate;
	private long _duration;
	private String _workflowContext;
}