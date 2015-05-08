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

package com.liferay.so.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class ProjectsEntrySoap implements Serializable {
	public static ProjectsEntrySoap toSoapModel(ProjectsEntry model) {
		ProjectsEntrySoap soapModel = new ProjectsEntrySoap();

		soapModel.setProjectsEntryId(model.getProjectsEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setData(model.getData());

		return soapModel;
	}

	public static ProjectsEntrySoap[] toSoapModels(ProjectsEntry[] models) {
		ProjectsEntrySoap[] soapModels = new ProjectsEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ProjectsEntrySoap[][] toSoapModels(ProjectsEntry[][] models) {
		ProjectsEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ProjectsEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new ProjectsEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ProjectsEntrySoap[] toSoapModels(List<ProjectsEntry> models) {
		List<ProjectsEntrySoap> soapModels = new ArrayList<ProjectsEntrySoap>(models.size());

		for (ProjectsEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ProjectsEntrySoap[soapModels.size()]);
	}

	public ProjectsEntrySoap() {
	}

	public long getPrimaryKey() {
		return _projectsEntryId;
	}

	public void setPrimaryKey(long pk) {
		setProjectsEntryId(pk);
	}

	public long getProjectsEntryId() {
		return _projectsEntryId;
	}

	public void setProjectsEntryId(long projectsEntryId) {
		_projectsEntryId = projectsEntryId;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public String getData() {
		return _data;
	}

	public void setData(String data) {
		_data = data;
	}

	private long _projectsEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private Date _startDate;
	private Date _endDate;
	private String _data;
}