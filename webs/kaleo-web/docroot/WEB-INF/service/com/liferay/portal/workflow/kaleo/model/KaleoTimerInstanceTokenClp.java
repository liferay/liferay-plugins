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
import com.liferay.portal.workflow.kaleo.service.KaleoTimerInstanceTokenLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTimerInstanceTokenClp extends BaseModelImpl<KaleoTimerInstanceToken>
	implements KaleoTimerInstanceToken {
	public KaleoTimerInstanceTokenClp() {
	}

	public Class<?> getModelClass() {
		return KaleoTimerInstanceToken.class;
	}

	public String getModelClassName() {
		return KaleoTimerInstanceToken.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoTimerInstanceTokenId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoTimerInstanceTokenId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoTimerInstanceTokenId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getKaleoTimerInstanceTokenId() {
		return _kaleoTimerInstanceTokenId;
	}

	public void setKaleoTimerInstanceTokenId(long kaleoTimerInstanceTokenId) {
		_kaleoTimerInstanceTokenId = kaleoTimerInstanceTokenId;
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

	public long getKaleoTimerId() {
		return _kaleoTimerId;
	}

	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimerId = kaleoTimerId;
	}

	public String getKaleoTimerName() {
		return _kaleoTimerName;
	}

	public void setKaleoTimerName(String kaleoTimerName) {
		_kaleoTimerName = kaleoTimerName;
	}

	public boolean getBlocking() {
		return _blocking;
	}

	public boolean isBlocking() {
		return _blocking;
	}

	public void setBlocking(boolean blocking) {
		_blocking = blocking;
	}

	public long getCompletionUserId() {
		return _completionUserId;
	}

	public void setCompletionUserId(long completionUserId) {
		_completionUserId = completionUserId;
	}

	public String getCompletionUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getCompletionUserId(), "uuid",
			_completionUserUuid);
	}

	public void setCompletionUserUuid(String completionUserUuid) {
		_completionUserUuid = completionUserUuid;
	}

	public boolean getCompleted() {
		return _completed;
	}

	public boolean isCompleted() {
		return _completed;
	}

	public void setCompleted(boolean completed) {
		_completed = completed;
	}

	public Date getCompletionDate() {
		return _completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		_completionDate = completionDate;
	}

	public String getWorkflowContext() {
		return _workflowContext;
	}

	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getKaleoInstanceToken() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTimer getKaleoTimer() {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		KaleoTimerInstanceTokenLocalServiceUtil.updateKaleoTimerInstanceToken(this);
	}

	@Override
	public KaleoTimerInstanceToken toEscapedModel() {
		return (KaleoTimerInstanceToken)Proxy.newProxyInstance(KaleoTimerInstanceToken.class.getClassLoader(),
			new Class[] { KaleoTimerInstanceToken.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoTimerInstanceTokenClp clone = new KaleoTimerInstanceTokenClp();

		clone.setKaleoTimerInstanceTokenId(getKaleoTimerInstanceTokenId());
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
		clone.setKaleoTimerId(getKaleoTimerId());
		clone.setKaleoTimerName(getKaleoTimerName());
		clone.setBlocking(getBlocking());
		clone.setCompletionUserId(getCompletionUserId());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());
		clone.setWorkflowContext(getWorkflowContext());

		return clone;
	}

	public int compareTo(KaleoTimerInstanceToken kaleoTimerInstanceToken) {
		int value = 0;

		if (getKaleoTimerInstanceTokenId() < kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()) {
			value = -1;
		}
		else if (getKaleoTimerInstanceTokenId() > kaleoTimerInstanceToken.getKaleoTimerInstanceTokenId()) {
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

		KaleoTimerInstanceTokenClp kaleoTimerInstanceToken = null;

		try {
			kaleoTimerInstanceToken = (KaleoTimerInstanceTokenClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoTimerInstanceToken.getPrimaryKey();

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
		StringBundler sb = new StringBundler(41);

		sb.append("{kaleoTimerInstanceTokenId=");
		sb.append(getKaleoTimerInstanceTokenId());
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
		sb.append(", kaleoTimerId=");
		sb.append(getKaleoTimerId());
		sb.append(", kaleoTimerName=");
		sb.append(getKaleoTimerName());
		sb.append(", blocking=");
		sb.append(getBlocking());
		sb.append(", completionUserId=");
		sb.append(getCompletionUserId());
		sb.append(", completed=");
		sb.append(getCompleted());
		sb.append(", completionDate=");
		sb.append(getCompletionDate());
		sb.append(", workflowContext=");
		sb.append(getWorkflowContext());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(64);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.workflow.kaleo.model.KaleoTimerInstanceToken");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTimerInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTimerInstanceTokenId());
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
			"<column><column-name>kaleoTimerId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTimerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTimerName</column-name><column-value><![CDATA[");
		sb.append(getKaleoTimerName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>blocking</column-name><column-value><![CDATA[");
		sb.append(getBlocking());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionUserId</column-name><column-value><![CDATA[");
		sb.append(getCompletionUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completed</column-name><column-value><![CDATA[");
		sb.append(getCompleted());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>completionDate</column-name><column-value><![CDATA[");
		sb.append(getCompletionDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowContext</column-name><column-value><![CDATA[");
		sb.append(getWorkflowContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTimerInstanceTokenId;
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
	private long _kaleoTimerId;
	private String _kaleoTimerName;
	private boolean _blocking;
	private long _completionUserId;
	private String _completionUserUuid;
	private boolean _completed;
	private Date _completionDate;
	private String _workflowContext;
}