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

package com.liferay.mail.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Folder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Folder
 * @generated
 */
@ProviderType
public class FolderWrapper implements Folder, ModelWrapper<Folder> {
	public FolderWrapper(Folder folder) {
		_folder = folder;
	}

	@Override
	public Class<?> getModelClass() {
		return Folder.class;
	}

	@Override
	public String getModelClassName() {
		return Folder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("folderId", getFolderId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountId", getAccountId());
		attributes.put("fullName", getFullName());
		attributes.put("displayName", getDisplayName());
		attributes.put("remoteMessageCount", getRemoteMessageCount());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long folderId = (Long)attributes.get("folderId");

		if (folderId != null) {
			setFolderId(folderId);
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

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String fullName = (String)attributes.get("fullName");

		if (fullName != null) {
			setFullName(fullName);
		}

		String displayName = (String)attributes.get("displayName");

		if (displayName != null) {
			setDisplayName(displayName);
		}

		Integer remoteMessageCount = (Integer)attributes.get(
				"remoteMessageCount");

		if (remoteMessageCount != null) {
			setRemoteMessageCount(remoteMessageCount);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FolderWrapper((Folder)_folder.clone());
	}

	@Override
	public int compareTo(com.liferay.mail.model.Folder folder) {
		return _folder.compareTo(folder);
	}

	/**
	* Returns the account ID of this folder.
	*
	* @return the account ID of this folder
	*/
	@Override
	public long getAccountId() {
		return _folder.getAccountId();
	}

	/**
	* Returns the company ID of this folder.
	*
	* @return the company ID of this folder
	*/
	@Override
	public long getCompanyId() {
		return _folder.getCompanyId();
	}

	/**
	* Returns the create date of this folder.
	*
	* @return the create date of this folder
	*/
	@Override
	public Date getCreateDate() {
		return _folder.getCreateDate();
	}

	/**
	* Returns the display name of this folder.
	*
	* @return the display name of this folder
	*/
	@Override
	public java.lang.String getDisplayName() {
		return _folder.getDisplayName();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _folder.getExpandoBridge();
	}

	/**
	* Returns the folder ID of this folder.
	*
	* @return the folder ID of this folder
	*/
	@Override
	public long getFolderId() {
		return _folder.getFolderId();
	}

	/**
	* Returns the full name of this folder.
	*
	* @return the full name of this folder
	*/
	@Override
	public java.lang.String getFullName() {
		return _folder.getFullName();
	}

	/**
	* Returns the modified date of this folder.
	*
	* @return the modified date of this folder
	*/
	@Override
	public Date getModifiedDate() {
		return _folder.getModifiedDate();
	}

	/**
	* Returns the primary key of this folder.
	*
	* @return the primary key of this folder
	*/
	@Override
	public long getPrimaryKey() {
		return _folder.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _folder.getPrimaryKeyObj();
	}

	/**
	* Returns the remote message count of this folder.
	*
	* @return the remote message count of this folder
	*/
	@Override
	public int getRemoteMessageCount() {
		return _folder.getRemoteMessageCount();
	}

	/**
	* Returns the user ID of this folder.
	*
	* @return the user ID of this folder
	*/
	@Override
	public long getUserId() {
		return _folder.getUserId();
	}

	/**
	* Returns the user name of this folder.
	*
	* @return the user name of this folder
	*/
	@Override
	public java.lang.String getUserName() {
		return _folder.getUserName();
	}

	/**
	* Returns the user uuid of this folder.
	*
	* @return the user uuid of this folder
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _folder.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _folder.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _folder.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _folder.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _folder.isNew();
	}

	@Override
	public void persist() {
		_folder.persist();
	}

	/**
	* Sets the account ID of this folder.
	*
	* @param accountId the account ID of this folder
	*/
	@Override
	public void setAccountId(long accountId) {
		_folder.setAccountId(accountId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_folder.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this folder.
	*
	* @param companyId the company ID of this folder
	*/
	@Override
	public void setCompanyId(long companyId) {
		_folder.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this folder.
	*
	* @param createDate the create date of this folder
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_folder.setCreateDate(createDate);
	}

	/**
	* Sets the display name of this folder.
	*
	* @param displayName the display name of this folder
	*/
	@Override
	public void setDisplayName(java.lang.String displayName) {
		_folder.setDisplayName(displayName);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_folder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_folder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_folder.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the folder ID of this folder.
	*
	* @param folderId the folder ID of this folder
	*/
	@Override
	public void setFolderId(long folderId) {
		_folder.setFolderId(folderId);
	}

	/**
	* Sets the full name of this folder.
	*
	* @param fullName the full name of this folder
	*/
	@Override
	public void setFullName(java.lang.String fullName) {
		_folder.setFullName(fullName);
	}

	/**
	* Sets the modified date of this folder.
	*
	* @param modifiedDate the modified date of this folder
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_folder.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_folder.setNew(n);
	}

	/**
	* Sets the primary key of this folder.
	*
	* @param primaryKey the primary key of this folder
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_folder.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_folder.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the remote message count of this folder.
	*
	* @param remoteMessageCount the remote message count of this folder
	*/
	@Override
	public void setRemoteMessageCount(int remoteMessageCount) {
		_folder.setRemoteMessageCount(remoteMessageCount);
	}

	/**
	* Sets the user ID of this folder.
	*
	* @param userId the user ID of this folder
	*/
	@Override
	public void setUserId(long userId) {
		_folder.setUserId(userId);
	}

	/**
	* Sets the user name of this folder.
	*
	* @param userName the user name of this folder
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_folder.setUserName(userName);
	}

	/**
	* Sets the user uuid of this folder.
	*
	* @param userUuid the user uuid of this folder
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_folder.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.mail.model.Folder> toCacheModel() {
		return _folder.toCacheModel();
	}

	@Override
	public com.liferay.mail.model.Folder toEscapedModel() {
		return new FolderWrapper(_folder.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _folder.toString();
	}

	@Override
	public com.liferay.mail.model.Folder toUnescapedModel() {
		return new FolderWrapper(_folder.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _folder.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FolderWrapper)) {
			return false;
		}

		FolderWrapper folderWrapper = (FolderWrapper)obj;

		if (Validator.equals(_folder, folderWrapper._folder)) {
			return true;
		}

		return false;
	}

	@Override
	public Folder getWrappedModel() {
		return _folder;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _folder.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _folder.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_folder.resetOriginalValues();
	}

	private final Folder _folder;
}