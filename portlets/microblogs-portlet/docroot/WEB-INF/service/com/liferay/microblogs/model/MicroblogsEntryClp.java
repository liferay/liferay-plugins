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

package com.liferay.microblogs.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.microblogs.service.ClpSerializer;
import com.liferay.microblogs.service.MicroblogsEntryLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class MicroblogsEntryClp extends BaseModelImpl<MicroblogsEntry>
	implements MicroblogsEntry {
	public MicroblogsEntryClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return MicroblogsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return MicroblogsEntry.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _microblogsEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setMicroblogsEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _microblogsEntryId;
	}

	@Override
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
		attributes.put("creatorClassNameId", getCreatorClassNameId());
		attributes.put("creatorClassPK", getCreatorClassPK());
		attributes.put("content", getContent());
		attributes.put("type", getType());
		attributes.put("receiverUserId", getReceiverUserId());
		attributes.put("receiverMicroblogsEntryId",
			getReceiverMicroblogsEntryId());
		attributes.put("socialRelationType", getSocialRelationType());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		Long creatorClassNameId = (Long)attributes.get("creatorClassNameId");

		if (creatorClassNameId != null) {
			setCreatorClassNameId(creatorClassNameId);
		}

		Long creatorClassPK = (Long)attributes.get("creatorClassPK");

		if (creatorClassPK != null) {
			setCreatorClassPK(creatorClassPK);
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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getMicroblogsEntryId() {
		return _microblogsEntryId;
	}

	@Override
	public void setMicroblogsEntryId(long microblogsEntryId) {
		_microblogsEntryId = microblogsEntryId;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setMicroblogsEntryId",
						long.class);

				method.invoke(_microblogsEntryRemoteModel, microblogsEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_microblogsEntryRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_microblogsEntryRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_microblogsEntryRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_microblogsEntryRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_microblogsEntryRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCreatorClassNameId() {
		return _creatorClassNameId;
	}

	@Override
	public void setCreatorClassNameId(long creatorClassNameId) {
		_creatorClassNameId = creatorClassNameId;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreatorClassNameId",
						long.class);

				method.invoke(_microblogsEntryRemoteModel, creatorClassNameId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCreatorClassPK() {
		return _creatorClassPK;
	}

	@Override
	public void setCreatorClassPK(long creatorClassPK) {
		_creatorClassPK = creatorClassPK;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setCreatorClassPK", long.class);

				method.invoke(_microblogsEntryRemoteModel, creatorClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getContent() {
		return _content;
	}

	@Override
	public void setContent(String content) {
		_content = content;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setContent", String.class);

				method.invoke(_microblogsEntryRemoteModel, content);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_type = type;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setType", int.class);

				method.invoke(_microblogsEntryRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getReceiverUserId() {
		return _receiverUserId;
	}

	@Override
	public void setReceiverUserId(long receiverUserId) {
		_receiverUserId = receiverUserId;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setReceiverUserId", long.class);

				method.invoke(_microblogsEntryRemoteModel, receiverUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getReceiverUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getReceiverUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setReceiverUserUuid(String receiverUserUuid) {
	}

	@Override
	public long getReceiverMicroblogsEntryId() {
		return _receiverMicroblogsEntryId;
	}

	@Override
	public void setReceiverMicroblogsEntryId(long receiverMicroblogsEntryId) {
		_receiverMicroblogsEntryId = receiverMicroblogsEntryId;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setReceiverMicroblogsEntryId",
						long.class);

				method.invoke(_microblogsEntryRemoteModel,
					receiverMicroblogsEntryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getSocialRelationType() {
		return _socialRelationType;
	}

	@Override
	public void setSocialRelationType(int socialRelationType) {
		_socialRelationType = socialRelationType;

		if (_microblogsEntryRemoteModel != null) {
			try {
				Class<?> clazz = _microblogsEntryRemoteModel.getClass();

				Method method = clazz.getMethod("setSocialRelationType",
						int.class);

				method.invoke(_microblogsEntryRemoteModel, socialRelationType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getMicroblogsEntryRemoteModel() {
		return _microblogsEntryRemoteModel;
	}

	public void setMicroblogsEntryRemoteModel(
		BaseModel<?> microblogsEntryRemoteModel) {
		_microblogsEntryRemoteModel = microblogsEntryRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _microblogsEntryRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_microblogsEntryRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
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
		clone.setCreatorClassNameId(getCreatorClassNameId());
		clone.setCreatorClassPK(getCreatorClassPK());
		clone.setContent(getContent());
		clone.setType(getType());
		clone.setReceiverUserId(getReceiverUserId());
		clone.setReceiverMicroblogsEntryId(getReceiverMicroblogsEntryId());
		clone.setSocialRelationType(getSocialRelationType());

		return clone;
	}

	@Override
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
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MicroblogsEntryClp)) {
			return false;
		}

		MicroblogsEntryClp microblogsEntry = (MicroblogsEntryClp)obj;

		long primaryKey = microblogsEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

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
		sb.append(", creatorClassNameId=");
		sb.append(getCreatorClassNameId());
		sb.append(", creatorClassPK=");
		sb.append(getCreatorClassPK());
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

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(43);

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
			"<column><column-name>creatorClassNameId</column-name><column-value><![CDATA[");
		sb.append(getCreatorClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>creatorClassPK</column-name><column-value><![CDATA[");
		sb.append(getCreatorClassPK());
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
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _creatorClassNameId;
	private long _creatorClassPK;
	private String _content;
	private int _type;
	private long _receiverUserId;
	private long _receiverMicroblogsEntryId;
	private int _socialRelationType;
	private BaseModel<?> _microblogsEntryRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.microblogs.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}