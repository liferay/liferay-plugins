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
 * This class is a wrapper for {@link MeetupsEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MeetupsEntry
 * @generated
 */
public class MeetupsEntryWrapper implements MeetupsEntry,
	ModelWrapper<MeetupsEntry> {
	public MeetupsEntryWrapper(MeetupsEntry meetupsEntry) {
		_meetupsEntry = meetupsEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return MeetupsEntry.class;
	}

	@Override
	public String getModelClassName() {
		return MeetupsEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("meetupsEntryId", getMeetupsEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("description", getDescription());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("totalAttendees", getTotalAttendees());
		attributes.put("maxAttendees", getMaxAttendees());
		attributes.put("price", getPrice());
		attributes.put("thumbnailId", getThumbnailId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long meetupsEntryId = (Long)attributes.get("meetupsEntryId");

		if (meetupsEntryId != null) {
			setMeetupsEntryId(meetupsEntryId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Integer totalAttendees = (Integer)attributes.get("totalAttendees");

		if (totalAttendees != null) {
			setTotalAttendees(totalAttendees);
		}

		Integer maxAttendees = (Integer)attributes.get("maxAttendees");

		if (maxAttendees != null) {
			setMaxAttendees(maxAttendees);
		}

		Double price = (Double)attributes.get("price");

		if (price != null) {
			setPrice(price);
		}

		Long thumbnailId = (Long)attributes.get("thumbnailId");

		if (thumbnailId != null) {
			setThumbnailId(thumbnailId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new MeetupsEntryWrapper((MeetupsEntry)_meetupsEntry.clone());
	}

	@Override
	public int compareTo(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry) {
		return _meetupsEntry.compareTo(meetupsEntry);
	}

	/**
	* Returns the company ID of this meetups entry.
	*
	* @return the company ID of this meetups entry
	*/
	@Override
	public long getCompanyId() {
		return _meetupsEntry.getCompanyId();
	}

	/**
	* Returns the create date of this meetups entry.
	*
	* @return the create date of this meetups entry
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _meetupsEntry.getCreateDate();
	}

	/**
	* Returns the description of this meetups entry.
	*
	* @return the description of this meetups entry
	*/
	@Override
	public java.lang.String getDescription() {
		return _meetupsEntry.getDescription();
	}

	/**
	* Returns the end date of this meetups entry.
	*
	* @return the end date of this meetups entry
	*/
	@Override
	public java.util.Date getEndDate() {
		return _meetupsEntry.getEndDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetupsEntry.getExpandoBridge();
	}

	/**
	* Returns the max attendees of this meetups entry.
	*
	* @return the max attendees of this meetups entry
	*/
	@Override
	public int getMaxAttendees() {
		return _meetupsEntry.getMaxAttendees();
	}

	/**
	* Returns the meetups entry ID of this meetups entry.
	*
	* @return the meetups entry ID of this meetups entry
	*/
	@Override
	public long getMeetupsEntryId() {
		return _meetupsEntry.getMeetupsEntryId();
	}

	/**
	* Returns the modified date of this meetups entry.
	*
	* @return the modified date of this meetups entry
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _meetupsEntry.getModifiedDate();
	}

	/**
	* Returns the price of this meetups entry.
	*
	* @return the price of this meetups entry
	*/
	@Override
	public double getPrice() {
		return _meetupsEntry.getPrice();
	}

	/**
	* Returns the primary key of this meetups entry.
	*
	* @return the primary key of this meetups entry
	*/
	@Override
	public long getPrimaryKey() {
		return _meetupsEntry.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _meetupsEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the start date of this meetups entry.
	*
	* @return the start date of this meetups entry
	*/
	@Override
	public java.util.Date getStartDate() {
		return _meetupsEntry.getStartDate();
	}

	/**
	* Returns the thumbnail ID of this meetups entry.
	*
	* @return the thumbnail ID of this meetups entry
	*/
	@Override
	public long getThumbnailId() {
		return _meetupsEntry.getThumbnailId();
	}

	/**
	* Returns the title of this meetups entry.
	*
	* @return the title of this meetups entry
	*/
	@Override
	public java.lang.String getTitle() {
		return _meetupsEntry.getTitle();
	}

	/**
	* Returns the total attendees of this meetups entry.
	*
	* @return the total attendees of this meetups entry
	*/
	@Override
	public int getTotalAttendees() {
		return _meetupsEntry.getTotalAttendees();
	}

	/**
	* Returns the user ID of this meetups entry.
	*
	* @return the user ID of this meetups entry
	*/
	@Override
	public long getUserId() {
		return _meetupsEntry.getUserId();
	}

	/**
	* Returns the user name of this meetups entry.
	*
	* @return the user name of this meetups entry
	*/
	@Override
	public java.lang.String getUserName() {
		return _meetupsEntry.getUserName();
	}

	/**
	* Returns the user uuid of this meetups entry.
	*
	* @return the user uuid of this meetups entry
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _meetupsEntry.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _meetupsEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _meetupsEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _meetupsEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _meetupsEntry.isNew();
	}

	@Override
	public void persist() {
		_meetupsEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_meetupsEntry.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this meetups entry.
	*
	* @param companyId the company ID of this meetups entry
	*/
	@Override
	public void setCompanyId(long companyId) {
		_meetupsEntry.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this meetups entry.
	*
	* @param createDate the create date of this meetups entry
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_meetupsEntry.setCreateDate(createDate);
	}

	/**
	* Sets the description of this meetups entry.
	*
	* @param description the description of this meetups entry
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_meetupsEntry.setDescription(description);
	}

	/**
	* Sets the end date of this meetups entry.
	*
	* @param endDate the end date of this meetups entry
	*/
	@Override
	public void setEndDate(java.util.Date endDate) {
		_meetupsEntry.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_meetupsEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_meetupsEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetupsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the max attendees of this meetups entry.
	*
	* @param maxAttendees the max attendees of this meetups entry
	*/
	@Override
	public void setMaxAttendees(int maxAttendees) {
		_meetupsEntry.setMaxAttendees(maxAttendees);
	}

	/**
	* Sets the meetups entry ID of this meetups entry.
	*
	* @param meetupsEntryId the meetups entry ID of this meetups entry
	*/
	@Override
	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntry.setMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Sets the modified date of this meetups entry.
	*
	* @param modifiedDate the modified date of this meetups entry
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetupsEntry.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_meetupsEntry.setNew(n);
	}

	/**
	* Sets the price of this meetups entry.
	*
	* @param price the price of this meetups entry
	*/
	@Override
	public void setPrice(double price) {
		_meetupsEntry.setPrice(price);
	}

	/**
	* Sets the primary key of this meetups entry.
	*
	* @param primaryKey the primary key of this meetups entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_meetupsEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_meetupsEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this meetups entry.
	*
	* @param startDate the start date of this meetups entry
	*/
	@Override
	public void setStartDate(java.util.Date startDate) {
		_meetupsEntry.setStartDate(startDate);
	}

	/**
	* Sets the thumbnail ID of this meetups entry.
	*
	* @param thumbnailId the thumbnail ID of this meetups entry
	*/
	@Override
	public void setThumbnailId(long thumbnailId) {
		_meetupsEntry.setThumbnailId(thumbnailId);
	}

	/**
	* Sets the title of this meetups entry.
	*
	* @param title the title of this meetups entry
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_meetupsEntry.setTitle(title);
	}

	/**
	* Sets the total attendees of this meetups entry.
	*
	* @param totalAttendees the total attendees of this meetups entry
	*/
	@Override
	public void setTotalAttendees(int totalAttendees) {
		_meetupsEntry.setTotalAttendees(totalAttendees);
	}

	/**
	* Sets the user ID of this meetups entry.
	*
	* @param userId the user ID of this meetups entry
	*/
	@Override
	public void setUserId(long userId) {
		_meetupsEntry.setUserId(userId);
	}

	/**
	* Sets the user name of this meetups entry.
	*
	* @param userName the user name of this meetups entry
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_meetupsEntry.setUserName(userName);
	}

	/**
	* Sets the user uuid of this meetups entry.
	*
	* @param userUuid the user uuid of this meetups entry
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_meetupsEntry.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.socialnetworking.model.MeetupsEntry> toCacheModel() {
		return _meetupsEntry.toCacheModel();
	}

	@Override
	public com.liferay.socialnetworking.model.MeetupsEntry toEscapedModel() {
		return new MeetupsEntryWrapper(_meetupsEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _meetupsEntry.toString();
	}

	@Override
	public com.liferay.socialnetworking.model.MeetupsEntry toUnescapedModel() {
		return new MeetupsEntryWrapper(_meetupsEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _meetupsEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof MeetupsEntryWrapper)) {
			return false;
		}

		MeetupsEntryWrapper meetupsEntryWrapper = (MeetupsEntryWrapper)obj;

		if (Validator.equals(_meetupsEntry, meetupsEntryWrapper._meetupsEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public MeetupsEntry getWrappedMeetupsEntry() {
		return _meetupsEntry;
	}

	@Override
	public MeetupsEntry getWrappedModel() {
		return _meetupsEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _meetupsEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _meetupsEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_meetupsEntry.resetOriginalValues();
	}

	private MeetupsEntry _meetupsEntry;
}