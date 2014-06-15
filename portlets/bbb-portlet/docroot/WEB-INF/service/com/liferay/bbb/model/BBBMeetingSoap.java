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
 * This class is used by SOAP remote services, specifically {@link com.liferay.bbb.service.http.BBBMeetingServiceSoap}.
 *
 * @author Shinn Lok
 * @see com.liferay.bbb.service.http.BBBMeetingServiceSoap
 * @generated
 */
public class BBBMeetingSoap implements Serializable {
	public static BBBMeetingSoap toSoapModel(BBBMeeting model) {
		BBBMeetingSoap soapModel = new BBBMeetingSoap();

		soapModel.setBbbMeetingId(model.getBbbMeetingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBbbServerId(model.getBbbServerId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());
		soapModel.setAttendeePassword(model.getAttendeePassword());
		soapModel.setModeratorPassword(model.getModeratorPassword());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static BBBMeetingSoap[] toSoapModels(BBBMeeting[] models) {
		BBBMeetingSoap[] soapModels = new BBBMeetingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BBBMeetingSoap[][] toSoapModels(BBBMeeting[][] models) {
		BBBMeetingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BBBMeetingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BBBMeetingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BBBMeetingSoap[] toSoapModels(List<BBBMeeting> models) {
		List<BBBMeetingSoap> soapModels = new ArrayList<BBBMeetingSoap>(models.size());

		for (BBBMeeting model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BBBMeetingSoap[soapModels.size()]);
	}

	public BBBMeetingSoap() {
	}

	public long getPrimaryKey() {
		return _bbbMeetingId;
	}

	public void setPrimaryKey(long pk) {
		setBbbMeetingId(pk);
	}

	public long getBbbMeetingId() {
		return _bbbMeetingId;
	}

	public void setBbbMeetingId(long bbbMeetingId) {
		_bbbMeetingId = bbbMeetingId;
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

	public long getBbbServerId() {
		return _bbbServerId;
	}

	public void setBbbServerId(long bbbServerId) {
		_bbbServerId = bbbServerId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getAttendeePassword() {
		return _attendeePassword;
	}

	public void setAttendeePassword(String attendeePassword) {
		_attendeePassword = attendeePassword;
	}

	public String getModeratorPassword() {
		return _moderatorPassword;
	}

	public void setModeratorPassword(String moderatorPassword) {
		_moderatorPassword = moderatorPassword;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _bbbMeetingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _bbbServerId;
	private String _name;
	private String _description;
	private String _attendeePassword;
	private String _moderatorPassword;
	private int _status;
}