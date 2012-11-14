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

package com.liferay.microblogs.model;

import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class MicroblogsEntryClp extends BaseModelImpl<MicroblogsEntry>
	implements MicroblogsEntry {
	public MicroblogsEntryClp() {
	}

	public Class<?> getModelClass() {
		return MicroblogsEntry.class;
	}

	public String getModelClassName() {
		return MicroblogsEntry.class.getName();
	}

	public long getPrimaryKey() {
		return _microblogsEntryId;
	}

	public void setPrimaryKey(long primaryKey) {
		setMicroblogsEntryId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_microblogsEntryId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("microblogsEntryId", getMicroblogsEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("content", getContent());
		attributes.put("type", getType());
		attributes.put("receiverUserId", getReceiverUserId());
		attributes.put("receiverMicroblogsEntryId",
			getReceiverMicroblogsEntryId());
		attributes.put("socialRelationType", getSocialRelationType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long microblogsEntryId = (Long)attributes.get("microblogsEntryId");

		if (microblogsEntryId != null) {
			setMicroblogsEntryId(microblogsEntryId);
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

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long receiverUserId = (Long)attributes.get("receiverUserId");

		if (receiverUserId != null) {
			setReceiverUserId(receiverUserId);
		}

		Long receiverMicroblogsEntryId = (Long)attributes.get(
				"receiverMicroblogsEntryId");

		if (receiverMicroblogsEntryId != null) {
			setReceiverMicroblogsEntryId(receiverMicroblogsEntryId);
		}

		Integer socialRelationType = (Integer)attributes.get(
				"socialRelationType");

		if (socialRelationType != null) {
			setSocialRelationType(socialRelationType);
		}
	}

	public long getMicroblogsEntryId() {
		return _microblogsEntryId;
	}

	public void setMicroblogsEntryId(long microblogsEntryId) {
		_microblogsEntryId = microblogsEntryId;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
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

	public long getReceiverMicroblogsEntryId() {
		return _receiverMicroblogsEntryId;
	}

	public void setReceiverMicroblogsEntryId(long receiverMicroblogsEntryId) {
		_receiverMicroblogsEntryId = receiverMicroblogsEntryId;
	}

	public int getSocialRelationType() {
		return _socialRelationType;
	}

	public void setSocialRelationType(int socialRelationType) {
		_socialRelationType = socialRelationType;
	}

	public BaseModel<?> getMicroblogsEntryRemoteModel() {
		return _microblogsEntryRemoteModel;
	}

	public void setMicroblogsEntryRemoteModel(
		BaseModel<?> microblogsEntryRemoteModel) {
		_microblogsEntryRemoteModel = microblogsEntryRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			MicroblogsEntryLocalServiceUtil.addMicroblogsEntry(this);
		}
		else {
			MicroblogsEntryLocalServiceUtil.updateMicroblogsEntry(this);
		}
	}

	@Override
	public MicroblogsEntry toEscapedModel() {
		return (MicroblogsEntry)ProxyUtil.newProxyInstance(MicroblogsEntry.class.getClassLoader(),
			new Class[] { MicroblogsEntry.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		MicroblogsEntryClp clone = new MicroblogsEntryClp();

		clone.setMicroblogsEntryId(getMicroblogsEntryId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setContent(getContent());
		clone.setType(getType());
		clone.setReceiverUserId(getReceiverUserId());
		clone.setReceiverMicroblogsEntryId(getReceiverMicroblogsEntryId());
		clone.setSocialRelationType(getSocialRelationType());

		return clone;
	}

	public int compareTo(MicroblogsEntry microblogsEntry) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(),
				microblogsEntry.getCreateDate());

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

		MicroblogsEntryClp microblogsEntry = null;

		try {
			microblogsEntry = (MicroblogsEntryClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = microblogsEntry.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{microblogsEntryId=");
		sb.append(getMicroblogsEntryId());
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
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", receiverUserId=");
		sb.append(getReceiverUserId());
		sb.append(", receiverMicroblogsEntryId=");
		sb.append(getReceiverMicroblogsEntryId());
		sb.append(", socialRelationType=");
		sb.append(getSocialRelationType());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.liferay.microblogs.model.MicroblogsEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>microblogsEntryId</column-name><column-value><![CDATA[");
		sb.append(getMicroblogsEntryId());
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
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>receiverUserId</column-name><column-value><![CDATA[");
		sb.append(getReceiverUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>receiverMicroblogsEntryId</column-name><column-value><![CDATA[");
		sb.append(getReceiverMicroblogsEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>socialRelationType</column-name><column-value><![CDATA[");
		sb.append(getSocialRelationType());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _microblogsEntryId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _content;
	private int _type;
	private long _receiverUserId;
	private String _receiverUserUuid;
	private long _receiverMicroblogsEntryId;
	private int _socialRelationType;
	private BaseModel<?> _microblogsEntryRemoteModel;
}