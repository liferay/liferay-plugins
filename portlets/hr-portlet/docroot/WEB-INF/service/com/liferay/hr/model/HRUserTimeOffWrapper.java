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
 * This class is a wrapper for {@link HRUserTimeOff}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRUserTimeOff
 * @generated
 */
public class HRUserTimeOffWrapper implements HRUserTimeOff {
	public HRUserTimeOffWrapper(HRUserTimeOff hrUserTimeOff) {
		_hrUserTimeOff = hrUserTimeOff;
	}

	public Class<?> getModelClass() {
		return HRUserTimeOff.class;
	}

	public String getModelClassName() {
		return HRUserTimeOff.class.getName();
	}

	/**
	* Gets the primary key of this h r user time off.
	*
	* @return the primary key of this h r user time off
	*/
	public long getPrimaryKey() {
		return _hrUserTimeOff.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r user time off
	*
	* @param primaryKey the primary key of this h r user time off
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrUserTimeOff.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the hr user time off ID of this h r user time off.
	*
	* @return the hr user time off ID of this h r user time off
	*/
	public long getHrUserTimeOffId() {
		return _hrUserTimeOff.getHrUserTimeOffId();
	}

	/**
	* Sets the hr user time off ID of this h r user time off.
	*
	* @param hrUserTimeOffId the hr user time off ID of this h r user time off
	*/
	public void setHrUserTimeOffId(long hrUserTimeOffId) {
		_hrUserTimeOff.setHrUserTimeOffId(hrUserTimeOffId);
	}

	/**
	* Gets the group ID of this h r user time off.
	*
	* @return the group ID of this h r user time off
	*/
	public long getGroupId() {
		return _hrUserTimeOff.getGroupId();
	}

	/**
	* Sets the group ID of this h r user time off.
	*
	* @param groupId the group ID of this h r user time off
	*/
	public void setGroupId(long groupId) {
		_hrUserTimeOff.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r user time off.
	*
	* @return the company ID of this h r user time off
	*/
	public long getCompanyId() {
		return _hrUserTimeOff.getCompanyId();
	}

	/**
	* Sets the company ID of this h r user time off.
	*
	* @param companyId the company ID of this h r user time off
	*/
	public void setCompanyId(long companyId) {
		_hrUserTimeOff.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r user time off.
	*
	* @return the user ID of this h r user time off
	*/
	public long getUserId() {
		return _hrUserTimeOff.getUserId();
	}

	/**
	* Sets the user ID of this h r user time off.
	*
	* @param userId the user ID of this h r user time off
	*/
	public void setUserId(long userId) {
		_hrUserTimeOff.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r user time off.
	*
	* @return the user uuid of this h r user time off
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserTimeOff.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r user time off.
	*
	* @param userUuid the user uuid of this h r user time off
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrUserTimeOff.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r user time off.
	*
	* @return the user name of this h r user time off
	*/
	public java.lang.String getUserName() {
		return _hrUserTimeOff.getUserName();
	}

	/**
	* Sets the user name of this h r user time off.
	*
	* @param userName the user name of this h r user time off
	*/
	public void setUserName(java.lang.String userName) {
		_hrUserTimeOff.setUserName(userName);
	}

	/**
	* Gets the create date of this h r user time off.
	*
	* @return the create date of this h r user time off
	*/
	public java.util.Date getCreateDate() {
		return _hrUserTimeOff.getCreateDate();
	}

	/**
	* Sets the create date of this h r user time off.
	*
	* @param createDate the create date of this h r user time off
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrUserTimeOff.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r user time off.
	*
	* @return the modified date of this h r user time off
	*/
	public java.util.Date getModifiedDate() {
		return _hrUserTimeOff.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r user time off.
	*
	* @param modifiedDate the modified date of this h r user time off
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrUserTimeOff.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr time off type ID of this h r user time off.
	*
	* @return the hr time off type ID of this h r user time off
	*/
	public long getHrTimeOffTypeId() {
		return _hrUserTimeOff.getHrTimeOffTypeId();
	}

	/**
	* Sets the hr time off type ID of this h r user time off.
	*
	* @param hrTimeOffTypeId the hr time off type ID of this h r user time off
	*/
	public void setHrTimeOffTypeId(long hrTimeOffTypeId) {
		_hrUserTimeOff.setHrTimeOffTypeId(hrTimeOffTypeId);
	}

	/**
	* Gets the hr user ID of this h r user time off.
	*
	* @return the hr user ID of this h r user time off
	*/
	public long getHrUserId() {
		return _hrUserTimeOff.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r user time off.
	*
	* @param hrUserId the hr user ID of this h r user time off
	*/
	public void setHrUserId(long hrUserId) {
		_hrUserTimeOff.setHrUserId(hrUserId);
	}

	/**
	* Gets the hr user uuid of this h r user time off.
	*
	* @return the hr user uuid of this h r user time off
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserTimeOff.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r user time off.
	*
	* @param hrUserUuid the hr user uuid of this h r user time off
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrUserTimeOff.setHrUserUuid(hrUserUuid);
	}

	/**
	* Gets the year of this h r user time off.
	*
	* @return the year of this h r user time off
	*/
	public int getYear() {
		return _hrUserTimeOff.getYear();
	}

	/**
	* Sets the year of this h r user time off.
	*
	* @param year the year of this h r user time off
	*/
	public void setYear(int year) {
		_hrUserTimeOff.setYear(year);
	}

	/**
	* Gets the hours allowed of this h r user time off.
	*
	* @return the hours allowed of this h r user time off
	*/
	public double getHoursAllowed() {
		return _hrUserTimeOff.getHoursAllowed();
	}

	/**
	* Sets the hours allowed of this h r user time off.
	*
	* @param hoursAllowed the hours allowed of this h r user time off
	*/
	public void setHoursAllowed(double hoursAllowed) {
		_hrUserTimeOff.setHoursAllowed(hoursAllowed);
	}

	/**
	* Gets the hours accrued of this h r user time off.
	*
	* @return the hours accrued of this h r user time off
	*/
	public double getHoursAccrued() {
		return _hrUserTimeOff.getHoursAccrued();
	}

	/**
	* Sets the hours accrued of this h r user time off.
	*
	* @param hoursAccrued the hours accrued of this h r user time off
	*/
	public void setHoursAccrued(double hoursAccrued) {
		_hrUserTimeOff.setHoursAccrued(hoursAccrued);
	}

	/**
	* Gets the hours carried over of this h r user time off.
	*
	* @return the hours carried over of this h r user time off
	*/
	public double getHoursCarriedOver() {
		return _hrUserTimeOff.getHoursCarriedOver();
	}

	/**
	* Sets the hours carried over of this h r user time off.
	*
	* @param hoursCarriedOver the hours carried over of this h r user time off
	*/
	public void setHoursCarriedOver(double hoursCarriedOver) {
		_hrUserTimeOff.setHoursCarriedOver(hoursCarriedOver);
	}

	/**
	* Gets the hours used of this h r user time off.
	*
	* @return the hours used of this h r user time off
	*/
	public double getHoursUsed() {
		return _hrUserTimeOff.getHoursUsed();
	}

	/**
	* Sets the hours used of this h r user time off.
	*
	* @param hoursUsed the hours used of this h r user time off
	*/
	public void setHoursUsed(double hoursUsed) {
		_hrUserTimeOff.setHoursUsed(hoursUsed);
	}

	public boolean isNew() {
		return _hrUserTimeOff.isNew();
	}

	public void setNew(boolean n) {
		_hrUserTimeOff.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrUserTimeOff.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrUserTimeOff.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrUserTimeOff.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrUserTimeOff.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrUserTimeOff.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrUserTimeOff.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrUserTimeOff.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrUserTimeOff.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRUserTimeOffWrapper((HRUserTimeOff)_hrUserTimeOff.clone());
	}

	public int compareTo(com.liferay.hr.model.HRUserTimeOff hrUserTimeOff) {
		return _hrUserTimeOff.compareTo(hrUserTimeOff);
	}

	public int hashCode() {
		return _hrUserTimeOff.hashCode();
	}

	public com.liferay.hr.model.HRUserTimeOff toEscapedModel() {
		return new HRUserTimeOffWrapper(_hrUserTimeOff.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrUserTimeOff.toString();
	}

	public java.lang.String toXmlString() {
		return _hrUserTimeOff.toXmlString();
	}

	public HRUserTimeOff getWrappedHRUserTimeOff() {
		return _hrUserTimeOff;
	}

	public void resetOriginalValues() {
		_hrUserTimeOff.resetOriginalValues();
	}

	private HRUserTimeOff _hrUserTimeOff;
}