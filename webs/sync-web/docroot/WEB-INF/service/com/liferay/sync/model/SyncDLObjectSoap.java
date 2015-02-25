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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.sync.service.http.SyncDLObjectServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.sync.service.http.SyncDLObjectServiceSoap
 * @generated
 */
@ProviderType
public class SyncDLObjectSoap implements Serializable {
	public static SyncDLObjectSoap toSoapModel(SyncDLObject model) {
		SyncDLObjectSoap soapModel = new SyncDLObjectSoap();

		soapModel.setSyncDLObjectId(model.getSyncDLObjectId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateTime(model.getCreateTime());
		soapModel.setModifiedTime(model.getModifiedTime());
		soapModel.setRepositoryId(model.getRepositoryId());
		soapModel.setParentFolderId(model.getParentFolderId());
		soapModel.setName(model.getName());
		soapModel.setExtension(model.getExtension());
		soapModel.setMimeType(model.getMimeType());
		soapModel.setDescription(model.getDescription());
		soapModel.setChangeLog(model.getChangeLog());
		soapModel.setExtraSettings(model.getExtraSettings());
		soapModel.setVersion(model.getVersion());
		soapModel.setVersionId(model.getVersionId());
		soapModel.setSize(model.getSize());
		soapModel.setChecksum(model.getChecksum());
		soapModel.setEvent(model.getEvent());
		soapModel.setLockExpirationDate(model.getLockExpirationDate());
		soapModel.setLockUserId(model.getLockUserId());
		soapModel.setLockUserName(model.getLockUserName());
		soapModel.setType(model.getType());
		soapModel.setTypePK(model.getTypePK());
		soapModel.setTypeUuid(model.getTypeUuid());

		return soapModel;
	}

	public static SyncDLObjectSoap[] toSoapModels(SyncDLObject[] models) {
		SyncDLObjectSoap[] soapModels = new SyncDLObjectSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncDLObjectSoap[][] toSoapModels(SyncDLObject[][] models) {
		SyncDLObjectSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncDLObjectSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncDLObjectSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncDLObjectSoap[] toSoapModels(List<SyncDLObject> models) {
		List<SyncDLObjectSoap> soapModels = new ArrayList<SyncDLObjectSoap>(models.size());

		for (SyncDLObject model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncDLObjectSoap[soapModels.size()]);
	}

	public SyncDLObjectSoap() {
	}

	public long getPrimaryKey() {
		return _syncDLObjectId;
	}

	public void setPrimaryKey(long pk) {
		setSyncDLObjectId(pk);
	}

	public long getSyncDLObjectId() {
		return _syncDLObjectId;
	}

	public void setSyncDLObjectId(long syncDLObjectId) {
		_syncDLObjectId = syncDLObjectId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getCreateTime() {
		return _createTime;
	}

	public void setCreateTime(long createTime) {
		_createTime = createTime;
	}

	public long getModifiedTime() {
		return _modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		_modifiedTime = modifiedTime;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	public long getParentFolderId() {
		return _parentFolderId;
	}

	public void setParentFolderId(long parentFolderId) {
		_parentFolderId = parentFolderId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getExtension() {
		return _extension;
	}

	public void setExtension(String extension) {
		_extension = extension;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public void setMimeType(String mimeType) {
		_mimeType = mimeType;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getChangeLog() {
		return _changeLog;
	}

	public void setChangeLog(String changeLog) {
		_changeLog = changeLog;
	}

	public String getExtraSettings() {
		return _extraSettings;
	}

	public void setExtraSettings(String extraSettings) {
		_extraSettings = extraSettings;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public String getChecksum() {
		return _checksum;
	}

	public void setChecksum(String checksum) {
		_checksum = checksum;
	}

	public String getEvent() {
		return _event;
	}

	public void setEvent(String event) {
		_event = event;
	}

	public Date getLockExpirationDate() {
		return _lockExpirationDate;
	}

	public void setLockExpirationDate(Date lockExpirationDate) {
		_lockExpirationDate = lockExpirationDate;
	}

	public long getLockUserId() {
		return _lockUserId;
	}

	public void setLockUserId(long lockUserId) {
		_lockUserId = lockUserId;
	}

	public String getLockUserName() {
		return _lockUserName;
	}

	public void setLockUserName(String lockUserName) {
		_lockUserName = lockUserName;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public long getTypePK() {
		return _typePK;
	}

	public void setTypePK(long typePK) {
		_typePK = typePK;
	}

	public String getTypeUuid() {
		return _typeUuid;
	}

	public void setTypeUuid(String typeUuid) {
		_typeUuid = typeUuid;
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
}