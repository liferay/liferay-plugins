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
 * This class is a wrapper for {@link MeetupsEntry}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MeetupsEntry
 * @generated
 */
public class MeetupsEntryWrapper implements MeetupsEntry {
	public MeetupsEntryWrapper(MeetupsEntry meetupsEntry) {
		_meetupsEntry = meetupsEntry;
	}

	public long getPrimaryKey() {
		return _meetupsEntry.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_meetupsEntry.setPrimaryKey(pk);
	}

	public long getMeetupsEntryId() {
		return _meetupsEntry.getMeetupsEntryId();
	}

	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsEntry.setMeetupsEntryId(meetupsEntryId);
	}

	public long getCompanyId() {
		return _meetupsEntry.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_meetupsEntry.setCompanyId(companyId);
	}

	public long getUserId() {
		return _meetupsEntry.getUserId();
	}

	public void setUserId(long userId) {
		_meetupsEntry.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsEntry.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_meetupsEntry.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _meetupsEntry.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_meetupsEntry.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _meetupsEntry.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_meetupsEntry.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _meetupsEntry.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetupsEntry.setModifiedDate(modifiedDate);
	}

	public java.lang.String getTitle() {
		return _meetupsEntry.getTitle();
	}

	public void setTitle(java.lang.String title) {
		_meetupsEntry.setTitle(title);
	}

	public java.lang.String getDescription() {
		return _meetupsEntry.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_meetupsEntry.setDescription(description);
	}

	public java.util.Date getStartDate() {
		return _meetupsEntry.getStartDate();
	}

	public void setStartDate(java.util.Date startDate) {
		_meetupsEntry.setStartDate(startDate);
	}

	public java.util.Date getEndDate() {
		return _meetupsEntry.getEndDate();
	}

	public void setEndDate(java.util.Date endDate) {
		_meetupsEntry.setEndDate(endDate);
	}

	public int getTotalAttendees() {
		return _meetupsEntry.getTotalAttendees();
	}

	public void setTotalAttendees(int totalAttendees) {
		_meetupsEntry.setTotalAttendees(totalAttendees);
	}

	public int getMaxAttendees() {
		return _meetupsEntry.getMaxAttendees();
	}

	public void setMaxAttendees(int maxAttendees) {
		_meetupsEntry.setMaxAttendees(maxAttendees);
	}

	public double getPrice() {
		return _meetupsEntry.getPrice();
	}

	public void setPrice(double price) {
		_meetupsEntry.setPrice(price);
	}

	public long getThumbnailId() {
		return _meetupsEntry.getThumbnailId();
	}

	public void setThumbnailId(long thumbnailId) {
		_meetupsEntry.setThumbnailId(thumbnailId);
	}

	public com.liferay.socialnetworking.model.MeetupsEntry toEscapedModel() {
		return _meetupsEntry.toEscapedModel();
	}

	public boolean isNew() {
		return _meetupsEntry.isNew();
	}

	public void setNew(boolean n) {
		_meetupsEntry.setNew(n);
	}

	public boolean isCachedModel() {
		return _meetupsEntry.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_meetupsEntry.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _meetupsEntry.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_meetupsEntry.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _meetupsEntry.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetupsEntry.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetupsEntry.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _meetupsEntry.clone();
	}

	public int compareTo(
		com.liferay.socialnetworking.model.MeetupsEntry meetupsEntry) {
		return _meetupsEntry.compareTo(meetupsEntry);
	}

	public int hashCode() {
		return _meetupsEntry.hashCode();
	}

	public java.lang.String toString() {
		return _meetupsEntry.toString();
	}

	public java.lang.String toXmlString() {
		return _meetupsEntry.toXmlString();
	}

	public MeetupsEntry getWrappedMeetupsEntry() {
		return _meetupsEntry;
	}

	private MeetupsEntry _meetupsEntry;
}