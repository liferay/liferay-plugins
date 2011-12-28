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

package com.liferay.socialnetworking.model;

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
public class MeetupsRegistrationSoap implements Serializable {
	public static MeetupsRegistrationSoap toSoapModel(MeetupsRegistration model) {
		MeetupsRegistrationSoap soapModel = new MeetupsRegistrationSoap();

		soapModel.setMeetupsRegistrationId(model.getMeetupsRegistrationId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setMeetupsEntryId(model.getMeetupsEntryId());
		soapModel.setStatus(model.getStatus());
		soapModel.setComments(model.getComments());

		return soapModel;
	}

	public static MeetupsRegistrationSoap[] toSoapModels(
		MeetupsRegistration[] models) {
		MeetupsRegistrationSoap[] soapModels = new MeetupsRegistrationSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MeetupsRegistrationSoap[][] toSoapModels(
		MeetupsRegistration[][] models) {
		MeetupsRegistrationSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MeetupsRegistrationSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MeetupsRegistrationSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MeetupsRegistrationSoap[] toSoapModels(
		List<MeetupsRegistration> models) {
		List<MeetupsRegistrationSoap> soapModels = new ArrayList<MeetupsRegistrationSoap>(models.size());

		for (MeetupsRegistration model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MeetupsRegistrationSoap[soapModels.size()]);
	}

	public MeetupsRegistrationSoap() {
	}

	public long getPrimaryKey() {
		return _meetupsRegistrationId;
	}

	public void setPrimaryKey(long pk) {
		setMeetupsRegistrationId(pk);
	}

	public long getMeetupsRegistrationId() {
		return _meetupsRegistrationId;
	}

	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		_meetupsRegistrationId = meetupsRegistrationId;
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

	public long getMeetupsEntryId() {
		return _meetupsEntryId;
	}

	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntryId = meetupsEntryId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public String getComments() {
		return _comments;
	}

	public void setComments(String comments) {
		_comments = comments;
	}

	private long _meetupsRegistrationId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _meetupsEntryId;
	private int _status;
	private String _comments;
}