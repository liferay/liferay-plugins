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

package com.liferay.socialcoding.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.JIRAActionLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAActionClp extends BaseModelImpl<JIRAAction>
	implements JIRAAction {
	public JIRAActionClp() {
	}

	public Class<?> getModelClass() {
		return JIRAAction.class;
	}

	public String getModelClassName() {
		return JIRAAction.class.getName();
	}

	public long getPrimaryKey() {
		return _jiraActionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setJiraActionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraActionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraActionId", getJiraActionId());
		attributes.put("jiraUserId", getJiraUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("jiraIssueId", getJiraIssueId());
		attributes.put("type", getType());
		attributes.put("body", getBody());
		attributes.put("jiraGroupName", getJiraGroupName());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraActionId = (Long)attributes.get("jiraActionId");

		if (jiraActionId != null) {
			setJiraActionId(jiraActionId);
		}

		String jiraUserId = (String)attributes.get("jiraUserId");

		if (jiraUserId != null) {
			setJiraUserId(jiraUserId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String body = (String)attributes.get("body");

		if (body != null) {
			setBody(body);
		}

		String jiraGroupName = (String)attributes.get("jiraGroupName");

		if (jiraGroupName != null) {
			setJiraGroupName(jiraGroupName);
		}
	}

	public long getJiraActionId() {
		return _jiraActionId;
	}

	public void setJiraActionId(long jiraActionId) {
		_jiraActionId = jiraActionId;
	}

	public String getJiraUserId() {
		return _jiraUserId;
	}

	public void setJiraUserId(String jiraUserId) {
		_jiraUserId = jiraUserId;
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

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getBody() {
		return _body;
	}

	public void setBody(String body) {
		_body = body;
	}

	public String getJiraGroupName() {
		return _jiraGroupName;
	}

	public void setJiraGroupName(String jiraGroupName) {
		_jiraGroupName = jiraGroupName;
	}

	public BaseModel<?> getJIRAActionRemoteModel() {
		return _jiraActionRemoteModel;
	}

	public void setJIRAActionRemoteModel(BaseModel<?> jiraActionRemoteModel) {
		_jiraActionRemoteModel = jiraActionRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			JIRAActionLocalServiceUtil.addJIRAAction(this);
		}
		else {
			JIRAActionLocalServiceUtil.updateJIRAAction(this);
		}
	}

	@Override
	public JIRAAction toEscapedModel() {
		return (JIRAAction)ProxyUtil.newProxyInstance(JIRAAction.class.getClassLoader(),
			new Class[] { JIRAAction.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		JIRAActionClp clone = new JIRAActionClp();

		clone.setJiraActionId(getJiraActionId());
		clone.setJiraUserId(getJiraUserId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setJiraIssueId(getJiraIssueId());
		clone.setType(getType());
		clone.setBody(getBody());
		clone.setJiraGroupName(getJiraGroupName());

		return clone;
	}

	public int compareTo(JIRAAction jiraAction) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				jiraAction.getModifiedDate());

		value = value * -1;

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

		JIRAActionClp jiraAction = null;

		try {
			jiraAction = (JIRAActionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = jiraAction.getPrimaryKey();

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
		StringBundler sb = new StringBundler(17);

		sb.append("{jiraActionId=");
		sb.append(getJiraActionId());
		sb.append(", jiraUserId=");
		sb.append(getJiraUserId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", jiraIssueId=");
		sb.append(getJiraIssueId());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", body=");
		sb.append(getBody());
		sb.append(", jiraGroupName=");
		sb.append(getJiraGroupName());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAAction");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraActionId</column-name><column-value><![CDATA[");
		sb.append(getJiraActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraUserId</column-name><column-value><![CDATA[");
		sb.append(getJiraUserId());
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
			"<column><column-name>jiraIssueId</column-name><column-value><![CDATA[");
		sb.append(getJiraIssueId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>body</column-name><column-value><![CDATA[");
		sb.append(getBody());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>jiraGroupName</column-name><column-value><![CDATA[");
		sb.append(getJiraGroupName());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraActionId;
	private String _jiraUserId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _jiraIssueId;
	private String _type;
	private String _body;
	private String _jiraGroupName;
	private BaseModel<?> _jiraActionRemoteModel;
}