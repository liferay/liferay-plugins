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

package com.liferay.bbb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.bbb.service.http.BBBParticipantServiceSoap}.
 *
 * @author Shinn Lok
 * @see com.liferay.bbb.service.http.BBBParticipantServiceSoap
 * @generated
 */
public class BBBParticipantSoap implements Serializable {
	public static BBBParticipantSoap toSoapModel(BBBParticipant model) {
		BBBParticipantSoap soapModel = new BBBParticipantSoap();

		soapModel.setBbbParticipantId(model.getBbbParticipantId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBbbMeetingId(model.getBbbMeetingId());
		soapModel.setName(model.getName());
		soapModel.setEmailAddress(model.getEmailAddress());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static BBBParticipantSoap[] toSoapModels(BBBParticipant[] models) {
		BBBParticipantSoap[] soapModels = new BBBParticipantSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BBBParticipantSoap[][] toSoapModels(BBBParticipant[][] models) {
		BBBParticipantSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BBBParticipantSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BBBParticipantSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BBBParticipantSoap[] toSoapModels(List<BBBParticipant> models) {
		List<BBBParticipantSoap> soapModels = new ArrayList<BBBParticipantSoap>(models.size());

		for (BBBParticipant model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BBBParticipantSoap[soapModels.size()]);
	}

	public BBBParticipantSoap() {
	}

	public long getPrimaryKey() {
		return _bbbParticipantId;
	}

	public void setPrimaryKey(long pk) {
		setBbbParticipantId(pk);
	}

	public long getBbbParticipantId() {
		return _bbbParticipantId;
	}

	public void setBbbParticipantId(long bbbParticipantId) {
		_bbbParticipantId = bbbParticipantId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getBbbMeetingId() {
		return _bbbMeetingId;
	}

	public void setBbbMeetingId(long bbbMeetingId) {
		_bbbMeetingId = bbbMeetingId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmailAddress() {
		return _emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _bbbParticipantId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _bbbMeetingId;
	private String _name;
	private String _emailAddress;
	private int _type;
	private int _status;
}