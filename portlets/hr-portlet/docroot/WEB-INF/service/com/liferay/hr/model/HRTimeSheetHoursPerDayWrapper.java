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
 * This class is a wrapper for {@link HRTimeSheetHoursPerDay}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRTimeSheetHoursPerDay
 * @generated
 */
public class HRTimeSheetHoursPerDayWrapper implements HRTimeSheetHoursPerDay {
	public HRTimeSheetHoursPerDayWrapper(
		HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay) {
		_hrTimeSheetHoursPerDay = hrTimeSheetHoursPerDay;
	}

	public Class<?> getModelClass() {
		return HRTimeSheetHoursPerDay.class;
	}

	public String getModelClassName() {
		return HRTimeSheetHoursPerDay.class.getName();
	}

	/**
	* Gets the primary key of this h r time sheet hours per day.
	*
	* @return the primary key of this h r time sheet hours per day
	*/
	public long getPrimaryKey() {
		return _hrTimeSheetHoursPerDay.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time sheet hours per day
	*
	* @param pk the primary key of this h r time sheet hours per day
	*/
	public void setPrimaryKey(long pk) {
		_hrTimeSheetHoursPerDay.setPrimaryKey(pk);
	}

	/**
	* Gets the hr time sheet hours per day of this h r time sheet hours per day.
	*
	* @return the hr time sheet hours per day of this h r time sheet hours per day
	*/
	public long getHrTimeSheetHoursPerDay() {
		return _hrTimeSheetHoursPerDay.getHrTimeSheetHoursPerDay();
	}

	/**
	* Sets the hr time sheet hours per day of this h r time sheet hours per day.
	*
	* @param hrTimeSheetHoursPerDay the hr time sheet hours per day of this h r time sheet hours per day
	*/
	public void setHrTimeSheetHoursPerDay(long hrTimeSheetHoursPerDay) {
		_hrTimeSheetHoursPerDay.setHrTimeSheetHoursPerDay(hrTimeSheetHoursPerDay);
	}

	/**
	* Gets the group ID of this h r time sheet hours per day.
	*
	* @return the group ID of this h r time sheet hours per day
	*/
	public long getGroupId() {
		return _hrTimeSheetHoursPerDay.getGroupId();
	}

	/**
	* Sets the group ID of this h r time sheet hours per day.
	*
	* @param groupId the group ID of this h r time sheet hours per day
	*/
	public void setGroupId(long groupId) {
		_hrTimeSheetHoursPerDay.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r time sheet hours per day.
	*
	* @return the company ID of this h r time sheet hours per day
	*/
	public long getCompanyId() {
		return _hrTimeSheetHoursPerDay.getCompanyId();
	}

	/**
	* Sets the company ID of this h r time sheet hours per day.
	*
	* @param companyId the company ID of this h r time sheet hours per day
	*/
	public void setCompanyId(long companyId) {
		_hrTimeSheetHoursPerDay.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r time sheet hours per day.
	*
	* @return the user ID of this h r time sheet hours per day
	*/
	public long getUserId() {
		return _hrTimeSheetHoursPerDay.getUserId();
	}

	/**
	* Sets the user ID of this h r time sheet hours per day.
	*
	* @param userId the user ID of this h r time sheet hours per day
	*/
	public void setUserId(long userId) {
		_hrTimeSheetHoursPerDay.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r time sheet hours per day.
	*
	* @return the user uuid of this h r time sheet hours per day
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeSheetHoursPerDay.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r time sheet hours per day.
	*
	* @param userUuid the user uuid of this h r time sheet hours per day
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTimeSheetHoursPerDay.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r time sheet hours per day.
	*
	* @return the user name of this h r time sheet hours per day
	*/
	public java.lang.String getUserName() {
		return _hrTimeSheetHoursPerDay.getUserName();
	}

	/**
	* Sets the user name of this h r time sheet hours per day.
	*
	* @param userName the user name of this h r time sheet hours per day
	*/
	public void setUserName(java.lang.String userName) {
		_hrTimeSheetHoursPerDay.setUserName(userName);
	}

	/**
	* Gets the create date of this h r time sheet hours per day.
	*
	* @return the create date of this h r time sheet hours per day
	*/
	public java.util.Date getCreateDate() {
		return _hrTimeSheetHoursPerDay.getCreateDate();
	}

	/**
	* Sets the create date of this h r time sheet hours per day.
	*
	* @param createDate the create date of this h r time sheet hours per day
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTimeSheetHoursPerDay.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r time sheet hours per day.
	*
	* @return the modified date of this h r time sheet hours per day
	*/
	public java.util.Date getModifiedDate() {
		return _hrTimeSheetHoursPerDay.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r time sheet hours per day.
	*
	* @param modifiedDate the modified date of this h r time sheet hours per day
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTimeSheetHoursPerDay.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr office ID of this h r time sheet hours per day.
	*
	* @return the hr office ID of this h r time sheet hours per day
	*/
	public long getHrOfficeId() {
		return _hrTimeSheetHoursPerDay.getHrOfficeId();
	}

	/**
	* Sets the hr office ID of this h r time sheet hours per day.
	*
	* @param hrOfficeId the hr office ID of this h r time sheet hours per day
	*/
	public void setHrOfficeId(long hrOfficeId) {
		_hrTimeSheetHoursPerDay.setHrOfficeId(hrOfficeId);
	}

	/**
	* Gets the hours per day of this h r time sheet hours per day.
	*
	* @return the hours per day of this h r time sheet hours per day
	*/
	public double getHoursPerDay() {
		return _hrTimeSheetHoursPerDay.getHoursPerDay();
	}

	/**
	* Sets the hours per day of this h r time sheet hours per day.
	*
	* @param hoursPerDay the hours per day of this h r time sheet hours per day
	*/
	public void setHoursPerDay(double hoursPerDay) {
		_hrTimeSheetHoursPerDay.setHoursPerDay(hoursPerDay);
	}

	public boolean isNew() {
		return _hrTimeSheetHoursPerDay.isNew();
	}

	public void setNew(boolean n) {
		_hrTimeSheetHoursPerDay.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTimeSheetHoursPerDay.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTimeSheetHoursPerDay.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTimeSheetHoursPerDay.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTimeSheetHoursPerDay.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTimeSheetHoursPerDay.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeSheetHoursPerDay.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeSheetHoursPerDay.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRTimeSheetHoursPerDayWrapper((HRTimeSheetHoursPerDay)_hrTimeSheetHoursPerDay.clone());
	}

	public int compareTo(HRTimeSheetHoursPerDay hrTimeSheetHoursPerDay) {
		return _hrTimeSheetHoursPerDay.compareTo(hrTimeSheetHoursPerDay);
	}

	public int hashCode() {
		return _hrTimeSheetHoursPerDay.hashCode();
	}

	public HRTimeSheetHoursPerDay toEscapedModel() {
		return new HRTimeSheetHoursPerDayWrapper(_hrTimeSheetHoursPerDay.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrTimeSheetHoursPerDay.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTimeSheetHoursPerDay.toXmlString();
	}

	public HRTimeSheetHoursPerDay getWrappedHRTimeSheetHoursPerDay() {
		return _hrTimeSheetHoursPerDay;
	}

	public void resetOriginalValues() {
		_hrTimeSheetHoursPerDay.resetOriginalValues();
	}

	private HRTimeSheetHoursPerDay _hrTimeSheetHoursPerDay;
}