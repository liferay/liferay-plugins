/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
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

	public long getPrimaryKey() {
		return _folder.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_folder.setPrimaryKey(pk);
	}

	public long getFolderId() {
		return _folder.getFolderId();
	}

	public void setFolderId(long folderId) {
		_folder.setFolderId(folderId);
	}

	public long getCompanyId() {
		return _folder.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_folder.setCompanyId(companyId);
	}

	public long getUserId() {
		return _folder.getUserId();
	}

	public void setUserId(long userId) {
		_folder.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _folder.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_folder.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _folder.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_folder.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _folder.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_folder.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _folder.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_folder.setModifiedDate(modifiedDate);
	}

	public long getAccountId() {
		return _folder.getAccountId();
	}

	public void setAccountId(long accountId) {
		_folder.setAccountId(accountId);
	}

	public java.lang.String getFullName() {
		return _folder.getFullName();
	}

	public void setFullName(java.lang.String fullName) {
		_folder.setFullName(fullName);
	}

	public java.lang.String getDisplayName() {
		return _folder.getDisplayName();
	}

	public void setDisplayName(java.lang.String displayName) {
		_folder.setDisplayName(displayName);
	}

	public int getRemoteMessageCount() {
		return _folder.getRemoteMessageCount();
	}

	public void setRemoteMessageCount(int remoteMessageCount) {
		_folder.setRemoteMessageCount(remoteMessageCount);
	}

	public com.liferay.mail.model.Folder toEscapedModel() {
		return _folder.toEscapedModel();
	}

	public boolean isNew() {
		return _folder.isNew();
	}

	public boolean setNew(boolean n) {
		return _folder.setNew(n);
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

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _folder.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_folder.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _folder.clone();
	}

	public int compareTo(com.liferay.mail.model.Folder folder) {
		return _folder.compareTo(folder);
	}

	public int hashCode() {
		return _folder.hashCode();
	}

	public java.lang.String toString() {
		return _folder.toString();
	}

	public java.lang.String toXmlString() {
		return _folder.toXmlString();
	}

	public Folder getWrappedFolder() {
		return _folder;
	}

	private Folder _folder;
}