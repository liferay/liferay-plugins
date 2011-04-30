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
 * This class is a wrapper for {@link HRTimeOff}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRTimeOff
 * @generated
 */
public class HRTimeOffWrapper implements HRTimeOff {
	public HRTimeOffWrapper(HRTimeOff hrTimeOff) {
		_hrTimeOff = hrTimeOff;
	}

	public Class<?> getModelClass() {
		return HRTimeOff.class;
	}

	public String getModelClassName() {
		return HRTimeOff.class.getName();
	}

	/**
	* Gets the primary key of this h r time off.
	*
	* @return the primary key of this h r time off
	*/
	public long getPrimaryKey() {
		return _hrTimeOff.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time off
	*
	* @param pk the primary key of this h r time off
	*/
	public void setPrimaryKey(long pk) {
		_hrTimeOff.setPrimaryKey(pk);
	}

	/**
	* Gets the hr time off ID of this h r time off.
	*
	* @return the hr time off ID of this h r time off
	*/
	public long getHrTimeOffId() {
		return _hrTimeOff.getHrTimeOffId();
	}

	/**
	* Sets the hr time off ID of this h r time off.
	*
	* @param hrTimeOffId the hr time off ID of this h r time off
	*/
	public void setHrTimeOffId(long hrTimeOffId) {
		_hrTimeOff.setHrTimeOffId(hrTimeOffId);
	}

	/**
	* Gets the group ID of this h r time off.
	*
	* @return the group ID of this h r time off
	*/
	public long getGroupId() {
		return _hrTimeOff.getGroupId();
	}

	/**
	* Sets the group ID of this h r time off.
	*
	* @param groupId the group ID of this h r time off
	*/
	public void setGroupId(long groupId) {
		_hrTimeOff.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r time off.
	*
	* @return the company ID of this h r time off
	*/
	public long getCompanyId() {
		return _hrTimeOff.getCompanyId();
	}

	/**
	* Sets the company ID of this h r time off.
	*
	* @param companyId the company ID of this h r time off
	*/
	public void setCompanyId(long companyId) {
		_hrTimeOff.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r time off.
	*
	* @return the user ID of this h r time off
	*/
	public long getUserId() {
		return _hrTimeOff.getUserId();
	}

	/**
	* Sets the user ID of this h r time off.
	*
	* @param userId the user ID of this h r time off
	*/
	public void setUserId(long userId) {
		_hrTimeOff.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r time off.
	*
	* @return the user uuid of this h r time off
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeOff.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r time off.
	*
	* @param userUuid the user uuid of this h r time off
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTimeOff.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r time off.
	*
	* @return the user name of this h r time off
	*/
	public java.lang.String getUserName() {
		return _hrTimeOff.getUserName();
	}

	/**
	* Sets the user name of this h r time off.
	*
	* @param userName the user name of this h r time off
	*/
	public void setUserName(java.lang.String userName) {
		_hrTimeOff.setUserName(userName);
	}

	/**
	* Gets the create date of this h r time off.
	*
	* @return the create date of this h r time off
	*/
	public java.util.Date getCreateDate() {
		return _hrTimeOff.getCreateDate();
	}

	/**
	* Sets the create date of this h r time off.
	*
	* @param createDate the create date of this h r time off
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTimeOff.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r time off.
	*
	* @return the modified date of this h r time off
	*/
	public java.util.Date getModifiedDate() {
		return _hrTimeOff.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r time off.
	*
	* @param modifiedDate the modified date of this h r time off
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTimeOff.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr time off type ID of this h r time off.
	*
	* @return the hr time off type ID of this h r time off
	*/
	public long getHrTimeOffTypeId() {
		return _hrTimeOff.getHrTimeOffTypeId();
	}

	/**
	* Sets the hr time off type ID of this h r time off.
	*
	* @param hrTimeOffTypeId the hr time off type ID of this h r time off
	*/
	public void setHrTimeOffTypeId(long hrTimeOffTypeId) {
		_hrTimeOff.setHrTimeOffTypeId(hrTimeOffTypeId);
	}

	/**
	* Gets the hr time sheet ID of this h r time off.
	*
	* @return the hr time sheet ID of this h r time off
	*/
	public long getHrTimeSheetId() {
		return _hrTimeOff.getHrTimeSheetId();
	}

	/**
	* Sets the hr time sheet ID of this h r time off.
	*
	* @param hrTimeSheetId the hr time sheet ID of this h r time off
	*/
	public void setHrTimeSheetId(long hrTimeSheetId) {
		_hrTimeOff.setHrTimeSheetId(hrTimeSheetId);
	}

	/**
	* Gets the hr user ID of this h r time off.
	*
	* @return the hr user ID of this h r time off
	*/
	public long getHrUserId() {
		return _hrTimeOff.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r time off.
	*
	* @param hrUserId the hr user ID of this h r time off
	*/
	public void setHrUserId(long hrUserId) {
		_hrTimeOff.setHrUserId(hrUserId);
	}

	/**
	* Gets the hr user uuid of this h r time off.
	*
	* @return the hr user uuid of this h r time off
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeOff.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r time off.
	*
	* @param hrUserUuid the hr user uuid of this h r time off
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrTimeOff.setHrUserUuid(hrUserUuid);
	}

	/**
	* Gets the start day of year of this h r time off.
	*
	* @return the start day of year of this h r time off
	*/
	public int getStartDayOfYear() {
		return _hrTimeOff.getStartDayOfYear();
	}

	/**
	* Sets the start day of year of this h r time off.
	*
	* @param startDayOfYear the start day of year of this h r time off
	*/
	public void setStartDayOfYear(int startDayOfYear) {
		_hrTimeOff.setStartDayOfYear(startDayOfYear);
	}

	/**
	* Gets the end day of year of this h r time off.
	*
	* @return the end day of year of this h r time off
	*/
	public int getEndDayOfYear() {
		return _hrTimeOff.getEndDayOfYear();
	}

	/**
	* Sets the end day of year of this h r time off.
	*
	* @param endDayOfYear the end day of year of this h r time off
	*/
	public void setEndDayOfYear(int endDayOfYear) {
		_hrTimeOff.setEndDayOfYear(endDayOfYear);
	}

	/**
	* Gets the year of this h r time off.
	*
	* @return the year of this h r time off
	*/
	public int getYear() {
		return _hrTimeOff.getYear();
	}

	/**
	* Sets the year of this h r time off.
	*
	* @param year the year of this h r time off
	*/
	public void setYear(int year) {
		_hrTimeOff.setYear(year);
	}

	/**
	* Gets the total hours of this h r time off.
	*
	* @return the total hours of this h r time off
	*/
	public double getTotalHours() {
		return _hrTimeOff.getTotalHours();
	}

	/**
	* Sets the total hours of this h r time off.
	*
	* @param totalHours the total hours of this h r time off
	*/
	public void setTotalHours(double totalHours) {
		_hrTimeOff.setTotalHours(totalHours);
	}

	/**
	* Gets the status of this h r time off.
	*
	* @return the status of this h r time off
	*/
	public int getStatus() {
		return _hrTimeOff.getStatus();
	}

	/**
	* Sets the status of this h r time off.
	*
	* @param status the status of this h r time off
	*/
	public void setStatus(int status) {
		_hrTimeOff.setStatus(status);
	}

	/**
	* Gets the status by user ID of this h r time off.
	*
	* @return the status by user ID of this h r time off
	*/
	public long getStatusByUserId() {
		return _hrTimeOff.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this h r time off.
	*
	* @param statusByUserId the status by user ID of this h r time off
	*/
	public void setStatusByUserId(long statusByUserId) {
		_hrTimeOff.setStatusByUserId(statusByUserId);
	}

	/**
	* Gets the status by user uuid of this h r time off.
	*
	* @return the status by user uuid of this h r time off
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeOff.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this h r time off.
	*
	* @param statusByUserUuid the status by user uuid of this h r time off
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_hrTimeOff.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Gets the status by user name of this h r time off.
	*
	* @return the status by user name of this h r time off
	*/
	public java.lang.String getStatusByUserName() {
		return _hrTimeOff.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this h r time off.
	*
	* @param statusByUserName the status by user name of this h r time off
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_hrTimeOff.setStatusByUserName(statusByUserName);
	}

	/**
	* Gets the status date of this h r time off.
	*
	* @return the status date of this h r time off
	*/
	public java.util.Date getStatusDate() {
		return _hrTimeOff.getStatusDate();
	}

	/**
	* Sets the status date of this h r time off.
	*
	* @param statusDate the status date of this h r time off
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_hrTimeOff.setStatusDate(statusDate);
	}

	/**
	* @deprecated {@link #isApproved}
	*/
	public boolean getApproved() {
		return _hrTimeOff.getApproved();
	}

	/**
	* Determines if this h r time off is approved.
	*
	* @return <code>true</code> if this h r time off is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _hrTimeOff.isApproved();
	}

	/**
	* Determines if this h r time off is a draft.
	*
	* @return <code>true</code> if this h r time off is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _hrTimeOff.isDraft();
	}

	/**
	* Determines if this h r time off is expired.
	*
	* @return <code>true</code> if this h r time off is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _hrTimeOff.isExpired();
	}

	/**
	* Determines if this h r time off is pending.
	*
	* @return <code>true</code> if this h r time off is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _hrTimeOff.isPending();
	}

	public boolean isNew() {
		return _hrTimeOff.isNew();
	}

	public void setNew(boolean n) {
		_hrTimeOff.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTimeOff.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTimeOff.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTimeOff.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTimeOff.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTimeOff.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeOff.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeOff.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRTimeOffWrapper((HRTimeOff)_hrTimeOff.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTimeOff hrTimeOff) {
		return _hrTimeOff.compareTo(hrTimeOff);
	}

	public int hashCode() {
		return _hrTimeOff.hashCode();
	}

	public com.liferay.hr.model.HRTimeOff toEscapedModel() {
		return new HRTimeOffWrapper(_hrTimeOff.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrTimeOff.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTimeOff.toXmlString();
	}

	public HRTimeOff getWrappedHRTimeOff() {
		return _hrTimeOff;
	}

	public void resetOriginalValues() {
		_hrTimeOff.resetOriginalValues();
	}

	private HRTimeOff _hrTimeOff;
}