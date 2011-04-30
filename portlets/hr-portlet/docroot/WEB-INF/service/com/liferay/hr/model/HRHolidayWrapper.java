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
 * This class is a wrapper for {@link HRHoliday}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRHoliday
 * @generated
 */
public class HRHolidayWrapper implements HRHoliday {
	public HRHolidayWrapper(HRHoliday hrHoliday) {
		_hrHoliday = hrHoliday;
	}

	public Class<?> getModelClass() {
		return HRHoliday.class;
	}

	public String getModelClassName() {
		return HRHoliday.class.getName();
	}

	/**
	* Gets the primary key of this h r holiday.
	*
	* @return the primary key of this h r holiday
	*/
	public long getPrimaryKey() {
		return _hrHoliday.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r holiday
	*
	* @param pk the primary key of this h r holiday
	*/
	public void setPrimaryKey(long pk) {
		_hrHoliday.setPrimaryKey(pk);
	}

	/**
	* Gets the hr holiday ID of this h r holiday.
	*
	* @return the hr holiday ID of this h r holiday
	*/
	public long getHrHolidayId() {
		return _hrHoliday.getHrHolidayId();
	}

	/**
	* Sets the hr holiday ID of this h r holiday.
	*
	* @param hrHolidayId the hr holiday ID of this h r holiday
	*/
	public void setHrHolidayId(long hrHolidayId) {
		_hrHoliday.setHrHolidayId(hrHolidayId);
	}

	/**
	* Gets the group ID of this h r holiday.
	*
	* @return the group ID of this h r holiday
	*/
	public long getGroupId() {
		return _hrHoliday.getGroupId();
	}

	/**
	* Sets the group ID of this h r holiday.
	*
	* @param groupId the group ID of this h r holiday
	*/
	public void setGroupId(long groupId) {
		_hrHoliday.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r holiday.
	*
	* @return the company ID of this h r holiday
	*/
	public long getCompanyId() {
		return _hrHoliday.getCompanyId();
	}

	/**
	* Sets the company ID of this h r holiday.
	*
	* @param companyId the company ID of this h r holiday
	*/
	public void setCompanyId(long companyId) {
		_hrHoliday.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r holiday.
	*
	* @return the user ID of this h r holiday
	*/
	public long getUserId() {
		return _hrHoliday.getUserId();
	}

	/**
	* Sets the user ID of this h r holiday.
	*
	* @param userId the user ID of this h r holiday
	*/
	public void setUserId(long userId) {
		_hrHoliday.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r holiday.
	*
	* @return the user uuid of this h r holiday
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrHoliday.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r holiday.
	*
	* @param userUuid the user uuid of this h r holiday
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrHoliday.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r holiday.
	*
	* @return the user name of this h r holiday
	*/
	public java.lang.String getUserName() {
		return _hrHoliday.getUserName();
	}

	/**
	* Sets the user name of this h r holiday.
	*
	* @param userName the user name of this h r holiday
	*/
	public void setUserName(java.lang.String userName) {
		_hrHoliday.setUserName(userName);
	}

	/**
	* Gets the create date of this h r holiday.
	*
	* @return the create date of this h r holiday
	*/
	public java.util.Date getCreateDate() {
		return _hrHoliday.getCreateDate();
	}

	/**
	* Sets the create date of this h r holiday.
	*
	* @param createDate the create date of this h r holiday
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrHoliday.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r holiday.
	*
	* @return the modified date of this h r holiday
	*/
	public java.util.Date getModifiedDate() {
		return _hrHoliday.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r holiday.
	*
	* @param modifiedDate the modified date of this h r holiday
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrHoliday.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the name of this h r holiday.
	*
	* @return the name of this h r holiday
	*/
	public java.lang.String getName() {
		return _hrHoliday.getName();
	}

	/**
	* Sets the name of this h r holiday.
	*
	* @param name the name of this h r holiday
	*/
	public void setName(java.lang.String name) {
		_hrHoliday.setName(name);
	}

	/**
	* Gets the description of this h r holiday.
	*
	* @return the description of this h r holiday
	*/
	public java.lang.String getDescription() {
		return _hrHoliday.getDescription();
	}

	/**
	* Sets the description of this h r holiday.
	*
	* @param description the description of this h r holiday
	*/
	public void setDescription(java.lang.String description) {
		_hrHoliday.setDescription(description);
	}

	/**
	* Gets the day of year of this h r holiday.
	*
	* @return the day of year of this h r holiday
	*/
	public int getDayOfYear() {
		return _hrHoliday.getDayOfYear();
	}

	/**
	* Sets the day of year of this h r holiday.
	*
	* @param dayOfYear the day of year of this h r holiday
	*/
	public void setDayOfYear(int dayOfYear) {
		_hrHoliday.setDayOfYear(dayOfYear);
	}

	/**
	* Gets the year of this h r holiday.
	*
	* @return the year of this h r holiday
	*/
	public int getYear() {
		return _hrHoliday.getYear();
	}

	/**
	* Sets the year of this h r holiday.
	*
	* @param year the year of this h r holiday
	*/
	public void setYear(int year) {
		_hrHoliday.setYear(year);
	}

	/**
	* Gets the paid of this h r holiday.
	*
	* @return the paid of this h r holiday
	*/
	public boolean getPaid() {
		return _hrHoliday.getPaid();
	}

	/**
	* Determines if this h r holiday is paid.
	*
	* @return <code>true</code> if this h r holiday is paid; <code>false</code> otherwise
	*/
	public boolean isPaid() {
		return _hrHoliday.isPaid();
	}

	/**
	* Sets whether this h r holiday is paid.
	*
	* @param paid the paid of this h r holiday
	*/
	public void setPaid(boolean paid) {
		_hrHoliday.setPaid(paid);
	}

	public boolean isNew() {
		return _hrHoliday.isNew();
	}

	public void setNew(boolean n) {
		_hrHoliday.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrHoliday.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrHoliday.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrHoliday.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrHoliday.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrHoliday.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrHoliday.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrHoliday.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRHolidayWrapper((HRHoliday)_hrHoliday.clone());
	}

	public int compareTo(com.liferay.hr.model.HRHoliday hrHoliday) {
		return _hrHoliday.compareTo(hrHoliday);
	}

	public int hashCode() {
		return _hrHoliday.hashCode();
	}

	public com.liferay.hr.model.HRHoliday toEscapedModel() {
		return new HRHolidayWrapper(_hrHoliday.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrHoliday.toString();
	}

	public java.lang.String toXmlString() {
		return _hrHoliday.toXmlString();
	}

	public HRHoliday getWrappedHRHoliday() {
		return _hrHoliday;
	}

	public void resetOriginalValues() {
		_hrHoliday.resetOriginalValues();
	}

	private HRHoliday _hrHoliday;
}