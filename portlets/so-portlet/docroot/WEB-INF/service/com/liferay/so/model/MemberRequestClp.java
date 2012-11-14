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

package com.liferay.so.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.so.service.MemberRequestLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class MemberRequestClp extends BaseModelImpl<MemberRequest>
	implements MemberRequest {
	public MemberRequestClp() {
	}

	public Class<?> getModelClass() {
		return MemberRequest.class;
	}

	public String getModelClassName() {
		return MemberRequest.class.getName();
	}

	public long getPrimaryKey() {
		return _memberRequestId;
	}

	public void setPrimaryKey(long primaryKey) {
		setMemberRequestId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_memberRequestId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("memberRequestId", getMemberRequestId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("key", getKey());
		attributes.put("receiverUserId", getReceiverUserId());
		attributes.put("invitedRoleId", getInvitedRoleId());
		attributes.put("invitedTeamId", getInvitedTeamId());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long memberRequestId = (Long)attributes.get("memberRequestId");

		if (memberRequestId != null) {
			setMemberRequestId(memberRequestId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String key = (String)attributes.get("key");

		if (key != null) {
			setKey(key);
		}

		Long receiverUserId = (Long)attributes.get("receiverUserId");

		if (receiverUserId != null) {
			setReceiverUserId(receiverUserId);
		}

		Long invitedRoleId = (Long)attributes.get("invitedRoleId");

		if (invitedRoleId != null) {
			setInvitedRoleId(invitedRoleId);
		}

		Long invitedTeamId = (Long)attributes.get("invitedTeamId");

		if (invitedTeamId != null) {
			setInvitedTeamId(invitedTeamId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public String getReceiverUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getReceiverUserId(), "uuid",
			_receiverUserUuid);
	}

	public void setReceiverUserUuid(String receiverUserUuid) {
		_receiverUserUuid = receiverUserUuid;
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

	public BaseModel<?> getMemberRequestRemoteModel() {
		return _memberRequestRemoteModel;
	}

	public void setMemberRequestRemoteModel(
		BaseModel<?> memberRequestRemoteModel) {
		_memberRequestRemoteModel = memberRequestRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			MemberRequestLocalServiceUtil.addMemberRequest(this);
		}
		else {
			MemberRequestLocalServiceUtil.updateMemberRequest(this);
		}
	}

	@Override
	public MemberRequest toEscapedModel() {
		return (MemberRequest)ProxyUtil.newProxyInstance(MemberRequest.class.getClassLoader(),
			new Class[] { MemberRequest.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MemberRequestClp clone = new MemberRequestClp();

		clone.setMemberRequestId(getMemberRequestId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKey(getKey());
		clone.setReceiverUserId(getReceiverUserId());
		clone.setInvitedRoleId(getInvitedRoleId());
		clone.setInvitedTeamId(getInvitedTeamId());
		clone.setStatus(getStatus());

		return clone;
	}

	public int compareTo(MemberRequest memberRequest) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				memberRequest.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		MemberRequestClp memberRequest = null;

		try {
			memberRequest = (MemberRequestClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = memberRequest.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{memberRequestId=");
		sb.append(getMemberRequestId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", key=");
		sb.append(getKey());
		sb.append(", receiverUserId=");
		sb.append(getReceiverUserId());
		sb.append(", invitedRoleId=");
		sb.append(getInvitedRoleId());
		sb.append(", invitedTeamId=");
		sb.append(getInvitedTeamId());
		sb.append(", status=");
		sb.append(getStatus());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.so.model.MemberRequest");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>memberRequestId</column-name><column-value><![CDATA[");
		sb.append(getMemberRequestId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>key</column-name><column-value><![CDATA[");
		sb.append(getKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>receiverUserId</column-name><column-value><![CDATA[");
		sb.append(getReceiverUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>invitedRoleId</column-name><column-value><![CDATA[");
		sb.append(getInvitedRoleId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>invitedTeamId</column-name><column-value><![CDATA[");
		sb.append(getInvitedTeamId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>status</column-name><column-value><![CDATA[");
		sb.append(getStatus());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _memberRequestId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _key;
	private long _receiverUserId;
	private String _receiverUserUuid;
	private long _invitedRoleId;
	private long _invitedTeamId;
	private int _status;
	private BaseModel<?> _memberRequestRemoteModel;
}