/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link Folder}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Folder
 * @generated
 */
public class FolderWrapper implements Folder {
	public FolderWrapper(Folder folder) {
		_folder = folder;
	}

	public Class<?> getModelClass() {
		return Folder.class;
	}

	public String getModelClassName() {
		return Folder.class.getName();
	}

	/**
	* Returns the primary key of this folder.
	*
	* @return the primary key of this folder
	*/
	public long getPrimaryKey() {
		return _folder.getPrimaryKey();
	}

	/**
	* Sets the primary key of this folder.
	*
	* @param primaryKey the primary key of this folder
	*/
	public void setPrimaryKey(long primaryKey) {
		_folder.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the folder ID of this folder.
	*
	* @return the folder ID of this folder
	*/
	public long getFolderId() {
		return _folder.getFolderId();
	}

	/**
	* Sets the folder ID of this folder.
	*
	* @param folderId the folder ID of this folder
	*/
	public void setFolderId(long folderId) {
		_folder.setFolderId(folderId);
	}

	/**
	* Returns the company ID of this folder.
	*
	* @return the company ID of this folder
	*/
	public long getCompanyId() {
		return _folder.getCompanyId();
	}

	/**
	* Sets the company ID of this folder.
	*
	* @param companyId the company ID of this folder
	*/
	public void setCompanyId(long companyId) {
		_folder.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this folder.
	*
	* @return the user ID of this folder
	*/
	public long getUserId() {
		return _folder.getUserId();
	}

	/**
	* Sets the user ID of this folder.
	*
	* @param userId the user ID of this folder
	*/
	public void setUserId(long userId) {
		_folder.setUserId(userId);
	}

	/**
	* Returns the user uuid of this folder.
	*
	* @return the user uuid of this folder
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folder.getUserUuid();
	}

	/**
	* Sets the user uuid of this folder.
	*
	* @param userUuid the user uuid of this folder
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_folder.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this folder.
	*
	* @return the user name of this folder
	*/
	public java.lang.String getUserName() {
		return _folder.getUserName();
	}

	/**
	* Sets the user name of this folder.
	*
	* @param userName the user name of this folder
	*/
	public void setUserName(java.lang.String userName) {
		_folder.setUserName(userName);
	}

	/**
	* Returns the create date of this folder.
	*
	* @return the create date of this folder
	*/
	public java.util.Date getCreateDate() {
		return _folder.getCreateDate();
	}

	/**
	* Sets the create date of this folder.
	*
	* @param createDate the create date of this folder
	*/
	public void setCreateDate(java.util.Date createDate) {
		_folder.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this folder.
	*
	* @return the modified date of this folder
	*/
	public java.util.Date getModifiedDate() {
		return _folder.getModifiedDate();
	}

	/**
	* Sets the modified date of this folder.
	*
	* @param modifiedDate the modified date of this folder
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_folder.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the account ID of this folder.
	*
	* @return the account ID of this folder
	*/
	public long getAccountId() {
		return _folder.getAccountId();
	}

	/**
	* Sets the account ID of this folder.
	*
	* @param accountId the account ID of this folder
	*/
	public void setAccountId(long accountId) {
		_folder.setAccountId(accountId);
	}

	/**
	* Returns the full name of this folder.
	*
	* @return the full name of this folder
	*/
	public java.lang.String getFullName() {
		return _folder.getFullName();
	}

	/**
	* Sets the full name of this folder.
	*
	* @param fullName the full name of this folder
	*/
	public void setFullName(java.lang.String fullName) {
		_folder.setFullName(fullName);
	}

	/**
	* Returns the display name of this folder.
	*
	* @return the display name of this folder
	*/
	public java.lang.String getDisplayName() {
		return _folder.getDisplayName();
	}

	/**
	* Sets the display name of this folder.
	*
	* @param displayName the display name of this folder
	*/
	public void setDisplayName(java.lang.String displayName) {
		_folder.setDisplayName(displayName);
	}

	/**
	* Returns the remote message count of this folder.
	*
	* @return the remote message count of this folder
	*/
	public int getRemoteMessageCount() {
		return _folder.getRemoteMessageCount();
	}

	/**
	* Sets the remote message count of this folder.
	*
	* @param remoteMessageCount the remote message count of this folder
	*/
	public void setRemoteMessageCount(int remoteMessageCount) {
		_folder.setRemoteMessageCount(remoteMessageCount);
	}

	public boolean isNew() {
		return _folder.isNew();
	}

	public void setNew(boolean n) {
		_folder.setNew(n);
	}

	public boolean isCachedModel() {
		return _folder.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_folder.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _folder.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_folder.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _folder.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_folder.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _folder.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_folder.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new FolderWrapper((Folder)_folder.clone());
	}

	public int compareTo(Folder folder) {
		return _folder.compareTo(folder);
	}

	@Override
	public int hashCode() {
		return _folder.hashCode();
	}

	public com.liferay.portal.model.CacheModel<Folder> toCacheModel() {
		return _folder.toCacheModel();
	}

	public Folder toEscapedModel() {
		return new FolderWrapper(_folder.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _folder.toString();
	}

	public java.lang.String toXmlString() {
		return _folder.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_folder.persist();
	}

	public Folder getWrappedFolder() {
		return _folder;
	}

	public void resetOriginalValues() {
		_folder.resetOriginalValues();
	}

	private Folder _folder;
}