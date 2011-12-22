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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class JIRAIssueSoap implements Serializable {
	public static JIRAIssueSoap toSoapModel(JIRAIssue model) {
		JIRAIssueSoap soapModel = new JIRAIssueSoap();

		soapModel.setJiraIssueId(model.getJiraIssueId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setProjectId(model.getProjectId());
		soapModel.setKey(model.getKey());
		soapModel.setSummary(model.getSummary());
		soapModel.setDescription(model.getDescription());
		soapModel.setReporterJiraUserId(model.getReporterJiraUserId());
		soapModel.setAssigneeJiraUserId(model.getAssigneeJiraUserId());
		soapModel.setResolution(model.getResolution());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static JIRAIssueSoap[] toSoapModels(JIRAIssue[] models) {
		JIRAIssueSoap[] soapModels = new JIRAIssueSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAIssueSoap[][] toSoapModels(JIRAIssue[][] models) {
		JIRAIssueSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAIssueSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAIssueSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAIssueSoap[] toSoapModels(List<JIRAIssue> models) {
		List<JIRAIssueSoap> soapModels = new ArrayList<JIRAIssueSoap>(models.size());

		for (JIRAIssue model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAIssueSoap[soapModels.size()]);
	}

	public JIRAIssueSoap() {
	}

	public long getPrimaryKey() {
		return _jiraIssueId;
	}

	public void setPrimaryKey(long pk) {
		setJiraIssueId(pk);
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
}