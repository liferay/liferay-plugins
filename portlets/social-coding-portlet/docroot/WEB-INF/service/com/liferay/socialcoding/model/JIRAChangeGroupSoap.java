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
public class JIRAChangeGroupSoap implements Serializable {
	public static JIRAChangeGroupSoap toSoapModel(JIRAChangeGroup model) {
		JIRAChangeGroupSoap soapModel = new JIRAChangeGroupSoap();

		soapModel.setJiraChangeGroupId(model.getJiraChangeGroupId());
		soapModel.setJiraUserId(model.getJiraUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setJiraIssueId(model.getJiraIssueId());

		return soapModel;
	}

	public static JIRAChangeGroupSoap[] toSoapModels(JIRAChangeGroup[] models) {
		JIRAChangeGroupSoap[] soapModels = new JIRAChangeGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JIRAChangeGroupSoap[][] toSoapModels(
		JIRAChangeGroup[][] models) {
		JIRAChangeGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JIRAChangeGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JIRAChangeGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JIRAChangeGroupSoap[] toSoapModels(
		List<JIRAChangeGroup> models) {
		List<JIRAChangeGroupSoap> soapModels = new ArrayList<JIRAChangeGroupSoap>(models.size());

		for (JIRAChangeGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JIRAChangeGroupSoap[soapModels.size()]);
	}

	public JIRAChangeGroupSoap() {
	}

	public long getPrimaryKey() {
		return _jiraChangeGroupId;
	}

	public void setPrimaryKey(long pk) {
		setJiraChangeGroupId(pk);
	}

	public long getJiraChangeGroupId() {
		return _jiraChangeGroupId;
	}

	public void setJiraChangeGroupId(long jiraChangeGroupId) {
		_jiraChangeGroupId = jiraChangeGroupId;
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

	public long getJiraIssueId() {
		return _jiraIssueId;
	}

	public void setJiraIssueId(long jiraIssueId) {
		_jiraIssueId = jiraIssueId;
	}

	private long _jiraChangeGroupId;
	private String _jiraUserId;
	private Date _createDate;
	private long _jiraIssueId;
}