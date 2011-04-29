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
 * This class is a wrapper for {@link HRTimeSheet}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRTimeSheet
 * @generated
 */
public class HRTimeSheetWrapper implements HRTimeSheet {
	public HRTimeSheetWrapper(HRTimeSheet hrTimeSheet) {
		_hrTimeSheet = hrTimeSheet;
	}

	public Class<?> getModelClass() {
		return HRTimeSheet.class;
	}

	public String getModelClassName() {
		return HRTimeSheet.class.getName();
	}

	/**
	* Gets the primary key of this h r time sheet.
	*
	* @return the primary key of this h r time sheet
	*/
	public long getPrimaryKey() {
		return _hrTimeSheet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time sheet
	*
	* @param pk the primary key of this h r time sheet
	*/
	public void setPrimaryKey(long pk) {
		_hrTimeSheet.setPrimaryKey(pk);
	}

	/**
	* Gets the hr time sheet ID of this h r time sheet.
	*
	* @return the hr time sheet ID of this h r time sheet
	*/
	public long getHrTimeSheetId() {
		return _hrTimeSheet.getHrTimeSheetId();
	}

	/**
	* Sets the hr time sheet ID of this h r time sheet.
	*
	* @param hrTimeSheetId the hr time sheet ID of this h r time sheet
	*/
	public void setHrTimeSheetId(long hrTimeSheetId) {
		_hrTimeSheet.setHrTimeSheetId(hrTimeSheetId);
	}

	/**
	* Gets the group ID of this h r time sheet.
	*
	* @return the group ID of this h r time sheet
	*/
	public long getGroupId() {
		return _hrTimeSheet.getGroupId();
	}

	/**
	* Sets the group ID of this h r time sheet.
	*
	* @param groupId the group ID of this h r time sheet
	*/
	public void setGroupId(long groupId) {
		_hrTimeSheet.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r time sheet.
	*
	* @return the company ID of this h r time sheet
	*/
	public long getCompanyId() {
		return _hrTimeSheet.getCompanyId();
	}

	/**
	* Sets the company ID of this h r time sheet.
	*
	* @param companyId the company ID of this h r time sheet
	*/
	public void setCompanyId(long companyId) {
		_hrTimeSheet.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r time sheet.
	*
	* @return the user ID of this h r time sheet
	*/
	public long getUserId() {
		return _hrTimeSheet.getUserId();
	}

	/**
	* Sets the user ID of this h r time sheet.
	*
	* @param userId the user ID of this h r time sheet
	*/
	public void setUserId(long userId) {
		_hrTimeSheet.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r time sheet.
	*
	* @return the user uuid of this h r time sheet
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeSheet.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r time sheet.
	*
	* @param userUuid the user uuid of this h r time sheet
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTimeSheet.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r time sheet.
	*
	* @return the user name of this h r time sheet
	*/
	public java.lang.String getUserName() {
		return _hrTimeSheet.getUserName();
	}

	/**
	* Sets the user name of this h r time sheet.
	*
	* @param userName the user name of this h r time sheet
	*/
	public void setUserName(java.lang.String userName) {
		_hrTimeSheet.setUserName(userName);
	}

	/**
	* Gets the create date of this h r time sheet.
	*
	* @return the create date of this h r time sheet
	*/
	public java.util.Date getCreateDate() {
		return _hrTimeSheet.getCreateDate();
	}

	/**
	* Sets the create date of this h r time sheet.
	*
	* @param createDate the create date of this h r time sheet
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTimeSheet.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r time sheet.
	*
	* @return the modified date of this h r time sheet
	*/
	public java.util.Date getModifiedDate() {
		return _hrTimeSheet.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r time sheet.
	*
	* @param modifiedDate the modified date of this h r time sheet
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTimeSheet.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr user ID of this h r time sheet.
	*
	* @return the hr user ID of this h r time sheet
	*/
	public long getHrUserId() {
		return _hrTimeSheet.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r time sheet.
	*
	* @param hrUserId the hr user ID of this h r time sheet
	*/
	public void setHrUserId(long hrUserId) {
		_hrTimeSheet.setHrUserId(hrUserId);
	}

	/**
	* Gets the hr user uuid of this h r time sheet.
	*
	* @return the hr user uuid of this h r time sheet
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeSheet.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r time sheet.
	*
	* @param hrUserUuid the hr user uuid of this h r time sheet
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrTimeSheet.setHrUserUuid(hrUserUuid);
	}

	/**
	* Gets the start day of year of this h r time sheet.
	*
	* @return the start day of year of this h r time sheet
	*/
	public int getStartDayOfYear() {
		return _hrTimeSheet.getStartDayOfYear();
	}

	/**
	* Sets the start day of year of this h r time sheet.
	*
	* @param startDayOfYear the start day of year of this h r time sheet
	*/
	public void setStartDayOfYear(int startDayOfYear) {
		_hrTimeSheet.setStartDayOfYear(startDayOfYear);
	}

	/**
	* Gets the end day of year of this h r time sheet.
	*
	* @return the end day of year of this h r time sheet
	*/
	public int getEndDayOfYear() {
		return _hrTimeSheet.getEndDayOfYear();
	}

	/**
	* Sets the end day of year of this h r time sheet.
	*
	* @param endDayOfYear the end day of year of this h r time sheet
	*/
	public void setEndDayOfYear(int endDayOfYear) {
		_hrTimeSheet.setEndDayOfYear(endDayOfYear);
	}

	/**
	* Gets the year of this h r time sheet.
	*
	* @return the year of this h r time sheet
	*/
	public int getYear() {
		return _hrTimeSheet.getYear();
	}

	/**
	* Sets the year of this h r time sheet.
	*
	* @param year the year of this h r time sheet
	*/
	public void setYear(int year) {
		_hrTimeSheet.setYear(year);
	}

	/**
	* Gets the status of this h r time sheet.
	*
	* @return the status of this h r time sheet
	*/
	public int getStatus() {
		return _hrTimeSheet.getStatus();
	}

	/**
	* Sets the status of this h r time sheet.
	*
	* @param status the status of this h r time sheet
	*/
	public void setStatus(int status) {
		_hrTimeSheet.setStatus(status);
	}

	/**
	* Gets the status by user ID of this h r time sheet.
	*
	* @return the status by user ID of this h r time sheet
	*/
	public long getStatusByUserId() {
		return _hrTimeSheet.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this h r time sheet.
	*
	* @param statusByUserId the status by user ID of this h r time sheet
	*/
	public void setStatusByUserId(long statusByUserId) {
		_hrTimeSheet.setStatusByUserId(statusByUserId);
	}

	/**
	* Gets the status by user uuid of this h r time sheet.
	*
	* @return the status by user uuid of this h r time sheet
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeSheet.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this h r time sheet.
	*
	* @param statusByUserUuid the status by user uuid of this h r time sheet
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_hrTimeSheet.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Gets the status by user name of this h r time sheet.
	*
	* @return the status by user name of this h r time sheet
	*/
	public java.lang.String getStatusByUserName() {
		return _hrTimeSheet.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this h r time sheet.
	*
	* @param statusByUserName the status by user name of this h r time sheet
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_hrTimeSheet.setStatusByUserName(statusByUserName);
	}

	/**
	* Gets the status date of this h r time sheet.
	*
	* @return the status date of this h r time sheet
	*/
	public java.util.Date getStatusDate() {
		return _hrTimeSheet.getStatusDate();
	}

	/**
	* Sets the status date of this h r time sheet.
	*
	* @param statusDate the status date of this h r time sheet
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_hrTimeSheet.setStatusDate(statusDate);
	}

	/**
	* @deprecated {@link #isApproved}
	*/
	public boolean getApproved() {
		return _hrTimeSheet.getApproved();
	}

	/**
	* Determines if this h r time sheet is approved.
	*
	* @return <code>true</code> if this h r time sheet is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _hrTimeSheet.isApproved();
	}

	/**
	* Determines if this h r time sheet is a draft.
	*
	* @return <code>true</code> if this h r time sheet is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _hrTimeSheet.isDraft();
	}

	/**
	* Determines if this h r time sheet is expired.
	*
	* @return <code>true</code> if this h r time sheet is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _hrTimeSheet.isExpired();
	}

	/**
	* Determines if this h r time sheet is pending.
	*
	* @return <code>true</code> if this h r time sheet is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _hrTimeSheet.isPending();
	}

	public boolean isNew() {
		return _hrTimeSheet.isNew();
	}

	public void setNew(boolean n) {
		_hrTimeSheet.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTimeSheet.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTimeSheet.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTimeSheet.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTimeSheet.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTimeSheet.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeSheet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeSheet.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRTimeSheetWrapper((HRTimeSheet)_hrTimeSheet.clone());
	}

	public int compareTo(HRTimeSheet hrTimeSheet) {
		return _hrTimeSheet.compareTo(hrTimeSheet);
	}

	public int hashCode() {
		return _hrTimeSheet.hashCode();
	}

	public HRTimeSheet toEscapedModel() {
		return new HRTimeSheetWrapper(_hrTimeSheet.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrTimeSheet.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTimeSheet.toXmlString();
	}

	public HRTimeSheet getWrappedHRTimeSheet() {
		return _hrTimeSheet;
	}

	public void resetOriginalValues() {
		_hrTimeSheet.resetOriginalValues();
	}

	private HRTimeSheet _hrTimeSheet;
}