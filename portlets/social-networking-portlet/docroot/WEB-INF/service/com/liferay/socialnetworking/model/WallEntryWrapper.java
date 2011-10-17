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

package com.liferay.socialnetworking.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link WallEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       WallEntry
 * @generated
 */
public class WallEntryWrapper implements WallEntry, ModelWrapper<WallEntry> {
	public WallEntryWrapper(WallEntry wallEntry) {
		_wallEntry = wallEntry;
	}

	public Class<?> getModelClass() {
		return WallEntry.class;
	}

	public String getModelClassName() {
		return WallEntry.class.getName();
	}

	/**
	* Returns the primary key of this wall entry.
	*
	* @return the primary key of this wall entry
	*/
	public long getPrimaryKey() {
		return _wallEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this wall entry.
	*
	* @param primaryKey the primary key of this wall entry
	*/
	public void setPrimaryKey(long primaryKey) {
		_wallEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the wall entry ID of this wall entry.
	*
	* @return the wall entry ID of this wall entry
	*/
	public long getWallEntryId() {
		return _wallEntry.getWallEntryId();
	}

	/**
	* Sets the wall entry ID of this wall entry.
	*
	* @param wallEntryId the wall entry ID of this wall entry
	*/
	public void setWallEntryId(long wallEntryId) {
		_wallEntry.setWallEntryId(wallEntryId);
	}

	/**
	* Returns the group ID of this wall entry.
	*
	* @return the group ID of this wall entry
	*/
	public long getGroupId() {
		return _wallEntry.getGroupId();
	}

	/**
	* Sets the group ID of this wall entry.
	*
	* @param groupId the group ID of this wall entry
	*/
	public void setGroupId(long groupId) {
		_wallEntry.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this wall entry.
	*
	* @return the company ID of this wall entry
	*/
	public long getCompanyId() {
		return _wallEntry.getCompanyId();
	}

	/**
	* Sets the company ID of this wall entry.
	*
	* @param companyId the company ID of this wall entry
	*/
	public void setCompanyId(long companyId) {
		_wallEntry.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this wall entry.
	*
	* @return the user ID of this wall entry
	*/
	public long getUserId() {
		return _wallEntry.getUserId();
	}

	/**
	* Sets the user ID of this wall entry.
	*
	* @param userId the user ID of this wall entry
	*/
	public void setUserId(long userId) {
		_wallEntry.setUserId(userId);
	}

	/**
	* Returns the user uuid of this wall entry.
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
	* Returns the user name of this wall entry.
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
	* Returns the create date of this wall entry.
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
	* Returns the modified date of this wall entry.
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
	* Returns the comments of this wall entry.
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _wallEntry.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wallEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wallEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wallEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new WallEntryWrapper((WallEntry)_wallEntry.clone());
	}

	public int compareTo(com.liferay.socialnetworking.model.WallEntry wallEntry) {
		return _wallEntry.compareTo(wallEntry);
	}

	@Override
	public int hashCode() {
		return _wallEntry.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.socialnetworking.model.WallEntry> toCacheModel() {
		return _wallEntry.toCacheModel();
	}

	public com.liferay.socialnetworking.model.WallEntry toEscapedModel() {
		return new WallEntryWrapper(_wallEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wallEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _wallEntry.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_wallEntry.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public WallEntry getWrappedWallEntry() {
		return _wallEntry;
	}

	public WallEntry getWrappedModel() {
		return _wallEntry;
	}

	public void resetOriginalValues() {
		_wallEntry.resetOriginalValues();
	}

	private WallEntry _wallEntry;
}