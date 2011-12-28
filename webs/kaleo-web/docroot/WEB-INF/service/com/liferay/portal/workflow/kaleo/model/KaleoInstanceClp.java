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
import com.liferay.portal.workflow.kaleo.service.KaleoInstanceLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoInstanceClp extends BaseModelImpl<KaleoInstance>
	implements KaleoInstance {
	public KaleoInstanceClp() {
	}

	public Class<?> getModelClass() {
		return KaleoInstance.class;
	}

	public String getModelClassName() {
		return KaleoInstance.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoInstanceId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoInstanceId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoInstanceId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;
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

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public String getKaleoDefinitionName() {
		return _kaleoDefinitionName;
	}

	public void setKaleoDefinitionName(String kaleoDefinitionName) {
		_kaleoDefinitionName = kaleoDefinitionName;
	}

	public int getKaleoDefinitionVersion() {
		return _kaleoDefinitionVersion;
	}

	public void setKaleoDefinitionVersion(int kaleoDefinitionVersion) {
		_kaleoDefinitionVersion = kaleoDefinitionVersion;
	}

	public long getRootKaleoInstanceTokenId() {
		return _rootKaleoInstanceTokenId;
	}

	public void setRootKaleoInstanceTokenId(long rootKaleoInstanceTokenId) {
		_rootKaleoInstanceTokenId = rootKaleoInstanceTokenId;
	}

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
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

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition getKaleoDefinition() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext) {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getRootKaleoInstanceToken(
		com.liferay.portal.service.ServiceContext serviceContext) {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		KaleoInstanceLocalServiceUtil.updateKaleoInstance(this);
	}

	@Override
	public KaleoInstance toEscapedModel() {
		return (KaleoInstance)Proxy.newProxyInstance(KaleoInstance.class.getClassLoader(),
			new Class[] { KaleoInstance.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoInstanceClp clone = new KaleoInstanceClp();

		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoDefinitionName(getKaleoDefinitionName());
		clone.setKaleoDefinitionVersion(getKaleoDefinitionVersion());
		clone.setRootKaleoInstanceTokenId(getRootKaleoInstanceTokenId());
		clone.setClassName(getClassName());
		clone.setClassPK(getClassPK());
		clone.setCompleted(getCompleted());
		clone.setCompletionDate(getCompletionDate());
		clone.setWorkflowContext(getWorkflowContext());

		return clone;
	}

	public int compareTo(KaleoInstance kaleoInstance) {
		int value = 0;

		if (getKaleoInstanceId() < kaleoInstance.getKaleoInstanceId()) {
			value = -1;
		}
		else if (getKaleoInstanceId() > kaleoInstance.getKaleoInstanceId()) {
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

		KaleoInstanceClp kaleoInstance = null;

		try {
			kaleoInstance = (KaleoInstanceClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoInstance.getPrimaryKey();

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
		StringBundler sb = new StringBundler(33);

		sb.append("{kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
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
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoDefinitionName=");
		sb.append(getKaleoDefinitionName());
		sb.append(", kaleoDefinitionVersion=");
		sb.append(getKaleoDefinitionVersion());
		sb.append(", rootKaleoInstanceTokenId=");
		sb.append(getRootKaleoInstanceTokenId());
		sb.append(", className=");
		sb.append(getClassName());
		sb.append(", classPK=");
		sb.append(getClassPK());
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
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoInstance");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
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
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionName</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionVersion</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>rootKaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getRootKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>className</column-name><column-value><![CDATA[");
		sb.append(getClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
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

	private long _kaleoInstanceId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private String _kaleoDefinitionName;
	private int _kaleoDefinitionVersion;
	private long _rootKaleoInstanceTokenId;
	private String _className;
	private long _classPK;
	private boolean _completed;
	private Date _completionDate;
	private String _workflowContext;
}