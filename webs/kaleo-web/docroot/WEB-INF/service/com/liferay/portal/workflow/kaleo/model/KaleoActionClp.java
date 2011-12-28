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
import com.liferay.portal.workflow.kaleo.service.KaleoActionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoActionClp extends BaseModelImpl<KaleoAction>
	implements KaleoAction {
	public KaleoActionClp() {
	}

	public Class<?> getModelClass() {
		return KaleoAction.class;
	}

	public String getModelClassName() {
		return KaleoAction.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoActionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoActionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoActionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;
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

	public String getKaleoNodeName() {
		return _kaleoNodeName;
	}

	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getExecutionType() {
		return _executionType;
	}

	public void setExecutionType(String executionType) {
		_executionType = executionType;
	}

	public String getScript() {
		return _script;
	}

	public void setScript(String script) {
		_script = script;
	}

	public String getScriptLanguage() {
		return _scriptLanguage;
	}

	public void setScriptLanguage(String scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public void persist() throws SystemException {
		KaleoActionLocalServiceUtil.updateKaleoAction(this);
	}

	@Override
	public KaleoAction toEscapedModel() {
		return (KaleoAction)Proxy.newProxyInstance(KaleoAction.class.getClassLoader(),
			new Class[] { KaleoAction.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoActionClp clone = new KaleoActionClp();

		clone.setKaleoActionId(getKaleoActionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoClassName(getKaleoClassName());
		clone.setKaleoClassPK(getKaleoClassPK());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeName(getKaleoNodeName());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setExecutionType(getExecutionType());
		clone.setScript(getScript());
		clone.setScriptLanguage(getScriptLanguage());
		clone.setPriority(getPriority());

		return clone;
	}

	public int compareTo(KaleoAction kaleoAction) {
		int value = 0;

		if (getPriority() < kaleoAction.getPriority()) {
			value = -1;
		}
		else if (getPriority() > kaleoAction.getPriority()) {
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

		KaleoActionClp kaleoAction = null;

		try {
			kaleoAction = (KaleoActionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoAction.getPrimaryKey();

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
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoActionId=");
		sb.append(getKaleoActionId());
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
		sb.append(", kaleoNodeName=");
		sb.append(getKaleoNodeName());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", executionType=");
		sb.append(getExecutionType());
		sb.append(", script=");
		sb.append(getScript());
		sb.append(", scriptLanguage=");
		sb.append(getScriptLanguage());
		sb.append(", priority=");
		sb.append(getPriority());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(55);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoAction");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoActionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionId());
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
			"<column><column-name>kaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>executionType</column-name><column-value><![CDATA[");
		sb.append(getExecutionType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>script</column-name><column-value><![CDATA[");
		sb.append(getScript());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>scriptLanguage</column-name><column-value><![CDATA[");
		sb.append(getScriptLanguage());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>priority</column-name><column-value><![CDATA[");
		sb.append(getPriority());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoActionId;
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
	private String _kaleoNodeName;
	private String _name;
	private String _description;
	private String _executionType;
	private String _script;
	private String _scriptLanguage;
	private int _priority;
}