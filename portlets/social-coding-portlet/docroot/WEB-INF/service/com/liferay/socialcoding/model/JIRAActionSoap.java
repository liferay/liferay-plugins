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
public class JIRAActionSoap implements Serializable {
	public static JIRAActionSoap toSoapModel(JIRAAction model) {
		JIRAActionSoap soapModel = new JIRAActionSoap();

		soapModel.setJiraActionId(model.getJiraActionId());
		soapModel.setJiraUserId(model.getJiraUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setJiraIssueId(model.getJiraIssueId());
		soapModel.setType(model.getType());
		soapModel.setBody(model.getBody());
		soapModel.setJiraGroupName(model.getJiraGroupName());

		return soapModel;
	}

	public static JIRAActionSoap[] toSoapModels(JIRAAction[] models) {
		JIRAActionSoap[] soapModels = new JIRAActionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAActionSoap[][] toSoapModels(JIRAAction[][] models) {
		JIRAActionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAActionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAActionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAActionSoap[] toSoapModels(List<JIRAAction> models) {
		List<JIRAActionSoap> soapModels = new ArrayList<JIRAActionSoap>(models.size());

		for (JIRAAction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAActionSoap[soapModels.size()]);
	}

	public JIRAActionSoap() {
	}

	public long getPrimaryKey() {
		return _jiraActionId;
	}

	public void setPrimaryKey(long pk) {
		setJiraActionId(pk);
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

	private long _jiraActionId;
	private String _jiraUserId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _jiraIssueId;
	private String _type;
	private String _body;
	private String _jiraGroupName;
}