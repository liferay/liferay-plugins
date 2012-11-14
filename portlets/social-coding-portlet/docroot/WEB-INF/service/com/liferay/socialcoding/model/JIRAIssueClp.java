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

import com.liferay.socialcoding.service.JIRAIssueLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class JIRAIssueClp extends BaseModelImpl<JIRAIssue> implements JIRAIssue {
	public JIRAIssueClp() {
	}

	public Class<?> getModelClass() {
		return JIRAIssue.class;
	}

	public String getModelClassName() {
		return JIRAIssue.class.getName();
	}

	public long getPrimaryKey() {
		return _jiraIssueId;
	}

	public void setPrimaryKey(long primaryKey) {
		setJiraIssueId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_jiraIssueId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("jiraIssueId", getJiraIssueId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("projectId", getProjectId());
		attributes.put("key", getKey());
		attributes.put("summary", getSummary());
		attributes.put("description", getDescription());
		attributes.put("reporterJiraUserId", getReporterJiraUserId());
		attributes.put("assigneeJiraUserId", getAssigneeJiraUserId());
		attributes.put("resolution", getResolution());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long jiraIssueId = (Long)attributes.get("jiraIssueId");

		if (jiraIssueId != null) {
			setJiraIssueId(jiraIssueId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long projectId = (Long)attributes.get("projectId");

		if (projectId != null) {
			setProjectId(projectId);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String reporterJiraUserId = (String)attributes.get("reporterJiraUserId");

		if (reporterJiraUserId != null) {
			setReporterJiraUserId(reporterJiraUserId);
		}

		String assigneeJiraUserId = (String)attributes.get("assigneeJiraUserId");

		if (assigneeJiraUserId != null) {
			setAssigneeJiraUserId(assigneeJiraUserId);
		}

		String resolution = (String)attributes.get("resolution");

		if (resolution != null) {
			setResolution(resolution);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
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

	public long getProjectId() {
		return _projectId;
	}

	public void setProjectId(long projectId) {
		_projectId = projectId;
	}

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getReporterJiraUserId() {
		return _reporterJiraUserId;
	}

	public void setReporterJiraUserId(String reporterJiraUserId) {
		_reporterJiraUserId = reporterJiraUserId;
	}

	public String getAssigneeJiraUserId() {
		return _assigneeJiraUserId;
	}

	public void setAssigneeJiraUserId(String assigneeJiraUserId) {
		_assigneeJiraUserId = assigneeJiraUserId;
	}

	public String getResolution() {
		return _resolution;
	}

	public void setResolution(String resolution) {
		_resolution = resolution;
	}

	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	public BaseModel<?> getJIRAIssueRemoteModel() {
		return _jiraIssueRemoteModel;
	}

	public void setJIRAIssueRemoteModel(BaseModel<?> jiraIssueRemoteModel) {
		_jiraIssueRemoteModel = jiraIssueRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			JIRAIssueLocalServiceUtil.addJIRAIssue(this);
		}
		else {
			JIRAIssueLocalServiceUtil.updateJIRAIssue(this);
		}
	}

	@Override
	public JIRAIssue toEscapedModel() {
		return (JIRAIssue)ProxyUtil.newProxyInstance(JIRAIssue.class.getClassLoader(),
			new Class[] { JIRAIssue.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		JIRAIssueClp clone = new JIRAIssueClp();

		clone.setJiraIssueId(getJiraIssueId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setProjectId(getProjectId());
		clone.setKey(getKey());
		clone.setSummary(getSummary());
		clone.setDescription(getDescription());
		clone.setReporterJiraUserId(getReporterJiraUserId());
		clone.setAssigneeJiraUserId(getAssigneeJiraUserId());
		clone.setResolution(getResolution());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(JIRAIssue jiraIssue) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				jiraIssue.getModifiedDate());

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

		JIRAIssueClp jiraIssue = null;

		try {
			jiraIssue = (JIRAIssueClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = jiraIssue.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{jiraIssueId=");
		sb.append(getJiraIssueId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", projectId=");
		sb.append(getProjectId());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", summary=");
		sb.append(getSummary());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", reporterJiraUserId=");
		sb.append(getReporterJiraUserId());
		sb.append(", assigneeJiraUserId=");
		sb.append(getAssigneeJiraUserId());
		sb.append(", resolution=");
		sb.append(getResolution());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.socialcoding.model.JIRAIssue");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>jiraIssueId</column-name><column-value><![CDATA[");
		sb.append(getJiraIssueId());
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
			"<column><column-name>projectId</column-name><column-value><![CDATA[");
		sb.append(getProjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>summary</column-name><column-value><![CDATA[");
		sb.append(getSummary());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>reporterJiraUserId</column-name><column-value><![CDATA[");
		sb.append(getReporterJiraUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assigneeJiraUserId</column-name><column-value><![CDATA[");
		sb.append(getAssigneeJiraUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resolution</column-name><column-value><![CDATA[");
		sb.append(getResolution());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _jiraIssueId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _projectId;
	private String _key;
	private String _summary;
	private String _description;
	private String _reporterJiraUserId;
	private String _assigneeJiraUserId;
	private String _resolution;
	private String _status;
	private BaseModel<?> _jiraIssueRemoteModel;
}