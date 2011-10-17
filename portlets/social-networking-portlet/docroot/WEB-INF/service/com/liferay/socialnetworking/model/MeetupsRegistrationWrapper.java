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
 * This class is a wrapper for {@link MeetupsRegistration}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MeetupsRegistration
 * @generated
 */
public class MeetupsRegistrationWrapper implements MeetupsRegistration,
	ModelWrapper<MeetupsRegistration> {
	public MeetupsRegistrationWrapper(MeetupsRegistration meetupsRegistration) {
		_meetupsRegistration = meetupsRegistration;
	}

	public Class<?> getModelClass() {
		return MeetupsRegistration.class;
	}

	public String getModelClassName() {
		return MeetupsRegistration.class.getName();
	}

	/**
	* Returns the primary key of this meetups registration.
	*
	* @return the primary key of this meetups registration
	*/
	public long getPrimaryKey() {
		return _meetupsRegistration.getPrimaryKey();
	}

	/**
	* Sets the primary key of this meetups registration.
	*
	* @param primaryKey the primary key of this meetups registration
	*/
	public void setPrimaryKey(long primaryKey) {
		_meetupsRegistration.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the meetups registration ID of this meetups registration.
	*
	* @return the meetups registration ID of this meetups registration
	*/
	public long getMeetupsRegistrationId() {
		return _meetupsRegistration.getMeetupsRegistrationId();
	}

	/**
	* Sets the meetups registration ID of this meetups registration.
	*
	* @param meetupsRegistrationId the meetups registration ID of this meetups registration
	*/
	public void setMeetupsRegistrationId(long meetupsRegistrationId) {
		_meetupsRegistration.setMeetupsRegistrationId(meetupsRegistrationId);
	}

	/**
	* Returns the company ID of this meetups registration.
	*
	* @return the company ID of this meetups registration
	*/
	public long getCompanyId() {
		return _meetupsRegistration.getCompanyId();
	}

	/**
	* Sets the company ID of this meetups registration.
	*
	* @param companyId the company ID of this meetups registration
	*/
	public void setCompanyId(long companyId) {
		_meetupsRegistration.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this meetups registration.
	*
	* @return the user ID of this meetups registration
	*/
	public long getUserId() {
		return _meetupsRegistration.getUserId();
	}

	/**
	* Sets the user ID of this meetups registration.
	*
	* @param userId the user ID of this meetups registration
	*/
	public void setUserId(long userId) {
		_meetupsRegistration.setUserId(userId);
	}

	/**
	* Returns the user uuid of this meetups registration.
	*
	* @return the user uuid of this meetups registration
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _meetupsRegistration.getUserUuid();
	}

	/**
	* Sets the user uuid of this meetups registration.
	*
	* @param userUuid the user uuid of this meetups registration
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_meetupsRegistration.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this meetups registration.
	*
	* @return the user name of this meetups registration
	*/
	public java.lang.String getUserName() {
		return _meetupsRegistration.getUserName();
	}

	/**
	* Sets the user name of this meetups registration.
	*
	* @param userName the user name of this meetups registration
	*/
	public void setUserName(java.lang.String userName) {
		_meetupsRegistration.setUserName(userName);
	}

	/**
	* Returns the create date of this meetups registration.
	*
	* @return the create date of this meetups registration
	*/
	public java.util.Date getCreateDate() {
		return _meetupsRegistration.getCreateDate();
	}

	/**
	* Sets the create date of this meetups registration.
	*
	* @param createDate the create date of this meetups registration
	*/
	public void setCreateDate(java.util.Date createDate) {
		_meetupsRegistration.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this meetups registration.
	*
	* @return the modified date of this meetups registration
	*/
	public java.util.Date getModifiedDate() {
		return _meetupsRegistration.getModifiedDate();
	}

	/**
	* Sets the modified date of this meetups registration.
	*
	* @param modifiedDate the modified date of this meetups registration
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_meetupsRegistration.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the meetups entry ID of this meetups registration.
	*
	* @return the meetups entry ID of this meetups registration
	*/
	public long getMeetupsEntryId() {
		return _meetupsRegistration.getMeetupsEntryId();
	}

	/**
	* Sets the meetups entry ID of this meetups registration.
	*
	* @param meetupsEntryId the meetups entry ID of this meetups registration
	*/
	public void setMeetupsEntryId(long meetupsEntryId) {
		_meetupsRegistration.setMeetupsEntryId(meetupsEntryId);
	}

	/**
	* Returns the status of this meetups registration.
	*
	* @return the status of this meetups registration
	*/
	public int getStatus() {
		return _meetupsRegistration.getStatus();
	}

	/**
	* Sets the status of this meetups registration.
	*
	* @param status the status of this meetups registration
	*/
	public void setStatus(int status) {
		_meetupsRegistration.setStatus(status);
	}

	/**
	* Returns the comments of this meetups registration.
	*
	* @return the comments of this meetups registration
	*/
	public java.lang.String getComments() {
		return _meetupsRegistration.getComments();
	}

	/**
	* Sets the comments of this meetups registration.
	*
	* @param comments the comments of this meetups registration
	*/
	public void setComments(java.lang.String comments) {
		_meetupsRegistration.setComments(comments);
	}

	public boolean isNew() {
		return _meetupsRegistration.isNew();
	}

	public void setNew(boolean n) {
		_meetupsRegistration.setNew(n);
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

	public java.io.Serializable getPrimaryKeyObj() {
		return _meetupsRegistration.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_meetupsRegistration.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _meetupsRegistration.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_meetupsRegistration.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new MeetupsRegistrationWrapper((MeetupsRegistration)_meetupsRegistration.clone());
	}

	public int compareTo(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration) {
		return _meetupsRegistration.compareTo(meetupsRegistration);
	}

	@Override
	public int hashCode() {
		return _meetupsRegistration.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.socialnetworking.model.MeetupsRegistration> toCacheModel() {
		return _meetupsRegistration.toCacheModel();
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration toEscapedModel() {
		return new MeetupsRegistrationWrapper(_meetupsRegistration.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _meetupsRegistration.toString();
	}

	public java.lang.String toXmlString() {
		return _meetupsRegistration.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_meetupsRegistration.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public MeetupsRegistration getWrappedMeetupsRegistration() {
		return _meetupsRegistration;
	}

	public MeetupsRegistration getWrappedModel() {
		return _meetupsRegistration;
	}

	public void resetOriginalValues() {
		_meetupsRegistration.resetOriginalValues();
	}

	private MeetupsRegistration _meetupsRegistration;
}