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
public class SAMTodoListSoap implements Serializable {
	public static SAMTodoListSoap toSoapModel(SAMTodoList model) {
		SAMTodoListSoap soapModel = new SAMTodoListSoap();

		soapModel.setSamTodoListId(model.getSamTodoListId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static SAMTodoListSoap[] toSoapModels(SAMTodoList[] models) {
		SAMTodoListSoap[] soapModels = new SAMTodoListSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SAMTodoListSoap[][] toSoapModels(SAMTodoList[][] models) {
		SAMTodoListSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SAMTodoListSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SAMTodoListSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SAMTodoListSoap[] toSoapModels(List<SAMTodoList> models) {
		List<SAMTodoListSoap> soapModels = new ArrayList<SAMTodoListSoap>(models.size());

		for (SAMTodoList model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SAMTodoListSoap[soapModels.size()]);
	}

	public SAMTodoListSoap() {
	}

	public long getPrimaryKey() {
		return _samTodoListId;
	}

	public void setPrimaryKey(long pk) {
		setSamTodoListId(pk);
	}

	public long getSamTodoListId() {
		return _samTodoListId;
	}

	public void setSamTodoListId(long samTodoListId) {
		_samTodoListId = samTodoListId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _samTodoListId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
}