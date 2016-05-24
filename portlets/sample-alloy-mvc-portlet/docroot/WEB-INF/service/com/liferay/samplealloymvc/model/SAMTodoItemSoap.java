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

package com.liferay.samplealloymvc.model;

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
public class SAMTodoItemSoap implements Serializable {
	public static SAMTodoItemSoap toSoapModel(SAMTodoItem model) {
		SAMTodoItemSoap soapModel = new SAMTodoItemSoap();

		soapModel.setSamTodoItemId(model.getSamTodoItemId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setSamTodoListId(model.getSamTodoListId());
		soapModel.setDescription(model.getDescription());
		soapModel.setPriority(model.getPriority());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static SAMTodoItemSoap[] toSoapModels(SAMTodoItem[] models) {
		SAMTodoItemSoap[] soapModels = new SAMTodoItemSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SAMTodoItemSoap[][] toSoapModels(SAMTodoItem[][] models) {
		SAMTodoItemSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SAMTodoItemSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SAMTodoItemSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SAMTodoItemSoap[] toSoapModels(List<SAMTodoItem> models) {
		List<SAMTodoItemSoap> soapModels = new ArrayList<SAMTodoItemSoap>(models.size());

		for (SAMTodoItem model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SAMTodoItemSoap[soapModels.size()]);
	}

	public SAMTodoItemSoap() {
	}

	public long getPrimaryKey() {
		return _samTodoItemId;
	}

	public void setPrimaryKey(long pk) {
		setSamTodoItemId(pk);
	}

	public long getSamTodoItemId() {
		return _samTodoItemId;
	}

	public void setSamTodoItemId(long samTodoItemId) {
		_samTodoItemId = samTodoItemId;
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

	public long getSamTodoListId() {
		return _samTodoListId;
	}

	public void setSamTodoListId(long samTodoListId) {
		_samTodoListId = samTodoListId;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public int getPriority() {
		return _priority;
	}

	public void setPriority(int priority) {
		_priority = priority;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _samTodoItemId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _samTodoListId;
	private String _description;
	private int _priority;
	private int _status;
}