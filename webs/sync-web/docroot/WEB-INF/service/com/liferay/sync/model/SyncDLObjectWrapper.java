/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SyncDLObject}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLObject
 * @generated
 */
public class SyncDLObjectWrapper implements SyncDLObject,
	ModelWrapper<SyncDLObject> {
	public SyncDLObjectWrapper(SyncDLObject syncDLObject) {
		_syncDLObject = syncDLObject;
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
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("objectId", getObjectId());
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
		attributes.put("size", getSize());
		attributes.put("checksum", getChecksum());
		attributes.put("event", getEvent());
		attributes.put("lockExpirationDate", getLockExpirationDate());
		attributes.put("lockUserId", getLockUserId());
		attributes.put("lockUserName", getLockUserName());
		attributes.put("type", getType());
		attributes.put("typePK", getTypePK());
		attributes.put("typeUuid", getTypeUuid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long objectId = (Long)attributes.get("objectId");

		if (objectId != null) {
			setObjectId(objectId);
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
	}

	/**
	* Returns the primary key of this sync d l object.
	*
	* @return the primary key of this sync d l object
	*/
	@Override
	public long getPrimaryKey() {
		return _syncDLObject.getPrimaryKey();
	}

	/**
	* Sets the primary key of this sync d l object.
	*
	* @param primaryKey the primary key of this sync d l object
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncDLObject.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the object ID of this sync d l object.
	*
	* @return the object ID of this sync d l object
	*/
	@Override
	public long getObjectId() {
		return _syncDLObject.getObjectId();
	}

	/**
	* Sets the object ID of this sync d l object.
	*
	* @param objectId the object ID of this sync d l object
	*/
	@Override
	public void setObjectId(long objectId) {
		_syncDLObject.setObjectId(objectId);
	}

	/**
	* Returns the company ID of this sync d l object.
	*
	* @return the company ID of this sync d l object
	*/
	@Override
	public long getCompanyId() {
		return _syncDLObject.getCompanyId();
	}

	/**
	* Sets the company ID of this sync d l object.
	*
	* @param companyId the company ID of this sync d l object
	*/
	@Override
	public void setCompanyId(long companyId) {
		_syncDLObject.setCompanyId(companyId);
	}

	/**
	* Returns the create time of this sync d l object.
	*
	* @return the create time of this sync d l object
	*/
	@Override
	public long getCreateTime() {
		return _syncDLObject.getCreateTime();
	}

	/**
	* Sets the create time of this sync d l object.
	*
	* @param createTime the create time of this sync d l object
	*/
	@Override
	public void setCreateTime(long createTime) {
		_syncDLObject.setCreateTime(createTime);
	}

	/**
	* Returns the modified time of this sync d l object.
	*
	* @return the modified time of this sync d l object
	*/
	@Override
	public long getModifiedTime() {
		return _syncDLObject.getModifiedTime();
	}

	/**
	* Sets the modified time of this sync d l object.
	*
	* @param modifiedTime the modified time of this sync d l object
	*/
	@Override
	public void setModifiedTime(long modifiedTime) {
		_syncDLObject.setModifiedTime(modifiedTime);
	}

	/**
	* Returns the repository ID of this sync d l object.
	*
	* @return the repository ID of this sync d l object
	*/
	@Override
	public long getRepositoryId() {
		return _syncDLObject.getRepositoryId();
	}

	/**
	* Sets the repository ID of this sync d l object.
	*
	* @param repositoryId the repository ID of this sync d l object
	*/
	@Override
	public void setRepositoryId(long repositoryId) {
		_syncDLObject.setRepositoryId(repositoryId);
	}

	/**
	* Returns the parent folder ID of this sync d l object.
	*
	* @return the parent folder ID of this sync d l object
	*/
	@Override
	public long getParentFolderId() {
		return _syncDLObject.getParentFolderId();
	}

	/**
	* Sets the parent folder ID of this sync d l object.
	*
	* @param parentFolderId the parent folder ID of this sync d l object
	*/
	@Override
	public void setParentFolderId(long parentFolderId) {
		_syncDLObject.setParentFolderId(parentFolderId);
	}

	/**
	* Returns the name of this sync d l object.
	*
	* @return the name of this sync d l object
	*/
	@Override
	public java.lang.String getName() {
		return _syncDLObject.getName();
	}

	/**
	* Sets the name of this sync d l object.
	*
	* @param name the name of this sync d l object
	*/
	@Override
	public void setName(java.lang.String name) {
		_syncDLObject.setName(name);
	}

	/**
	* Returns the extension of this sync d l object.
	*
	* @return the extension of this sync d l object
	*/
	@Override
	public java.lang.String getExtension() {
		return _syncDLObject.getExtension();
	}

	/**
	* Sets the extension of this sync d l object.
	*
	* @param extension the extension of this sync d l object
	*/
	@Override
	public void setExtension(java.lang.String extension) {
		_syncDLObject.setExtension(extension);
	}

	/**
	* Returns the mime type of this sync d l object.
	*
	* @return the mime type of this sync d l object
	*/
	@Override
	public java.lang.String getMimeType() {
		return _syncDLObject.getMimeType();
	}

	/**
	* Sets the mime type of this sync d l object.
	*
	* @param mimeType the mime type of this sync d l object
	*/
	@Override
	public void setMimeType(java.lang.String mimeType) {
		_syncDLObject.setMimeType(mimeType);
	}

	/**
	* Returns the description of this sync d l object.
	*
	* @return the description of this sync d l object
	*/
	@Override
	public java.lang.String getDescription() {
		return _syncDLObject.getDescription();
	}

	/**
	* Sets the description of this sync d l object.
	*
	* @param description the description of this sync d l object
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_syncDLObject.setDescription(description);
	}

	/**
	* Returns the change log of this sync d l object.
	*
	* @return the change log of this sync d l object
	*/
	@Override
	public java.lang.String getChangeLog() {
		return _syncDLObject.getChangeLog();
	}

	/**
	* Sets the change log of this sync d l object.
	*
	* @param changeLog the change log of this sync d l object
	*/
	@Override
	public void setChangeLog(java.lang.String changeLog) {
		_syncDLObject.setChangeLog(changeLog);
	}

	/**
	* Returns the extra settings of this sync d l object.
	*
	* @return the extra settings of this sync d l object
	*/
	@Override
	public java.lang.String getExtraSettings() {
		return _syncDLObject.getExtraSettings();
	}

	/**
	* Sets the extra settings of this sync d l object.
	*
	* @param extraSettings the extra settings of this sync d l object
	*/
	@Override
	public void setExtraSettings(java.lang.String extraSettings) {
		_syncDLObject.setExtraSettings(extraSettings);
	}

	/**
	* Returns the version of this sync d l object.
	*
	* @return the version of this sync d l object
	*/
	@Override
	public java.lang.String getVersion() {
		return _syncDLObject.getVersion();
	}

	/**
	* Sets the version of this sync d l object.
	*
	* @param version the version of this sync d l object
	*/
	@Override
	public void setVersion(java.lang.String version) {
		_syncDLObject.setVersion(version);
	}

	/**
	* Returns the size of this sync d l object.
	*
	* @return the size of this sync d l object
	*/
	@Override
	public long getSize() {
		return _syncDLObject.getSize();
	}

	/**
	* Sets the size of this sync d l object.
	*
	* @param size the size of this sync d l object
	*/
	@Override
	public void setSize(long size) {
		_syncDLObject.setSize(size);
	}

	/**
	* Returns the checksum of this sync d l object.
	*
	* @return the checksum of this sync d l object
	*/
	@Override
	public java.lang.String getChecksum() {
		return _syncDLObject.getChecksum();
	}

	/**
	* Sets the checksum of this sync d l object.
	*
	* @param checksum the checksum of this sync d l object
	*/
	@Override
	public void setChecksum(java.lang.String checksum) {
		_syncDLObject.setChecksum(checksum);
	}

	/**
	* Returns the event of this sync d l object.
	*
	* @return the event of this sync d l object
	*/
	@Override
	public java.lang.String getEvent() {
		return _syncDLObject.getEvent();
	}

	/**
	* Sets the event of this sync d l object.
	*
	* @param event the event of this sync d l object
	*/
	@Override
	public void setEvent(java.lang.String event) {
		_syncDLObject.setEvent(event);
	}

	/**
	* Returns the lock expiration date of this sync d l object.
	*
	* @return the lock expiration date of this sync d l object
	*/
	@Override
	public java.util.Date getLockExpirationDate() {
		return _syncDLObject.getLockExpirationDate();
	}

	/**
	* Sets the lock expiration date of this sync d l object.
	*
	* @param lockExpirationDate the lock expiration date of this sync d l object
	*/
	@Override
	public void setLockExpirationDate(java.util.Date lockExpirationDate) {
		_syncDLObject.setLockExpirationDate(lockExpirationDate);
	}

	/**
	* Returns the lock user ID of this sync d l object.
	*
	* @return the lock user ID of this sync d l object
	*/
	@Override
	public long getLockUserId() {
		return _syncDLObject.getLockUserId();
	}

	/**
	* Sets the lock user ID of this sync d l object.
	*
	* @param lockUserId the lock user ID of this sync d l object
	*/
	@Override
	public void setLockUserId(long lockUserId) {
		_syncDLObject.setLockUserId(lockUserId);
	}

	/**
	* Returns the lock user uuid of this sync d l object.
	*
	* @return the lock user uuid of this sync d l object
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getLockUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _syncDLObject.getLockUserUuid();
	}

	/**
	* Sets the lock user uuid of this sync d l object.
	*
	* @param lockUserUuid the lock user uuid of this sync d l object
	*/
	@Override
	public void setLockUserUuid(java.lang.String lockUserUuid) {
		_syncDLObject.setLockUserUuid(lockUserUuid);
	}

	/**
	* Returns the lock user name of this sync d l object.
	*
	* @return the lock user name of this sync d l object
	*/
	@Override
	public java.lang.String getLockUserName() {
		return _syncDLObject.getLockUserName();
	}

	/**
	* Sets the lock user name of this sync d l object.
	*
	* @param lockUserName the lock user name of this sync d l object
	*/
	@Override
	public void setLockUserName(java.lang.String lockUserName) {
		_syncDLObject.setLockUserName(lockUserName);
	}

	/**
	* Returns the type of this sync d l object.
	*
	* @return the type of this sync d l object
	*/
	@Override
	public java.lang.String getType() {
		return _syncDLObject.getType();
	}

	/**
	* Sets the type of this sync d l object.
	*
	* @param type the type of this sync d l object
	*/
	@Override
	public void setType(java.lang.String type) {
		_syncDLObject.setType(type);
	}

	/**
	* Returns the type p k of this sync d l object.
	*
	* @return the type p k of this sync d l object
	*/
	@Override
	public long getTypePK() {
		return _syncDLObject.getTypePK();
	}

	/**
	* Sets the type p k of this sync d l object.
	*
	* @param typePK the type p k of this sync d l object
	*/
	@Override
	public void setTypePK(long typePK) {
		_syncDLObject.setTypePK(typePK);
	}

	/**
	* Returns the type uuid of this sync d l object.
	*
	* @return the type uuid of this sync d l object
	*/
	@Override
	public java.lang.String getTypeUuid() {
		return _syncDLObject.getTypeUuid();
	}

	/**
	* Sets the type uuid of this sync d l object.
	*
	* @param typeUuid the type uuid of this sync d l object
	*/
	@Override
	public void setTypeUuid(java.lang.String typeUuid) {
		_syncDLObject.setTypeUuid(typeUuid);
	}

	@Override
	public boolean isNew() {
		return _syncDLObject.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_syncDLObject.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _syncDLObject.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncDLObject.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _syncDLObject.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _syncDLObject.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_syncDLObject.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _syncDLObject.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_syncDLObject.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_syncDLObject.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_syncDLObject.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SyncDLObjectWrapper((SyncDLObject)_syncDLObject.clone());
	}

	@Override
	public int compareTo(com.liferay.sync.model.SyncDLObject syncDLObject) {
		return _syncDLObject.compareTo(syncDLObject);
	}

	@Override
	public int hashCode() {
		return _syncDLObject.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sync.model.SyncDLObject> toCacheModel() {
		return _syncDLObject.toCacheModel();
	}

	@Override
	public com.liferay.sync.model.SyncDLObject toEscapedModel() {
		return new SyncDLObjectWrapper(_syncDLObject.toEscapedModel());
	}

	@Override
	public com.liferay.sync.model.SyncDLObject toUnescapedModel() {
		return new SyncDLObjectWrapper(_syncDLObject.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _syncDLObject.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _syncDLObject.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_syncDLObject.persist();
	}

	@Override
	public void setCreateDate(java.util.Date createDate) {
		_syncDLObject.setCreateDate(createDate);
	}

	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_syncDLObject.setModifiedDate(modifiedDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDLObjectWrapper)) {
			return false;
		}

		SyncDLObjectWrapper syncDLObjectWrapper = (SyncDLObjectWrapper)obj;

		if (Validator.equals(_syncDLObject, syncDLObjectWrapper._syncDLObject)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public SyncDLObject getWrappedSyncDLObject() {
		return _syncDLObject;
	}

	@Override
	public SyncDLObject getWrappedModel() {
		return _syncDLObject;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncDLObject.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncDLObject.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncDLObject.resetOriginalValues();
	}

	private SyncDLObject _syncDLObject;
}