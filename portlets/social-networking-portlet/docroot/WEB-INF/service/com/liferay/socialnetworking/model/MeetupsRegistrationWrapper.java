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
 * This class is a wrapper for {@link MeetupsRegistration}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MeetupsRegistration
 * @generated
 */
public class MeetupsRegistrationWrapper implements MeetupsRegistration {
	public MeetupsRegistrationWrapper(MeetupsRegistration meetupsRegistration) {
		_meetupsRegistration = meetupsRegistration;
	}

	public long getPrimaryKey() {
		return _meetupsRegistration.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_meetupsRegistration.setPrimaryKey(pk);
	}

	public long getMeetupsRegistrationId() {
		return _meetupsRegistration.getMeetupsRegistrationId();
	}

	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		_meetupsRegistration.setMeetupsRegistrationId(meetupsRegistrationId);
	}

	public long getCompanyId() {
		return _meetupsRegistration.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_meetupsRegistration.setCompanyId(companyId);
	}

	public long getUserId() {
		return _meetupsRegistration.getUserId();
	}

	public void setUserId(long userId) {
		_meetupsRegistration.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsRegistration.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_meetupsRegistration.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _meetupsRegistration.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_meetupsRegistration.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _meetupsRegistration.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_meetupsRegistration.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _meetupsRegistration.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetupsRegistration.setModifiedDate(modifiedDate);
	}

	public long getMeetupsEntryId() {
		return _meetupsRegistration.getMeetupsEntryId();
	}

	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsRegistration.setMeetupsEntryId(meetupsEntryId);
	}

	public int getStatus() {
		return _meetupsRegistration.getStatus();
	}

	public void setStatus(int status) {
		_meetupsRegistration.setStatus(status);
	}

	public java.lang.String getComments() {
		return _meetupsRegistration.getComments();
	}

	public void setComments(java.lang.String comments) {
		_meetupsRegistration.setComments(comments);
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration toEscapedModel() {
		return _meetupsRegistration.toEscapedModel();
	}

	public boolean isNew() {
		return _meetupsRegistration.isNew();
	}

	public boolean setNew(boolean n) {
		return _meetupsRegistration.setNew(n);
	}

	public boolean isCachedModel() {
		return _meetupsRegistration.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_meetupsRegistration.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _meetupsRegistration.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_meetupsRegistration.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _meetupsRegistration.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetupsRegistration.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetupsRegistration.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _meetupsRegistration.clone();
	}

	public int compareTo(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration) {
		return _meetupsRegistration.compareTo(meetupsRegistration);
	}

	public int hashCode() {
		return _meetupsRegistration.hashCode();
	}

	public java.lang.String toString() {
		return _meetupsRegistration.toString();
	}

	public java.lang.String toXmlString() {
		return _meetupsRegistration.toXmlString();
	}

	public MeetupsRegistration getWrappedMeetupsRegistration() {
		return _meetupsRegistration;
	}

	private MeetupsRegistration _meetupsRegistration;
}