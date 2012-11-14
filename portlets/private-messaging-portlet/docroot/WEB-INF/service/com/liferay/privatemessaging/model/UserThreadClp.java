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

package com.liferay.privatemessaging.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.privatemessaging.service.UserThreadLocalServiceUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class UserThreadClp extends BaseModelImpl<UserThread>
	implements UserThread {
	public UserThreadClp() {
	}

	public Class<?> getModelClass() {
		return UserThread.class;
	}

	public String getModelClassName() {
		return UserThread.class.getName();
	}

	public long getPrimaryKey() {
		return _userThreadId;
	}

	public void setPrimaryKey(long primaryKey) {
		setUserThreadId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_userThreadId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("userThreadId", getUserThreadId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("mbThreadId", getMbThreadId());
		attributes.put("topMBMessageId", getTopMBMessageId());
		attributes.put("read", getRead());
		attributes.put("deleted", getDeleted());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long userThreadId = (Long)attributes.get("userThreadId");

		if (userThreadId != null) {
			setUserThreadId(userThreadId);
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

		Long mbThreadId = (Long)attributes.get("mbThreadId");

		if (mbThreadId != null) {
			setMbThreadId(mbThreadId);
		}

		Long topMBMessageId = (Long)attributes.get("topMBMessageId");

		if (topMBMessageId != null) {
			setTopMBMessageId(topMBMessageId);
		}

		Boolean read = (Boolean)attributes.get("read");

		if (read != null) {
			setRead(read);
		}

		Boolean deleted = (Boolean)attributes.get("deleted");

		if (deleted != null) {
			setDeleted(deleted);
		}
	}

	public long getUserThreadId() {
		return _userThreadId;
	}

	public void setUserThreadId(long userThreadId) {
		_userThreadId = userThreadId;
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

	public long getMbThreadId() {
		return _mbThreadId;
	}

	public void setMbThreadId(long mbThreadId) {
		_mbThreadId = mbThreadId;
	}

	public long getTopMBMessageId() {
		return _topMBMessageId;
	}

	public void setTopMBMessageId(long topMBMessageId) {
		_topMBMessageId = topMBMessageId;
	}

	public boolean getRead() {
		return _read;
	}

	public boolean isRead() {
		return _read;
	}

	public void setRead(boolean read) {
		_read = read;
	}

	public boolean getDeleted() {
		return _deleted;
	}

	public boolean isDeleted() {
		return _deleted;
	}

	public void setDeleted(boolean deleted) {
		_deleted = deleted;
	}

	public BaseModel<?> getUserThreadRemoteModel() {
		return _userThreadRemoteModel;
	}

	public void setUserThreadRemoteModel(BaseModel<?> userThreadRemoteModel) {
		_userThreadRemoteModel = userThreadRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			UserThreadLocalServiceUtil.addUserThread(this);
		}
		else {
			UserThreadLocalServiceUtil.updateUserThread(this);
		}
	}

	@Override
	public UserThread toEscapedModel() {
		return (UserThread)ProxyUtil.newProxyInstance(UserThread.class.getClassLoader(),
			new Class[] { UserThread.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		UserThreadClp clone = new UserThreadClp();

		clone.setUserThreadId(getUserThreadId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setMbThreadId(getMbThreadId());
		clone.setTopMBMessageId(getTopMBMessageId());
		clone.setRead(getRead());
		clone.setDeleted(getDeleted());

		return clone;
	}

	public int compareTo(UserThread userThread) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				userThread.getModifiedDate());

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

		UserThreadClp userThread = null;

		try {
			userThread = (UserThreadClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = userThread.getPrimaryKey();

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
		StringBundler sb = new StringBundler(21);

		sb.append("{userThreadId=");
		sb.append(getUserThreadId());
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
		sb.append(", mbThreadId=");
		sb.append(getMbThreadId());
		sb.append(", topMBMessageId=");
		sb.append(getTopMBMessageId());
		sb.append(", read=");
		sb.append(getRead());
		sb.append(", deleted=");
		sb.append(getDeleted());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(34);

		sb.append("<model><model-name>");
		sb.append("com.liferay.privatemessaging.model.UserThread");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>userThreadId</column-name><column-value><![CDATA[");
		sb.append(getUserThreadId());
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
			"<column><column-name>mbThreadId</column-name><column-value><![CDATA[");
		sb.append(getMbThreadId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>topMBMessageId</column-name><column-value><![CDATA[");
		sb.append(getTopMBMessageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>read</column-name><column-value><![CDATA[");
		sb.append(getRead());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>deleted</column-name><column-value><![CDATA[");
		sb.append(getDeleted());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _userThreadId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _mbThreadId;
	private long _topMBMessageId;
	private boolean _read;
	private boolean _deleted;
	private BaseModel<?> _userThreadRemoteModel;
}