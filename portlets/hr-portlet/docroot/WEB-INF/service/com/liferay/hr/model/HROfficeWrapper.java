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
 * This class is a wrapper for {@link HROffice}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HROffice
 * @generated
 */
public class HROfficeWrapper implements HROffice {
	public HROfficeWrapper(HROffice hrOffice) {
		_hrOffice = hrOffice;
	}

	public Class<?> getModelClass() {
		return HROffice.class;
	}

	public String getModelClassName() {
		return HROffice.class.getName();
	}

	/**
	* Gets the primary key of this h r office.
	*
	* @return the primary key of this h r office
	*/
	public long getPrimaryKey() {
		return _hrOffice.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r office
	*
	* @param pk the primary key of this h r office
	*/
	public void setPrimaryKey(long pk) {
		_hrOffice.setPrimaryKey(pk);
	}

	/**
	* Gets the hr office ID of this h r office.
	*
	* @return the hr office ID of this h r office
	*/
	public long getHrOfficeId() {
		return _hrOffice.getHrOfficeId();
	}

	/**
	* Sets the hr office ID of this h r office.
	*
	* @param hrOfficeId the hr office ID of this h r office
	*/
	public void setHrOfficeId(long hrOfficeId) {
		_hrOffice.setHrOfficeId(hrOfficeId);
	}

	/**
	* Gets the group ID of this h r office.
	*
	* @return the group ID of this h r office
	*/
	public long getGroupId() {
		return _hrOffice.getGroupId();
	}

	/**
	* Sets the group ID of this h r office.
	*
	* @param groupId the group ID of this h r office
	*/
	public void setGroupId(long groupId) {
		_hrOffice.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r office.
	*
	* @return the company ID of this h r office
	*/
	public long getCompanyId() {
		return _hrOffice.getCompanyId();
	}

	/**
	* Sets the company ID of this h r office.
	*
	* @param companyId the company ID of this h r office
	*/
	public void setCompanyId(long companyId) {
		_hrOffice.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r office.
	*
	* @return the user ID of this h r office
	*/
	public long getUserId() {
		return _hrOffice.getUserId();
	}

	/**
	* Sets the user ID of this h r office.
	*
	* @param userId the user ID of this h r office
	*/
	public void setUserId(long userId) {
		_hrOffice.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r office.
	*
	* @return the user uuid of this h r office
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrOffice.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r office.
	*
	* @param userUuid the user uuid of this h r office
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrOffice.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r office.
	*
	* @return the user name of this h r office
	*/
	public java.lang.String getUserName() {
		return _hrOffice.getUserName();
	}

	/**
	* Sets the user name of this h r office.
	*
	* @param userName the user name of this h r office
	*/
	public void setUserName(java.lang.String userName) {
		_hrOffice.setUserName(userName);
	}

	/**
	* Gets the create date of this h r office.
	*
	* @return the create date of this h r office
	*/
	public java.util.Date getCreateDate() {
		return _hrOffice.getCreateDate();
	}

	/**
	* Sets the create date of this h r office.
	*
	* @param createDate the create date of this h r office
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrOffice.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r office.
	*
	* @return the modified date of this h r office
	*/
	public java.util.Date getModifiedDate() {
		return _hrOffice.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r office.
	*
	* @param modifiedDate the modified date of this h r office
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrOffice.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the organization ID of this h r office.
	*
	* @return the organization ID of this h r office
	*/
	public long getOrganizationId() {
		return _hrOffice.getOrganizationId();
	}

	/**
	* Sets the organization ID of this h r office.
	*
	* @param organizationId the organization ID of this h r office
	*/
	public void setOrganizationId(long organizationId) {
		_hrOffice.setOrganizationId(organizationId);
	}

	public boolean isNew() {
		return _hrOffice.isNew();
	}

	public void setNew(boolean n) {
		_hrOffice.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrOffice.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrOffice.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrOffice.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrOffice.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrOffice.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrOffice.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrOffice.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HROfficeWrapper((HROffice)_hrOffice.clone());
	}

	public int compareTo(com.liferay.hr.model.HROffice hrOffice) {
		return _hrOffice.compareTo(hrOffice);
	}

	public int hashCode() {
		return _hrOffice.hashCode();
	}

	public com.liferay.hr.model.HROffice toEscapedModel() {
		return new HROfficeWrapper(_hrOffice.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrOffice.toString();
	}

	public java.lang.String toXmlString() {
		return _hrOffice.toXmlString();
	}

	public HROffice getWrappedHROffice() {
		return _hrOffice;
	}

	public void resetOriginalValues() {
		_hrOffice.resetOriginalValues();
	}

	private HROffice _hrOffice;
}