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

package com.liferay.socialnetworking.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link WallEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see WallEntry
 * @generated
 */
public class WallEntryWrapper implements WallEntry, ModelWrapper<WallEntry> {
	public WallEntryWrapper(WallEntry wallEntry) {
		_wallEntry = wallEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return WallEntry.class;
	}

	@Override
	public String getModelClassName() {
		return WallEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("wallEntryId", getWallEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("comments", getComments());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long wallEntryId = (Long)attributes.get("wallEntryId");

		if (wallEntryId != null) {
			setWallEntryId(wallEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String comments = (String)attributes.get("comments");

		if (comments != null) {
			setComments(comments);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new WallEntryWrapper((WallEntry)_wallEntry.clone());
	}

	@Override
	public int compareTo(com.liferay.socialnetworking.model.WallEntry wallEntry) {
		return _wallEntry.compareTo(wallEntry);
	}

	/**
	* Returns the comments of this wall entry.
	*
	* @return the comments of this wall entry
	*/
	@Override
	public java.lang.String getComments() {
		return _wallEntry.getComments();
	}

	/**
	* Returns the company ID of this wall entry.
	*
	* @return the company ID of this wall entry
	*/
	@Override
	public long getCompanyId() {
		return _wallEntry.getCompanyId();
	}

	/**
	* Returns the create date of this wall entry.
	*
	* @return the create date of this wall entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _wallEntry.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _wallEntry.getExpandoBridge();
	}

	/**
	* Returns the group ID of this wall entry.
	*
	* @return the group ID of this wall entry
	*/
	@Override
	public long getGroupId() {
		return _wallEntry.getGroupId();
	}

	/**
	* Returns the modified date of this wall entry.
	*
	* @return the modified date of this wall entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _wallEntry.getModifiedDate();
	}

	/**
	* Returns the primary key of this wall entry.
	*
	* @return the primary key of this wall entry
	*/
	@Override
	public long getPrimaryKey() {
		return _wallEntry.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _wallEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this wall entry.
	*
	* @return the user ID of this wall entry
	*/
	@Override
	public long getUserId() {
		return _wallEntry.getUserId();
	}

	/**
	* Returns the user name of this wall entry.
	*
	* @return the user name of this wall entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _wallEntry.getUserName();
	}

	/**
	* Returns the user uuid of this wall entry.
	*
	* @return the user uuid of this wall entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _wallEntry.getUserUuid();
	}

	/**
	* Returns the wall entry ID of this wall entry.
	*
	* @return the wall entry ID of this wall entry
	*/
	@Override
	public long getWallEntryId() {
		return _wallEntry.getWallEntryId();
	}

	@Override
	public int hashCode() {
		return _wallEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _wallEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _wallEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _wallEntry.isNew();
	}

	@Override
	public void persist() {
		_wallEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_wallEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the comments of this wall entry.
	*
	* @param comments the comments of this wall entry
	*/
	@Override
	public void setComments(java.lang.String comments) {
		_wallEntry.setComments(comments);
	}

	/**
	* Sets the company ID of this wall entry.
	*
	* @param companyId the company ID of this wall entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_wallEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this wall entry.
	*
	* @param createDate the create date of this wall entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_wallEntry.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_wallEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_wallEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_wallEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this wall entry.
	*
	* @param groupId the group ID of this wall entry
	*/
	@Override
	public void setGroupId(long groupId) {
		_wallEntry.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this wall entry.
	*
	* @param modifiedDate the modified date of this wall entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_wallEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_wallEntry.setNew(n);
	}

	/**
	* Sets the primary key of this wall entry.
	*
	* @param primaryKey the primary key of this wall entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_wallEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_wallEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this wall entry.
	*
	* @param userId the user ID of this wall entry
	*/
	@Override
	public void setUserId(long userId) {
		_wallEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this wall entry.
	*
	* @param userName the user name of this wall entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_wallEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this wall entry.
	*
	* @param userUuid the user uuid of this wall entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_wallEntry.setUserUuid(userUuid);
	}

	/**
	* Sets the wall entry ID of this wall entry.
	*
	* @param wallEntryId the wall entry ID of this wall entry
	*/
	@Override
	public void setWallEntryId(long wallEntryId) {
		_wallEntry.setWallEntryId(wallEntryId);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialnetworking.model.WallEntry> toCacheModel() {
		return _wallEntry.toCacheModel();
	}

	@Override
	public com.liferay.socialnetworking.model.WallEntry toEscapedModel() {
		return new WallEntryWrapper(_wallEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _wallEntry.toString();
	}

	@Override
	public com.liferay.socialnetworking.model.WallEntry toUnescapedModel() {
		return new WallEntryWrapper(_wallEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _wallEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WallEntryWrapper)) {
			return false;
		}

		WallEntryWrapper wallEntryWrapper = (WallEntryWrapper)obj;

		if (Validator.equals(_wallEntry, wallEntryWrapper._wallEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public WallEntry getWrappedWallEntry() {
		return _wallEntry;
	}

	@Override
	public WallEntry getWrappedModel() {
		return _wallEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _wallEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _wallEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_wallEntry.resetOriginalValues();
	}

	private WallEntry _wallEntry;
}