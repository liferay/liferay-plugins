/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.socialcoding.service.ClpSerializer;
import com.liferay.socialcoding.service.JIRAIssueLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class JIRAIssueClp extends BaseModelImpl<JIRAIssue> implements JIRAIssue {
	public JIRAIssueClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return JIRAIssue.class;
	}

	@Override
	public String getModelClassName() {
		return JIRAIssue.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _jiraIssueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setJiraIssueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _jiraIssueId;
	}

	@Override
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
		attributes.put("issueNumber", getIssueNumber());
		attributes.put("summary", getSummary());
		attributes.put("description", getDescription());
		attributes.put("reporterJiraUserId", getReporterJiraUserId());
		attributes.put("assigneeJiraUserId", getAssigneeJiraUserId());
		attributes.put("resolution", getResolution());
		attributes.put("status", getStatus());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		Long issueNumber = (Long)attributes.get("issueNumber");

		if (issueNumber != null) {
			setIssueNumber(issueNumber);
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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	@Override
	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setJiraIssueId", long.class);

				method.invoke(_jiraIssueRemoteModel, jiraIssueId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_jiraIssueRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_jiraIssueRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getProjectId() {
		return _projectId;
	}

	@Override
	public void setProjectId(long projectId) {
		_projectId = projectId;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setProjectId", long.class);

				method.invoke(_jiraIssueRemoteModel, projectId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getIssueNumber() {
		return _issueNumber;
	}

	@Override
	public void setIssueNumber(long issueNumber) {
		_issueNumber = issueNumber;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setIssueNumber", long.class);

				method.invoke(_jiraIssueRemoteModel, issueNumber);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSummary() {
		return _summary;
	}

	@Override
	public void setSummary(String summary) {
		_summary = summary;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setSummary", String.class);

				method.invoke(_jiraIssueRemoteModel, summary);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_jiraIssueRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getReporterJiraUserId() {
		return _reporterJiraUserId;
	}

	@Override
	public void setReporterJiraUserId(String reporterJiraUserId) {
		_reporterJiraUserId = reporterJiraUserId;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setReporterJiraUserId",
						String.class);

				method.invoke(_jiraIssueRemoteModel, reporterJiraUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAssigneeJiraUserId() {
		return _assigneeJiraUserId;
	}

	@Override
	public void setAssigneeJiraUserId(String assigneeJiraUserId) {
		_assigneeJiraUserId = assigneeJiraUserId;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setAssigneeJiraUserId",
						String.class);

				method.invoke(_jiraIssueRemoteModel, assigneeJiraUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getResolution() {
		return _resolution;
	}

	@Override
	public void setResolution(String resolution) {
		_resolution = resolution;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setResolution", String.class);

				method.invoke(_jiraIssueRemoteModel, resolution);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getStatus() {
		return _status;
	}

	@Override
	public void setStatus(String status) {
		_status = status;

		if (_jiraIssueRemoteModel != null) {
			try {
				Class<?> clazz = _jiraIssueRemoteModel.getClass();

				Method method = clazz.getMethod("setStatus", String.class);

				method.invoke(_jiraIssueRemoteModel, status);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public java.lang.String getKey() {
		try {
			String methodName = "getKey";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getJIRAIssueRemoteModel() {
		return _jiraIssueRemoteModel;
	}

	public void setJIRAIssueRemoteModel(BaseModel<?> jiraIssueRemoteModel) {
		_jiraIssueRemoteModel = jiraIssueRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _jiraIssueRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_jiraIssueRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
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
		clone.setIssueNumber(getIssueNumber());
		clone.setSummary(getSummary());
		clone.setDescription(getDescription());
		clone.setReporterJiraUserId(getReporterJiraUserId());
		clone.setAssigneeJiraUserId(getAssigneeJiraUserId());
		clone.setResolution(getResolution());
		clone.setStatus(getStatus());

		return clone;
	}

	@Override
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
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof JIRAIssueClp)) {
			return false;
		}

		JIRAIssueClp jiraIssue = (JIRAIssueClp)obj;

		long primaryKey = jiraIssue.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
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
		sb.append(", issueNumber=");
		sb.append(getIssueNumber());
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

	@Override
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
			"<column><column-name>issueNumber</column-name><column-value><![CDATA[");
		sb.append(getIssueNumber());
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
	private long _issueNumber;
	private String _summary;
	private String _description;
	private String _reporterJiraUserId;
	private String _assigneeJiraUserId;
	private String _resolution;
	private String _status;
	private BaseModel<?> _jiraIssueRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}