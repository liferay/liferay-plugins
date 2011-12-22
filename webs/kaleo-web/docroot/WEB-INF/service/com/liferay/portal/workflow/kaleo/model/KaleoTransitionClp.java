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
import com.liferay.portal.workflow.kaleo.service.KaleoTransitionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoTransitionClp extends BaseModelImpl<KaleoTransition>
	implements KaleoTransition {
	public KaleoTransitionClp() {
	}

	public Class<?> getModelClass() {
		return KaleoTransition.class;
	}

	public String getModelClassName() {
		return KaleoTransition.class.getName();
	}

	public long getPrimaryKey() {
		return _kaleoTransitionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKaleoTransitionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kaleoTransitionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getKaleoTransitionId() {
		return _kaleoTransitionId;
	}

	public void setKaleoTransitionId(long kaleoTransitionId) {
		_kaleoTransitionId = kaleoTransitionId;
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

	public long getKaleoNodeId() {
		return _kaleoNodeId;
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNodeId = kaleoNodeId;
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

	public long getSourceKaleoNodeId() {
		return _sourceKaleoNodeId;
	}

	public void setSourceKaleoNodeId(long sourceKaleoNodeId) {
		_sourceKaleoNodeId = sourceKaleoNodeId;
	}

	public String getSourceKaleoNodeName() {
		return _sourceKaleoNodeName;
	}

	public void setSourceKaleoNodeName(String sourceKaleoNodeName) {
		_sourceKaleoNodeName = sourceKaleoNodeName;
	}

	public long getTargetKaleoNodeId() {
		return _targetKaleoNodeId;
	}

	public void setTargetKaleoNodeId(long targetKaleoNodeId) {
		_targetKaleoNodeId = targetKaleoNodeId;
	}

	public String getTargetKaleoNodeName() {
		return _targetKaleoNodeName;
	}

	public void setTargetKaleoNodeName(String targetKaleoNodeName) {
		_targetKaleoNodeName = targetKaleoNodeName;
	}

	public boolean getDefaultTransition() {
		return _defaultTransition;
	}

	public boolean isDefaultTransition() {
		return _defaultTransition;
	}

	public void setDefaultTransition(boolean defaultTransition) {
		_defaultTransition = defaultTransition;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getSourceKaleoNode() {
		throw new UnsupportedOperationException();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getTargetKaleoNode() {
		throw new UnsupportedOperationException();
	}

	public void persist() throws SystemException {
		KaleoTransitionLocalServiceUtil.updateKaleoTransition(this);
	}

	@Override
	public KaleoTransition toEscapedModel() {
		return (KaleoTransition)Proxy.newProxyInstance(KaleoTransition.class.getClassLoader(),
			new Class[] { KaleoTransition.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoTransitionClp clone = new KaleoTransitionClp();

		clone.setKaleoTransitionId(getKaleoTransitionId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoNodeId(getKaleoNodeId());
		clone.setName(getName());
		clone.setDescription(getDescription());
		clone.setSourceKaleoNodeId(getSourceKaleoNodeId());
		clone.setSourceKaleoNodeName(getSourceKaleoNodeName());
		clone.setTargetKaleoNodeId(getTargetKaleoNodeId());
		clone.setTargetKaleoNodeName(getTargetKaleoNodeName());
		clone.setDefaultTransition(getDefaultTransition());

		return clone;
	}

	public int compareTo(KaleoTransition kaleoTransition) {
		int value = 0;

		if (getKaleoTransitionId() < kaleoTransition.getKaleoTransitionId()) {
			value = -1;
		}
		else if (getKaleoTransitionId() > kaleoTransition.getKaleoTransitionId()) {
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

		KaleoTransitionClp kaleoTransition = null;

		try {
			kaleoTransition = (KaleoTransitionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kaleoTransition.getPrimaryKey();

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

		sb.append("{kaleoTransitionId=");
		sb.append(getKaleoTransitionId());
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
		sb.append(", kaleoNodeId=");
		sb.append(getKaleoNodeId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", sourceKaleoNodeId=");
		sb.append(getSourceKaleoNodeId());
		sb.append(", sourceKaleoNodeName=");
		sb.append(getSourceKaleoNodeName());
		sb.append(", targetKaleoNodeId=");
		sb.append(getTargetKaleoNodeId());
		sb.append(", targetKaleoNodeName=");
		sb.append(getTargetKaleoNodeName());
		sb.append(", defaultTransition=");
		sb.append(getDefaultTransition());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(52);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoTransition");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoTransitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTransitionId());
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
			"<column><column-name>kaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeId());
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
			"<column><column-name>sourceKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getSourceKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sourceKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getSourceKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>targetKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getTargetKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>targetKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getTargetKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultTransition</column-name><column-value><![CDATA[");
		sb.append(getDefaultTransition());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoTransitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNodeId;
	private String _name;
	private String _description;
	private long _sourceKaleoNodeId;
	private String _sourceKaleoNodeName;
	private long _targetKaleoNodeId;
	private String _targetKaleoNodeName;
	private boolean _defaultTransition;
}