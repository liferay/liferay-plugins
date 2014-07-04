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

package com.liferay.bbb.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BBBMeeting}.
 * </p>
 *
 * @author Shinn Lok
 * @see BBBMeeting
 * @generated
 */
public class BBBMeetingWrapper implements BBBMeeting, ModelWrapper<BBBMeeting> {
	public BBBMeetingWrapper(BBBMeeting bbbMeeting) {
		_bbbMeeting = bbbMeeting;
	}

	@Override
	public Class<?> getModelClass() {
		return BBBMeeting.class;
	}

	@Override
	public String getModelClassName() {
		return BBBMeeting.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("bbbMeetingId", getBbbMeetingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("bbbServerId", getBbbServerId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("attendeePassword", getAttendeePassword());
		attributes.put("moderatorPassword", getModeratorPassword());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long bbbMeetingId = (Long)attributes.get("bbbMeetingId");

		if (bbbMeetingId != null) {
			setBbbMeetingId(bbbMeetingId);
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

		Long bbbServerId = (Long)attributes.get("bbbServerId");

		if (bbbServerId != null) {
			setBbbServerId(bbbServerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String attendeePassword = (String)attributes.get("attendeePassword");

		if (attendeePassword != null) {
			setAttendeePassword(attendeePassword);
		}

		String moderatorPassword = (String)attributes.get("moderatorPassword");

		if (moderatorPassword != null) {
			setModeratorPassword(moderatorPassword);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new BBBMeetingWrapper((BBBMeeting)_bbbMeeting.clone());
	}

	@Override
	public int compareTo(com.liferay.bbb.model.BBBMeeting bbbMeeting) {
		return _bbbMeeting.compareTo(bbbMeeting);
	}

	/**
	* Returns the attendee password of this b b b meeting.
	*
	* @return the attendee password of this b b b meeting
	*/
	@Override
	public java.lang.String getAttendeePassword() {
		return _bbbMeeting.getAttendeePassword();
	}

	/**
	* Returns the bbb meeting ID of this b b b meeting.
	*
	* @return the bbb meeting ID of this b b b meeting
	*/
	@Override
	public long getBbbMeetingId() {
		return _bbbMeeting.getBbbMeetingId();
	}

	/**
	* Returns the bbb server ID of this b b b meeting.
	*
	* @return the bbb server ID of this b b b meeting
	*/
	@Override
	public long getBbbServerId() {
		return _bbbMeeting.getBbbServerId();
	}

	/**
	* Returns the company ID of this b b b meeting.
	*
	* @return the company ID of this b b b meeting
	*/
	@Override
	public long getCompanyId() {
		return _bbbMeeting.getCompanyId();
	}

	/**
	* Returns the create date of this b b b meeting.
	*
	* @return the create date of this b b b meeting
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _bbbMeeting.getCreateDate();
	}

	/**
	* Returns the description of this b b b meeting.
	*
	* @return the description of this b b b meeting
	*/
	@Override
	public java.lang.String getDescription() {
		return _bbbMeeting.getDescription();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _bbbMeeting.getExpandoBridge();
	}

	/**
	* Returns the group ID of this b b b meeting.
	*
	* @return the group ID of this b b b meeting
	*/
	@Override
	public long getGroupId() {
		return _bbbMeeting.getGroupId();
	}

	/**
	* Returns the moderator password of this b b b meeting.
	*
	* @return the moderator password of this b b b meeting
	*/
	@Override
	public java.lang.String getModeratorPassword() {
		return _bbbMeeting.getModeratorPassword();
	}

	/**
	* Returns the modified date of this b b b meeting.
	*
	* @return the modified date of this b b b meeting
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _bbbMeeting.getModifiedDate();
	}

	/**
	* Returns the name of this b b b meeting.
	*
	* @return the name of this b b b meeting
	*/
	@Override
	public java.lang.String getName() {
		return _bbbMeeting.getName();
	}

	/**
	* Returns the primary key of this b b b meeting.
	*
	* @return the primary key of this b b b meeting
	*/
	@Override
	public long getPrimaryKey() {
		return _bbbMeeting.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _bbbMeeting.getPrimaryKeyObj();
	}

	/**
	* Returns the status of this b b b meeting.
	*
	* @return the status of this b b b meeting
	*/
	@Override
	public int getStatus() {
		return _bbbMeeting.getStatus();
	}

	/**
	* Returns the user ID of this b b b meeting.
	*
	* @return the user ID of this b b b meeting
	*/
	@Override
	public long getUserId() {
		return _bbbMeeting.getUserId();
	}

	/**
	* Returns the user name of this b b b meeting.
	*
	* @return the user name of this b b b meeting
	*/
	@Override
	public java.lang.String getUserName() {
		return _bbbMeeting.getUserName();
	}

	/**
	* Returns the user uuid of this b b b meeting.
	*
	* @return the user uuid of this b b b meeting
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _bbbMeeting.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _bbbMeeting.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _bbbMeeting.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _bbbMeeting.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _bbbMeeting.isNew();
	}

	@Override
	public void persist() {
		_bbbMeeting.persist();
	}

	/**
	* Sets the attendee password of this b b b meeting.
	*
	* @param attendeePassword the attendee password of this b b b meeting
	*/
	@Override
	public void setAttendeePassword(java.lang.String attendeePassword) {
		_bbbMeeting.setAttendeePassword(attendeePassword);
	}

	/**
	* Sets the bbb meeting ID of this b b b meeting.
	*
	* @param bbbMeetingId the bbb meeting ID of this b b b meeting
	*/
	@Override
	public void setBbbMeetingId(long bbbMeetingId) {
		_bbbMeeting.setBbbMeetingId(bbbMeetingId);
	}

	/**
	* Sets the bbb server ID of this b b b meeting.
	*
	* @param bbbServerId the bbb server ID of this b b b meeting
	*/
	@Override
	public void setBbbServerId(long bbbServerId) {
		_bbbMeeting.setBbbServerId(bbbServerId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_bbbMeeting.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this b b b meeting.
	*
	* @param companyId the company ID of this b b b meeting
	*/
	@Override
	public void setCompanyId(long companyId) {
		_bbbMeeting.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this b b b meeting.
	*
	* @param createDate the create date of this b b b meeting
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_bbbMeeting.setCreateDate(createDate);
	}

	/**
	* Sets the description of this b b b meeting.
	*
	* @param description the description of this b b b meeting
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_bbbMeeting.setDescription(description);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_bbbMeeting.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_bbbMeeting.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_bbbMeeting.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this b b b meeting.
	*
	* @param groupId the group ID of this b b b meeting
	*/
	@Override
	public void setGroupId(long groupId) {
		_bbbMeeting.setGroupId(groupId);
	}

	/**
	* Sets the moderator password of this b b b meeting.
	*
	* @param moderatorPassword the moderator password of this b b b meeting
	*/
	@Override
	public void setModeratorPassword(java.lang.String moderatorPassword) {
		_bbbMeeting.setModeratorPassword(moderatorPassword);
	}

	/**
	* Sets the modified date of this b b b meeting.
	*
	* @param modifiedDate the modified date of this b b b meeting
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_bbbMeeting.setModifiedDate(modifiedDate);
	}

	/**
	* Sets the name of this b b b meeting.
	*
	* @param name the name of this b b b meeting
	*/
	@Override
	public void setName(java.lang.String name) {
		_bbbMeeting.setName(name);
	}

	@Override
	public void setNew(boolean n) {
		_bbbMeeting.setNew(n);
	}

	/**
	* Sets the primary key of this b b b meeting.
	*
	* @param primaryKey the primary key of this b b b meeting
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_bbbMeeting.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_bbbMeeting.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the status of this b b b meeting.
	*
	* @param status the status of this b b b meeting
	*/
	@Override
	public void setStatus(int status) {
		_bbbMeeting.setStatus(status);
	}

	/**
	* Sets the user ID of this b b b meeting.
	*
	* @param userId the user ID of this b b b meeting
	*/
	@Override
	public void setUserId(long userId) {
		_bbbMeeting.setUserId(userId);
	}

	/**
	* Sets the user name of this b b b meeting.
	*
	* @param userName the user name of this b b b meeting
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_bbbMeeting.setUserName(userName);
	}

	/**
	* Sets the user uuid of this b b b meeting.
	*
	* @param userUuid the user uuid of this b b b meeting
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_bbbMeeting.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.bbb.model.BBBMeeting> toCacheModel() {
		return _bbbMeeting.toCacheModel();
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting toEscapedModel() {
		return new BBBMeetingWrapper(_bbbMeeting.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _bbbMeeting.toString();
	}

	@Override
	public com.liferay.bbb.model.BBBMeeting toUnescapedModel() {
		return new BBBMeetingWrapper(_bbbMeeting.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _bbbMeeting.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BBBMeetingWrapper)) {
			return false;
		}

		BBBMeetingWrapper bbbMeetingWrapper = (BBBMeetingWrapper)obj;

		if (Validator.equals(_bbbMeeting, bbbMeetingWrapper._bbbMeeting)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public BBBMeeting getWrappedBBBMeeting() {
		return _bbbMeeting;
	}

	@Override
	public BBBMeeting getWrappedModel() {
		return _bbbMeeting;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _bbbMeeting.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _bbbMeeting.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_bbbMeeting.resetOriginalValues();
	}

	private BBBMeeting _bbbMeeting;
}