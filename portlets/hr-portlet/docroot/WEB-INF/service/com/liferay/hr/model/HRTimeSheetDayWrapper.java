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
 * This class is a wrapper for {@link HRTimeSheetDay}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRTimeSheetDay
 * @generated
 */
public class HRTimeSheetDayWrapper implements HRTimeSheetDay {
	public HRTimeSheetDayWrapper(HRTimeSheetDay hrTimeSheetDay) {
		_hrTimeSheetDay = hrTimeSheetDay;
	}

	public Class<?> getModelClass() {
		return HRTimeSheetDay.class;
	}

	public String getModelClassName() {
		return HRTimeSheetDay.class.getName();
	}

	/**
	* Returns the primary key of this h r time sheet day.
	*
	* @return the primary key of this h r time sheet day
	*/
	public long getPrimaryKey() {
		return _hrTimeSheetDay.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time sheet day.
	*
	* @param primaryKey the primary key of this h r time sheet day
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrTimeSheetDay.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr time sheet day ID of this h r time sheet day.
	*
	* @return the hr time sheet day ID of this h r time sheet day
	*/
	public long getHrTimeSheetDayId() {
		return _hrTimeSheetDay.getHrTimeSheetDayId();
	}

	/**
	* Sets the hr time sheet day ID of this h r time sheet day.
	*
	* @param hrTimeSheetDayId the hr time sheet day ID of this h r time sheet day
	*/
	public void setHrTimeSheetDayId(long hrTimeSheetDayId) {
		_hrTimeSheetDay.setHrTimeSheetDayId(hrTimeSheetDayId);
	}

	/**
	* Returns the group ID of this h r time sheet day.
	*
	* @return the group ID of this h r time sheet day
	*/
	public long getGroupId() {
		return _hrTimeSheetDay.getGroupId();
	}

	/**
	* Sets the group ID of this h r time sheet day.
	*
	* @param groupId the group ID of this h r time sheet day
	*/
	public void setGroupId(long groupId) {
		_hrTimeSheetDay.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r time sheet day.
	*
	* @return the company ID of this h r time sheet day
	*/
	public long getCompanyId() {
		return _hrTimeSheetDay.getCompanyId();
	}

	/**
	* Sets the company ID of this h r time sheet day.
	*
	* @param companyId the company ID of this h r time sheet day
	*/
	public void setCompanyId(long companyId) {
		_hrTimeSheetDay.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r time sheet day.
	*
	* @return the user ID of this h r time sheet day
	*/
	public long getUserId() {
		return _hrTimeSheetDay.getUserId();
	}

	/**
	* Sets the user ID of this h r time sheet day.
	*
	* @param userId the user ID of this h r time sheet day
	*/
	public void setUserId(long userId) {
		_hrTimeSheetDay.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r time sheet day.
	*
	* @return the user uuid of this h r time sheet day
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeSheetDay.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r time sheet day.
	*
	* @param userUuid the user uuid of this h r time sheet day
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTimeSheetDay.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r time sheet day.
	*
	* @return the user name of this h r time sheet day
	*/
	public java.lang.String getUserName() {
		return _hrTimeSheetDay.getUserName();
	}

	/**
	* Sets the user name of this h r time sheet day.
	*
	* @param userName the user name of this h r time sheet day
	*/
	public void setUserName(java.lang.String userName) {
		_hrTimeSheetDay.setUserName(userName);
	}

	/**
	* Returns the create date of this h r time sheet day.
	*
	* @return the create date of this h r time sheet day
	*/
	public java.util.Date getCreateDate() {
		return _hrTimeSheetDay.getCreateDate();
	}

	/**
	* Sets the create date of this h r time sheet day.
	*
	* @param createDate the create date of this h r time sheet day
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTimeSheetDay.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r time sheet day.
	*
	* @return the modified date of this h r time sheet day
	*/
	public java.util.Date getModifiedDate() {
		return _hrTimeSheetDay.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r time sheet day.
	*
	* @param modifiedDate the modified date of this h r time sheet day
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTimeSheetDay.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the hr time sheet ID of this h r time sheet day.
	*
	* @return the hr time sheet ID of this h r time sheet day
	*/
	public long getHrTimeSheetId() {
		return _hrTimeSheetDay.getHrTimeSheetId();
	}

	/**
	* Sets the hr time sheet ID of this h r time sheet day.
	*
	* @param hrTimeSheetId the hr time sheet ID of this h r time sheet day
	*/
	public void setHrTimeSheetId(long hrTimeSheetId) {
		_hrTimeSheetDay.setHrTimeSheetId(hrTimeSheetId);
	}

	/**
	* Returns the hr user ID of this h r time sheet day.
	*
	* @return the hr user ID of this h r time sheet day
	*/
	public long getHrUserId() {
		return _hrTimeSheetDay.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r time sheet day.
	*
	* @param hrUserId the hr user ID of this h r time sheet day
	*/
	public void setHrUserId(long hrUserId) {
		_hrTimeSheetDay.setHrUserId(hrUserId);
	}

	/**
	* Returns the hr user uuid of this h r time sheet day.
	*
	* @return the hr user uuid of this h r time sheet day
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeSheetDay.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r time sheet day.
	*
	* @param hrUserUuid the hr user uuid of this h r time sheet day
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrTimeSheetDay.setHrUserUuid(hrUserUuid);
	}

	/**
	* Returns the day of year of this h r time sheet day.
	*
	* @return the day of year of this h r time sheet day
	*/
	public int getDayOfYear() {
		return _hrTimeSheetDay.getDayOfYear();
	}

	/**
	* Sets the day of year of this h r time sheet day.
	*
	* @param dayOfYear the day of year of this h r time sheet day
	*/
	public void setDayOfYear(int dayOfYear) {
		_hrTimeSheetDay.setDayOfYear(dayOfYear);
	}

	/**
	* Returns the year of this h r time sheet day.
	*
	* @return the year of this h r time sheet day
	*/
	public int getYear() {
		return _hrTimeSheetDay.getYear();
	}

	/**
	* Sets the year of this h r time sheet day.
	*
	* @param year the year of this h r time sheet day
	*/
	public void setYear(int year) {
		_hrTimeSheetDay.setYear(year);
	}

	/**
	* Returns the hours of this h r time sheet day.
	*
	* @return the hours of this h r time sheet day
	*/
	public double getHours() {
		return _hrTimeSheetDay.getHours();
	}

	/**
	* Sets the hours of this h r time sheet day.
	*
	* @param hours the hours of this h r time sheet day
	*/
	public void setHours(double hours) {
		_hrTimeSheetDay.setHours(hours);
	}

	public boolean isNew() {
		return _hrTimeSheetDay.isNew();
	}

	public void setNew(boolean n) {
		_hrTimeSheetDay.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTimeSheetDay.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTimeSheetDay.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTimeSheetDay.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTimeSheetDay.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTimeSheetDay.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrTimeSheetDay.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeSheetDay.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeSheetDay.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRTimeSheetDayWrapper((HRTimeSheetDay)_hrTimeSheetDay.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTimeSheetDay hrTimeSheetDay) {
		return _hrTimeSheetDay.compareTo(hrTimeSheetDay);
	}

	@Override
	public int hashCode() {
		return _hrTimeSheetDay.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRTimeSheetDay> toCacheModel() {
		return _hrTimeSheetDay.toCacheModel();
	}

	public com.liferay.hr.model.HRTimeSheetDay toEscapedModel() {
		return new HRTimeSheetDayWrapper(_hrTimeSheetDay.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrTimeSheetDay.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTimeSheetDay.toXmlString();
	}

	public HRTimeSheetDay getWrappedHRTimeSheetDay() {
		return _hrTimeSheetDay;
	}

	public void resetOriginalValues() {
		_hrTimeSheetDay.resetOriginalValues();
	}

	private HRTimeSheetDay _hrTimeSheetDay;
}