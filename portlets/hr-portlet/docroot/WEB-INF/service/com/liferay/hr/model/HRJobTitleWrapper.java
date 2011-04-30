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

package com.liferay.hr.model;

/**
 * <p>
 * This class is a wrapper for {@link HRJobTitle}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRJobTitle
 * @generated
 */
public class HRJobTitleWrapper implements HRJobTitle {
	public HRJobTitleWrapper(HRJobTitle hrJobTitle) {
		_hrJobTitle = hrJobTitle;
	}

	public Class<?> getModelClass() {
		return HRJobTitle.class;
	}

	public String getModelClassName() {
		return HRJobTitle.class.getName();
	}

	/**
	* Gets the primary key of this h r job title.
	*
	* @return the primary key of this h r job title
	*/
	public long getPrimaryKey() {
		return _hrJobTitle.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r job title
	*
	* @param pk the primary key of this h r job title
	*/
	public void setPrimaryKey(long pk) {
		_hrJobTitle.setPrimaryKey(pk);
	}

	/**
	* Gets the hr job title ID of this h r job title.
	*
	* @return the hr job title ID of this h r job title
	*/
	public long getHrJobTitleId() {
		return _hrJobTitle.getHrJobTitleId();
	}

	/**
	* Sets the hr job title ID of this h r job title.
	*
	* @param hrJobTitleId the hr job title ID of this h r job title
	*/
	public void setHrJobTitleId(long hrJobTitleId) {
		_hrJobTitle.setHrJobTitleId(hrJobTitleId);
	}

	/**
	* Gets the group ID of this h r job title.
	*
	* @return the group ID of this h r job title
	*/
	public long getGroupId() {
		return _hrJobTitle.getGroupId();
	}

	/**
	* Sets the group ID of this h r job title.
	*
	* @param groupId the group ID of this h r job title
	*/
	public void setGroupId(long groupId) {
		_hrJobTitle.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r job title.
	*
	* @return the company ID of this h r job title
	*/
	public long getCompanyId() {
		return _hrJobTitle.getCompanyId();
	}

	/**
	* Sets the company ID of this h r job title.
	*
	* @param companyId the company ID of this h r job title
	*/
	public void setCompanyId(long companyId) {
		_hrJobTitle.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r job title.
	*
	* @return the user ID of this h r job title
	*/
	public long getUserId() {
		return _hrJobTitle.getUserId();
	}

	/**
	* Sets the user ID of this h r job title.
	*
	* @param userId the user ID of this h r job title
	*/
	public void setUserId(long userId) {
		_hrJobTitle.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r job title.
	*
	* @return the user uuid of this h r job title
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrJobTitle.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r job title.
	*
	* @param userUuid the user uuid of this h r job title
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrJobTitle.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r job title.
	*
	* @return the user name of this h r job title
	*/
	public java.lang.String getUserName() {
		return _hrJobTitle.getUserName();
	}

	/**
	* Sets the user name of this h r job title.
	*
	* @param userName the user name of this h r job title
	*/
	public void setUserName(java.lang.String userName) {
		_hrJobTitle.setUserName(userName);
	}

	/**
	* Gets the create date of this h r job title.
	*
	* @return the create date of this h r job title
	*/
	public java.util.Date getCreateDate() {
		return _hrJobTitle.getCreateDate();
	}

	/**
	* Sets the create date of this h r job title.
	*
	* @param createDate the create date of this h r job title
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrJobTitle.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r job title.
	*
	* @return the modified date of this h r job title
	*/
	public java.util.Date getModifiedDate() {
		return _hrJobTitle.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r job title.
	*
	* @param modifiedDate the modified date of this h r job title
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrJobTitle.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the name of this h r job title.
	*
	* @return the name of this h r job title
	*/
	public java.lang.String getName() {
		return _hrJobTitle.getName();
	}

	/**
	* Sets the name of this h r job title.
	*
	* @param name the name of this h r job title
	*/
	public void setName(java.lang.String name) {
		_hrJobTitle.setName(name);
	}

	/**
	* Gets the description of this h r job title.
	*
	* @return the description of this h r job title
	*/
	public java.lang.String getDescription() {
		return _hrJobTitle.getDescription();
	}

	/**
	* Sets the description of this h r job title.
	*
	* @param description the description of this h r job title
	*/
	public void setDescription(java.lang.String description) {
		_hrJobTitle.setDescription(description);
	}

	public boolean isNew() {
		return _hrJobTitle.isNew();
	}

	public void setNew(boolean n) {
		_hrJobTitle.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrJobTitle.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrJobTitle.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrJobTitle.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrJobTitle.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrJobTitle.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrJobTitle.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrJobTitle.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRJobTitleWrapper((HRJobTitle)_hrJobTitle.clone());
	}

	public int compareTo(com.liferay.hr.model.HRJobTitle hrJobTitle) {
		return _hrJobTitle.compareTo(hrJobTitle);
	}

	public int hashCode() {
		return _hrJobTitle.hashCode();
	}

	public com.liferay.hr.model.HRJobTitle toEscapedModel() {
		return new HRJobTitleWrapper(_hrJobTitle.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrJobTitle.toString();
	}

	public java.lang.String toXmlString() {
		return _hrJobTitle.toXmlString();
	}

	public HRJobTitle getWrappedHRJobTitle() {
		return _hrJobTitle;
	}

	public void resetOriginalValues() {
		_hrJobTitle.resetOriginalValues();
	}

	private HRJobTitle _hrJobTitle;
}