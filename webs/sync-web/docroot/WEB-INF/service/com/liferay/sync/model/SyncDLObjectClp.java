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

package com.liferay.sync.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import com.liferay.sync.service.ClpSerializer;
import com.liferay.sync.service.SyncDLObjectLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class SyncDLObjectClp extends BaseModelImpl<SyncDLObject>
	implements SyncDLObject {
	public SyncDLObjectClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDLObject.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDLObject.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _syncDLObjectId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSyncDLObjectId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _syncDLObjectId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("syncDLObjectId", getSyncDLObjectId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createTime", getCreateTime());
		attributes.put("modifiedTime", getModifiedTime());
		attributes.put("repositoryId", getRepositoryId());
		attributes.put("parentFolderId", getParentFolderId());
		attributes.put("name", getName());
		attributes.put("extension", getExtension());
		attributes.put("mimeType", getMimeType());
		attributes.put("description", getDescription());
		attributes.put("changeLog", getChangeLog());
		attributes.put("extraSettings", getExtraSettings());
		attributes.put("version", getVersion());
		attributes.put("versionId", getVersionId());
		attributes.put("size", getSize());
		attributes.put("checksum", getChecksum());
		attributes.put("event", getEvent());
		attributes.put("lockExpirationDate", getLockExpirationDate());
		attributes.put("lockUserId", getLockUserId());
		attributes.put("lockUserName", getLockUserName());
		attributes.put("type", getType());
		attributes.put("typePK", getTypePK());
		attributes.put("typeUuid", getTypeUuid());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long syncDLObjectId = (Long)attributes.get("syncDLObjectId");

		if (syncDLObjectId != null) {
			setSyncDLObjectId(syncDLObjectId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long createTime = (Long)attributes.get("createTime");

		if (createTime != null) {
			setCreateTime(createTime);
		}

		Long modifiedTime = (Long)attributes.get("modifiedTime");

		if (modifiedTime != null) {
			setModifiedTime(modifiedTime);
		}

		Long repositoryId = (Long)attributes.get("repositoryId");

		if (repositoryId != null) {
			setRepositoryId(repositoryId);
		}

		Long parentFolderId = (Long)attributes.get("parentFolderId");

		if (parentFolderId != null) {
			setParentFolderId(parentFolderId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String extension = (String)attributes.get("extension");

		if (extension != null) {
			setExtension(extension);
		}

		String mimeType = (String)attributes.get("mimeType");

		if (mimeType != null) {
			setMimeType(mimeType);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String changeLog = (String)attributes.get("changeLog");

		if (changeLog != null) {
			setChangeLog(changeLog);
		}

		String extraSettings = (String)attributes.get("extraSettings");

		if (extraSettings != null) {
			setExtraSettings(extraSettings);
		}

		String version = (String)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		Long versionId = (Long)attributes.get("versionId");

		if (versionId != null) {
			setVersionId(versionId);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		String checksum = (String)attributes.get("checksum");

		if (checksum != null) {
			setChecksum(checksum);
		}

		String event = (String)attributes.get("event");

		if (event != null) {
			setEvent(event);
		}

		Date lockExpirationDate = (Date)attributes.get("lockExpirationDate");

		if (lockExpirationDate != null) {
			setLockExpirationDate(lockExpirationDate);
		}

		Long lockUserId = (Long)attributes.get("lockUserId");

		if (lockUserId != null) {
			setLockUserId(lockUserId);
		}

		String lockUserName = (String)attributes.get("lockUserName");

		if (lockUserName != null) {
			setLockUserName(lockUserName);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long typePK = (Long)attributes.get("typePK");

		if (typePK != null) {
			setTypePK(typePK);
		}

		String typeUuid = (String)attributes.get("typeUuid");

		if (typeUuid != null) {
			setTypeUuid(typeUuid);
		}

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getSyncDLObjectId() {
		return _syncDLObjectId;
	}

	@Override
	public void setSyncDLObjectId(long syncDLObjectId) {
		_syncDLObjectId = syncDLObjectId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setSyncDLObjectId", long.class);

				method.invoke(_syncDLObjectRemoteModel, syncDLObjectId);
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

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_syncDLObjectRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCreateTime() {
		return _createTime;
	}

	@Override
	public void setCreateTime(long createTime) {
		_createTime = createTime;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateTime", long.class);

				method.invoke(_syncDLObjectRemoteModel, createTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getModifiedTime() {
		return _modifiedTime;
	}

	@Override
	public void setModifiedTime(long modifiedTime) {
		_modifiedTime = modifiedTime;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedTime", long.class);

				method.invoke(_syncDLObjectRemoteModel, modifiedTime);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getRepositoryId() {
		return _repositoryId;
	}

	@Override
	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setRepositoryId", long.class);

				method.invoke(_syncDLObjectRemoteModel, repositoryId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getParentFolderId() {
		return _parentFolderId;
	}

	@Override
	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setParentFolderId", long.class);

				method.invoke(_syncDLObjectRemoteModel, parentFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public void setName(String name) {
		_name = name;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setName", String.class);

				method.invoke(_syncDLObjectRemoteModel, name);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExtension() {
		return _extension;
	}

	@Override
	public void setExtension(String extension) {
		_extension = extension;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setExtension", String.class);

				method.invoke(_syncDLObjectRemoteModel, extension);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getMimeType() {
		return _mimeType;
	}

	@Override
	public void setMimeType(String mimeType) {
		_mimeType = mimeType;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setMimeType", String.class);

				method.invoke(_syncDLObjectRemoteModel, mimeType);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getDescription() {
		return _description;
	}

	@Override
	public void setDescription(String description) {
		_description = description;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setDescription", String.class);

				method.invoke(_syncDLObjectRemoteModel, description);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getChangeLog() {
		return _changeLog;
	}

	@Override
	public void setChangeLog(String changeLog) {
		_changeLog = changeLog;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setChangeLog", String.class);

				method.invoke(_syncDLObjectRemoteModel, changeLog);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getExtraSettings() {
		return _extraSettings;
	}

	@Override
	public void setExtraSettings(String extraSettings) {
		_extraSettings = extraSettings;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setExtraSettings", String.class);

				method.invoke(_syncDLObjectRemoteModel, extraSettings);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getVersion() {
		return _version;
	}

	@Override
	public void setVersion(String version) {
		_version = version;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setVersion", String.class);

				method.invoke(_syncDLObjectRemoteModel, version);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getVersionId() {
		return _versionId;
	}

	@Override
	public void setVersionId(long versionId) {
		_versionId = versionId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setVersionId", long.class);

				method.invoke(_syncDLObjectRemoteModel, versionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSize() {
		return _size;
	}

	@Override
	public void setSize(long size) {
		_size = size;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setSize", long.class);

				method.invoke(_syncDLObjectRemoteModel, size);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getChecksum() {
		return _checksum;
	}

	@Override
	public void setChecksum(String checksum) {
		_checksum = checksum;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setChecksum", String.class);

				method.invoke(_syncDLObjectRemoteModel, checksum);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getEvent() {
		return _event;
	}

	@Override
	public void setEvent(String event) {
		_event = event;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setEvent", String.class);

				method.invoke(_syncDLObjectRemoteModel, event);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getLockExpirationDate() {
		return _lockExpirationDate;
	}

	@Override
	public void setLockExpirationDate(Date lockExpirationDate) {
		_lockExpirationDate = lockExpirationDate;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setLockExpirationDate",
						Date.class);

				method.invoke(_syncDLObjectRemoteModel, lockExpirationDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getLockUserId() {
		return _lockUserId;
	}

	@Override
	public void setLockUserId(long lockUserId) {
		_lockUserId = lockUserId;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setLockUserId", long.class);

				method.invoke(_syncDLObjectRemoteModel, lockUserId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLockUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getLockUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setLockUserUuid(String lockUserUuid) {
	}

	@Override
	public String getLockUserName() {
		return _lockUserName;
	}

	@Override
	public void setLockUserName(String lockUserName) {
		_lockUserName = lockUserName;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setLockUserName", String.class);

				method.invoke(_syncDLObjectRemoteModel, lockUserName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_syncDLObjectRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTypePK() {
		return _typePK;
	}

	@Override
	public void setTypePK(long typePK) {
		_typePK = typePK;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setTypePK", long.class);

				method.invoke(_syncDLObjectRemoteModel, typePK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getTypeUuid() {
		return _typeUuid;
	}

	@Override
	public void setTypeUuid(String typeUuid) {
		_typeUuid = typeUuid;

		if (_syncDLObjectRemoteModel != null) {
			try {
				Class<?> clazz = _syncDLObjectRemoteModel.getClass();

				Method method = clazz.getMethod("setTypeUuid", String.class);

				method.invoke(_syncDLObjectRemoteModel, typeUuid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setCreateDate(java.util.Date createDate) {
		try {
			String methodName = "setCreateDate";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Date.class };

			Object[] parameterValues = new Object[] { createDate };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		try {
			String methodName = "setModifiedDate";

			Class<?>[] parameterTypes = new Class<?>[] { java.util.Date.class };

			Object[] parameterValues = new Object[] { modifiedDate };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getSyncDLObjectRemoteModel() {
		return _syncDLObjectRemoteModel;
	}

	public void setSyncDLObjectRemoteModel(BaseModel<?> syncDLObjectRemoteModel) {
		_syncDLObjectRemoteModel = syncDLObjectRemoteModel;
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

		Class<?> remoteModelClass = _syncDLObjectRemoteModel.getClass();

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

		Object returnValue = method.invoke(_syncDLObjectRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			SyncDLObjectLocalServiceUtil.addSyncDLObject(this);
		}
		else {
			SyncDLObjectLocalServiceUtil.updateSyncDLObject(this);
		}
	}

	@Override
	public SyncDLObject toEscapedModel() {
		return (SyncDLObject)ProxyUtil.newProxyInstance(SyncDLObject.class.getClassLoader(),
			new Class[] { SyncDLObject.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		SyncDLObjectClp clone = new SyncDLObjectClp();

		clone.setSyncDLObjectId(getSyncDLObjectId());
		clone.setCompanyId(getCompanyId());
		clone.setCreateTime(getCreateTime());
		clone.setModifiedTime(getModifiedTime());
		clone.setRepositoryId(getRepositoryId());
		clone.setParentFolderId(getParentFolderId());
		clone.setName(getName());
		clone.setExtension(getExtension());
		clone.setMimeType(getMimeType());
		clone.setDescription(getDescription());
		clone.setChangeLog(getChangeLog());
		clone.setExtraSettings(getExtraSettings());
		clone.setVersion(getVersion());
		clone.setVersionId(getVersionId());
		clone.setSize(getSize());
		clone.setChecksum(getChecksum());
		clone.setEvent(getEvent());
		clone.setLockExpirationDate(getLockExpirationDate());
		clone.setLockUserId(getLockUserId());
		clone.setLockUserName(getLockUserName());
		clone.setType(getType());
		clone.setTypePK(getTypePK());
		clone.setTypeUuid(getTypeUuid());

		return clone;
	}

	@Override
	public int compareTo(SyncDLObject syncDLObject) {
		int value = 0;

		if (getCompanyId() < syncDLObject.getCompanyId()) {
			value = -1;
		}
		else if (getCompanyId() > syncDLObject.getCompanyId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getModifiedTime() < syncDLObject.getModifiedTime()) {
			value = -1;
		}
		else if (getModifiedTime() > syncDLObject.getModifiedTime()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		if (getRepositoryId() < syncDLObject.getRepositoryId()) {
			value = -1;
		}
		else if (getRepositoryId() > syncDLObject.getRepositoryId()) {
			value = 1;
		}
		else {
			value = 0;
		}

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

		if (!(obj instanceof SyncDLObjectClp)) {
			return false;
		}

		SyncDLObjectClp syncDLObject = (SyncDLObjectClp)obj;

		long primaryKey = syncDLObject.getPrimaryKey();

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
		StringBundler sb = new StringBundler(47);

		sb.append("{syncDLObjectId=");
		sb.append(getSyncDLObjectId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", createTime=");
		sb.append(getCreateTime());
		sb.append(", modifiedTime=");
		sb.append(getModifiedTime());
		sb.append(", repositoryId=");
		sb.append(getRepositoryId());
		sb.append(", parentFolderId=");
		sb.append(getParentFolderId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", extension=");
		sb.append(getExtension());
		sb.append(", mimeType=");
		sb.append(getMimeType());
		sb.append(", description=");
		sb.append(getDescription());
		sb.append(", changeLog=");
		sb.append(getChangeLog());
		sb.append(", extraSettings=");
		sb.append(getExtraSettings());
		sb.append(", version=");
		sb.append(getVersion());
		sb.append(", versionId=");
		sb.append(getVersionId());
		sb.append(", size=");
		sb.append(getSize());
		sb.append(", checksum=");
		sb.append(getChecksum());
		sb.append(", event=");
		sb.append(getEvent());
		sb.append(", lockExpirationDate=");
		sb.append(getLockExpirationDate());
		sb.append(", lockUserId=");
		sb.append(getLockUserId());
		sb.append(", lockUserName=");
		sb.append(getLockUserName());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", typePK=");
		sb.append(getTypePK());
		sb.append(", typeUuid=");
		sb.append(getTypeUuid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(73);

		sb.append("<model><model-name>");
		sb.append("com.liferay.sync.model.SyncDLObject");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>syncDLObjectId</column-name><column-value><![CDATA[");
		sb.append(getSyncDLObjectId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createTime</column-name><column-value><![CDATA[");
		sb.append(getCreateTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedTime</column-name><column-value><![CDATA[");
		sb.append(getModifiedTime());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>repositoryId</column-name><column-value><![CDATA[");
		sb.append(getRepositoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentFolderId</column-name><column-value><![CDATA[");
		sb.append(getParentFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>extension</column-name><column-value><![CDATA[");
		sb.append(getExtension());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mimeType</column-name><column-value><![CDATA[");
		sb.append(getMimeType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>description</column-name><column-value><![CDATA[");
		sb.append(getDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>changeLog</column-name><column-value><![CDATA[");
		sb.append(getChangeLog());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>extraSettings</column-name><column-value><![CDATA[");
		sb.append(getExtraSettings());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>version</column-name><column-value><![CDATA[");
		sb.append(getVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>versionId</column-name><column-value><![CDATA[");
		sb.append(getVersionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>size</column-name><column-value><![CDATA[");
		sb.append(getSize());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>checksum</column-name><column-value><![CDATA[");
		sb.append(getChecksum());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>event</column-name><column-value><![CDATA[");
		sb.append(getEvent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lockExpirationDate</column-name><column-value><![CDATA[");
		sb.append(getLockExpirationDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lockUserId</column-name><column-value><![CDATA[");
		sb.append(getLockUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lockUserName</column-name><column-value><![CDATA[");
		sb.append(getLockUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typePK</column-name><column-value><![CDATA[");
		sb.append(getTypePK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>typeUuid</column-name><column-value><![CDATA[");
		sb.append(getTypeUuid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _syncDLObjectId;
	private long _companyId;
	private long _createTime;
	private long _modifiedTime;
	private long _repositoryId;
	private long _parentFolderId;
	private String _name;
	private String _extension;
	private String _mimeType;
	private String _description;
	private String _changeLog;
	private String _extraSettings;
	private String _version;
	private long _versionId;
	private long _size;
	private String _checksum;
	private String _event;
	private Date _lockExpirationDate;
	private long _lockUserId;
	private String _lockUserName;
	private String _type;
	private long _typePK;
	private String _typeUuid;
	private BaseModel<?> _syncDLObjectRemoteModel;
	private Class<?> _clpSerializerClass = com.liferay.sync.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}