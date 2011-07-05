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
 * @author    Wesley Gong
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
	* Returns the primary key of this h r time sheet.
	*
	* @return the primary key of this h r time sheet
	*/
	public long getPrimaryKey() {
		return _hrTimeSheet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time sheet.
	*
	* @param primaryKey the primary key of this h r time sheet
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrTimeSheet.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr time sheet ID of this h r time sheet.
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
	* Returns the group ID of this h r time sheet.
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
	* Returns the company ID of this h r time sheet.
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
	* Returns the user ID of this h r time sheet.
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
	* Returns the user uuid of this h r time sheet.
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
	* Returns the user name of this h r time sheet.
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
	* Returns the create date of this h r time sheet.
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
	* Returns the modified date of this h r time sheet.
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
	* Returns the hr user ID of this h r time sheet.
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
	* Returns the hr user uuid of this h r time sheet.
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
	* Returns the start day of year of this h r time sheet.
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
	* Returns the end day of year of this h r time sheet.
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
	* Returns the year of this h r time sheet.
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
	* Returns the status of this h r time sheet.
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
	* Returns the status by user ID of this h r time sheet.
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
	* Returns the status by user uuid of this h r time sheet.
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
	* Returns the status by user name of this h r time sheet.
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
	* Returns the status date of this h r time sheet.
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
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _hrTimeSheet.getApproved();
	}

	/**
	* Returns <code>true</code> if this h r time sheet is approved.
	*
	* @return <code>true</code> if this h r time sheet is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _hrTimeSheet.isApproved();
	}

	/**
	* Returns <code>true</code> if this h r time sheet is a draft.
	*
	* @return <code>true</code> if this h r time sheet is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _hrTimeSheet.isDraft();
	}

	/**
	* Returns <code>true</code> if this h r time sheet is expired.
	*
	* @return <code>true</code> if this h r time sheet is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _hrTimeSheet.isExpired();
	}

	/**
	* Returns <code>true</code> if this h r time sheet is pending.
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

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrTimeSheet.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeSheet.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeSheet.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRTimeSheetWrapper((HRTimeSheet)_hrTimeSheet.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTimeSheet hrTimeSheet) {
		return _hrTimeSheet.compareTo(hrTimeSheet);
	}

	@Override
	public int hashCode() {
		return _hrTimeSheet.hashCode();
	}

	public com.liferay.hr.model.HRTimeSheet toEscapedModel() {
		return new HRTimeSheetWrapper(_hrTimeSheet.toEscapedModel());
	}

	@Override
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