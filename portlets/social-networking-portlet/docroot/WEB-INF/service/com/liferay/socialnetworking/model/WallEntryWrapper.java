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

package com.liferay.socialnetworking.model;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link WallEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WallEntry
 * @generated
 */
public class WallEntryWrapper implements WallEntry {
	public WallEntryWrapper(WallEntry wallEntry) {
		_wallEntry = wallEntry;
	}

	public long getPrimaryKey() {
		return _wallEntry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_wallEntry.setPrimaryKey(pk);
	}

	public long getWallEntryId() {
		return _wallEntry.getWallEntryId();
	}

	public void setWallEntryId(long wallEntryId) {
		_wallEntry.setWallEntryId(wallEntryId);
	}

	public long getGroupId() {
		return _wallEntry.getGroupId();
	}

	public void setGroupId(long groupId) {
		_wallEntry.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _wallEntry.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_wallEntry.setCompanyId(companyId);
	}

	public long getUserId() {
		return _wallEntry.getUserId();
	}

	public void setUserId(long userId) {
		_wallEntry.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntry.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_wallEntry.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _wallEntry.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_wallEntry.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _wallEntry.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_wallEntry.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _wallEntry.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_wallEntry.setModifiedDate(modifiedDate);
	}

	public java.lang.String getComments() {
		return _wallEntry.getComments();
	}

	public void setComments(java.lang.String comments) {
		_wallEntry.setComments(comments);
	}

	public com.liferay.socialnetworking.model.WallEntry toEscapedModel() {
		return _wallEntry.toEscapedModel();
	}

	public boolean isNew() {
		return _wallEntry.isNew();
	}

	public boolean setNew(boolean n) {
		return _wallEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _wallEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_wallEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _wallEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_wallEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _wallEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wallEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wallEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _wallEntry.clone();
	}

	public int compareTo(com.liferay.socialnetworking.model.WallEntry wallEntry) {
		return _wallEntry.compareTo(wallEntry);
	}

	public int hashCode() {
		return _wallEntry.hashCode();
	}

	public java.lang.String toString() {
		return _wallEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _wallEntry.toXmlString();
	}

	public WallEntry getWrappedWallEntry() {
		return _wallEntry;
	}

	private WallEntry _wallEntry;
}