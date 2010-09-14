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

	/**
	* Gets the primary key of this wall entry.
	*
	* @return the primary key of this wall entry
	*/
	public long getPrimaryKey() {
		return _wallEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this wall entry
	*
	* @param pk the primary key of this wall entry
	*/
	public void setPrimaryKey(long pk) {
		_wallEntry.setPrimaryKey(pk);
	}

	/**
	* Gets the wall entry id of this wall entry.
	*
	* @return the wall entry id of this wall entry
	*/
	public long getWallEntryId() {
		return _wallEntry.getWallEntryId();
	}

	/**
	* Sets the wall entry id of this wall entry.
	*
	* @param wallEntryId the wall entry id of this wall entry
	*/
	public void setWallEntryId(long wallEntryId) {
		_wallEntry.setWallEntryId(wallEntryId);
	}

	/**
	* Gets the group id of this wall entry.
	*
	* @return the group id of this wall entry
	*/
	public long getGroupId() {
		return _wallEntry.getGroupId();
	}

	/**
	* Sets the group id of this wall entry.
	*
	* @param groupId the group id of this wall entry
	*/
	public void setGroupId(long groupId) {
		_wallEntry.setGroupId(groupId);
	}

	/**
	* Gets the company id of this wall entry.
	*
	* @return the company id of this wall entry
	*/
	public long getCompanyId() {
		return _wallEntry.getCompanyId();
	}

	/**
	* Sets the company id of this wall entry.
	*
	* @param companyId the company id of this wall entry
	*/
	public void setCompanyId(long companyId) {
		_wallEntry.setCompanyId(companyId);
	}

	/**
	* Gets the user id of this wall entry.
	*
	* @return the user id of this wall entry
	*/
	public long getUserId() {
		return _wallEntry.getUserId();
	}

	/**
	* Sets the user id of this wall entry.
	*
	* @param userId the user id of this wall entry
	*/
	public void setUserId(long userId) {
		_wallEntry.setUserId(userId);
	}

	/**
	* Gets the user uuid of this wall entry.
	*
	* @return the user uuid of this wall entry
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _wallEntry.getUserUuid();
	}

	/**
	* Sets the user uuid of this wall entry.
	*
	* @param userUuid the user uuid of this wall entry
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_wallEntry.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this wall entry.
	*
	* @return the user name of this wall entry
	*/
	public java.lang.String getUserName() {
		return _wallEntry.getUserName();
	}

	/**
	* Sets the user name of this wall entry.
	*
	* @param userName the user name of this wall entry
	*/
	public void setUserName(java.lang.String userName) {
		_wallEntry.setUserName(userName);
	}

	/**
	* Gets the create date of this wall entry.
	*
	* @return the create date of this wall entry
	*/
	public java.util.Date getCreateDate() {
		return _wallEntry.getCreateDate();
	}

	/**
	* Sets the create date of this wall entry.
	*
	* @param createDate the create date of this wall entry
	*/
	public void setCreateDate(java.util.Date createDate) {
		_wallEntry.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this wall entry.
	*
	* @return the modified date of this wall entry
	*/
	public java.util.Date getModifiedDate() {
		return _wallEntry.getModifiedDate();
	}

	/**
	* Sets the modified date of this wall entry.
	*
	* @param modifiedDate the modified date of this wall entry
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wallEntry.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the comments of this wall entry.
	*
	* @return the comments of this wall entry
	*/
	public java.lang.String getComments() {
		return _wallEntry.getComments();
	}

	/**
	* Sets the comments of this wall entry.
	*
	* @param comments the comments of this wall entry
	*/
	public void setComments(java.lang.String comments) {
		_wallEntry.setComments(comments);
	}

	public boolean isNew() {
		return _wallEntry.isNew();
	}

	public void setNew(boolean n) {
		_wallEntry.setNew(n);
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

	public com.liferay.socialnetworking.model.WallEntry toEscapedModel() {
		return _wallEntry.toEscapedModel();
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