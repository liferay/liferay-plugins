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
public class MemberRequestSoap implements Serializable {
	public static MemberRequestSoap toSoapModel(MemberRequest model) {
		MemberRequestSoap soapModel = new MemberRequestSoap();

		soapModel.setMemberRequestId(model.getMemberRequestId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKey(model.getKey());
		soapModel.setReceiverUserId(model.getReceiverUserId());
		soapModel.setInvitedRoleId(model.getInvitedRoleId());
		soapModel.setInvitedTeamId(model.getInvitedTeamId());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static MemberRequestSoap[] toSoapModels(MemberRequest[] models) {
		MemberRequestSoap[] soapModels = new MemberRequestSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MemberRequestSoap[][] toSoapModels(MemberRequest[][] models) {
		MemberRequestSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MemberRequestSoap[models.length][models[0].length];
		}
		else {
			soapModels = new MemberRequestSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MemberRequestSoap[] toSoapModels(List<MemberRequest> models) {
		List<MemberRequestSoap> soapModels = new ArrayList<MemberRequestSoap>(models.size());

		for (MemberRequest model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MemberRequestSoap[soapModels.size()]);
	}

	public MemberRequestSoap() {
	}

	public long getPrimaryKey() {
		return _memberRequestId;
	}

	public void setPrimaryKey(long pk) {
		setMemberRequestId(pk);
	}

	public long getMemberRequestId() {
		return _memberRequestId;
	}

	public void setMemberRequestId(long memberRequestId) {
		_memberRequestId = memberRequestId;
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

	public String getKey() {
		return _key;
	}

	public void setKey(String key) {
		_key = key;
	}

	public long getReceiverUserId() {
		return _receiverUserId;
	}

	public void setReceiverUserId(long receiverUserId) {
		_receiverUserId = receiverUserId;
	}

	public long getInvitedRoleId() {
		return _invitedRoleId;
	}

	public void setInvitedRoleId(long invitedRoleId) {
		_invitedRoleId = invitedRoleId;
	}

	public long getInvitedTeamId() {
		return _invitedTeamId;
	}

	public void setInvitedTeamId(long invitedTeamId) {
		_invitedTeamId = invitedTeamId;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _memberRequestId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _key;
	private long _receiverUserId;
	private long _invitedRoleId;
	private long _invitedTeamId;
	private int _status;
}