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
 * This class is a wrapper for {@link HRUserHistory}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRUserHistory
 * @generated
 */
public class HRUserHistoryWrapper implements HRUserHistory {
	public HRUserHistoryWrapper(HRUserHistory hrUserHistory) {
		_hrUserHistory = hrUserHistory;
	}

	public Class<?> getModelClass() {
		return HRUserHistory.class;
	}

	public String getModelClassName() {
		return HRUserHistory.class.getName();
	}

	/**
	* Gets the primary key of this h r user history.
	*
	* @return the primary key of this h r user history
	*/
	public long getPrimaryKey() {
		return _hrUserHistory.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r user history
	*
	* @param primaryKey the primary key of this h r user history
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrUserHistory.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the hr user history ID of this h r user history.
	*
	* @return the hr user history ID of this h r user history
	*/
	public long getHrUserHistoryId() {
		return _hrUserHistory.getHrUserHistoryId();
	}

	/**
	* Sets the hr user history ID of this h r user history.
	*
	* @param hrUserHistoryId the hr user history ID of this h r user history
	*/
	public void setHrUserHistoryId(long hrUserHistoryId) {
		_hrUserHistory.setHrUserHistoryId(hrUserHistoryId);
	}

	/**
	* Gets the group ID of this h r user history.
	*
	* @return the group ID of this h r user history
	*/
	public long getGroupId() {
		return _hrUserHistory.getGroupId();
	}

	/**
	* Sets the group ID of this h r user history.
	*
	* @param groupId the group ID of this h r user history
	*/
	public void setGroupId(long groupId) {
		_hrUserHistory.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r user history.
	*
	* @return the company ID of this h r user history
	*/
	public long getCompanyId() {
		return _hrUserHistory.getCompanyId();
	}

	/**
	* Sets the company ID of this h r user history.
	*
	* @param companyId the company ID of this h r user history
	*/
	public void setCompanyId(long companyId) {
		_hrUserHistory.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r user history.
	*
	* @return the user ID of this h r user history
	*/
	public long getUserId() {
		return _hrUserHistory.getUserId();
	}

	/**
	* Sets the user ID of this h r user history.
	*
	* @param userId the user ID of this h r user history
	*/
	public void setUserId(long userId) {
		_hrUserHistory.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r user history.
	*
	* @return the user uuid of this h r user history
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUserHistory.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r user history.
	*
	* @param userUuid the user uuid of this h r user history
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrUserHistory.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r user history.
	*
	* @return the user name of this h r user history
	*/
	public java.lang.String getUserName() {
		return _hrUserHistory.getUserName();
	}

	/**
	* Sets the user name of this h r user history.
	*
	* @param userName the user name of this h r user history
	*/
	public void setUserName(java.lang.String userName) {
		_hrUserHistory.setUserName(userName);
	}

	/**
	* Gets the create date of this h r user history.
	*
	* @return the create date of this h r user history
	*/
	public java.util.Date getCreateDate() {
		return _hrUserHistory.getCreateDate();
	}

	/**
	* Sets the create date of this h r user history.
	*
	* @param createDate the create date of this h r user history
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrUserHistory.setCreateDate(createDate);
	}

	/**
	* Gets the hr employment type ID of this h r user history.
	*
	* @return the hr employment type ID of this h r user history
	*/
	public long getHrEmploymentTypeId() {
		return _hrUserHistory.getHrEmploymentTypeId();
	}

	/**
	* Sets the hr employment type ID of this h r user history.
	*
	* @param hrEmploymentTypeId the hr employment type ID of this h r user history
	*/
	public void setHrEmploymentTypeId(long hrEmploymentTypeId) {
		_hrUserHistory.setHrEmploymentTypeId(hrEmploymentTypeId);
	}

	/**
	* Gets the hr job title ID of this h r user history.
	*
	* @return the hr job title ID of this h r user history
	*/
	public long getHrJobTitleId() {
		return _hrUserHistory.getHrJobTitleId();
	}

	/**
	* Sets the hr job title ID of this h r user history.
	*
	* @param hrJobTitleId the hr job title ID of this h r user history
	*/
	public void setHrJobTitleId(long hrJobTitleId) {
		_hrUserHistory.setHrJobTitleId(hrJobTitleId);
	}

	/**
	* Gets the hr office ID of this h r user history.
	*
	* @return the hr office ID of this h r user history
	*/
	public long getHrOfficeId() {
		return _hrUserHistory.getHrOfficeId();
	}

	/**
	* Sets the hr office ID of this h r user history.
	*
	* @param hrOfficeId the hr office ID of this h r user history
	*/
	public void setHrOfficeId(long hrOfficeId) {
		_hrUserHistory.setHrOfficeId(hrOfficeId);
	}

	/**
	* Gets the hr termination type ID of this h r user history.
	*
	* @return the hr termination type ID of this h r user history
	*/
	public long getHrTerminationTypeId() {
		return _hrUserHistory.getHrTerminationTypeId();
	}

	/**
	* Sets the hr termination type ID of this h r user history.
	*
	* @param hrTerminationTypeId the hr termination type ID of this h r user history
	*/
	public void setHrTerminationTypeId(long hrTerminationTypeId) {
		_hrUserHistory.setHrTerminationTypeId(hrTerminationTypeId);
	}

	/**
	* Gets the hr wage type ID of this h r user history.
	*
	* @return the hr wage type ID of this h r user history
	*/
	public long getHrWageTypeId() {
		return _hrUserHistory.getHrWageTypeId();
	}

	/**
	* Sets the hr wage type ID of this h r user history.
	*
	* @param hrWageTypeId the hr wage type ID of this h r user history
	*/
	public void setHrWageTypeId(long hrWageTypeId) {
		_hrUserHistory.setHrWageTypeId(hrWageTypeId);
	}

	/**
	* Gets the hire date of this h r user history.
	*
	* @return the hire date of this h r user history
	*/
	public java.util.Date getHireDate() {
		return _hrUserHistory.getHireDate();
	}

	/**
	* Sets the hire date of this h r user history.
	*
	* @param hireDate the hire date of this h r user history
	*/
	public void setHireDate(java.util.Date hireDate) {
		_hrUserHistory.setHireDate(hireDate);
	}

	/**
	* Gets the termination date of this h r user history.
	*
	* @return the termination date of this h r user history
	*/
	public java.util.Date getTerminationDate() {
		return _hrUserHistory.getTerminationDate();
	}

	/**
	* Sets the termination date of this h r user history.
	*
	* @param terminationDate the termination date of this h r user history
	*/
	public void setTerminationDate(java.util.Date terminationDate) {
		_hrUserHistory.setTerminationDate(terminationDate);
	}

	/**
	* Gets the wage amount of this h r user history.
	*
	* @return the wage amount of this h r user history
	*/
	public double getWageAmount() {
		return _hrUserHistory.getWageAmount();
	}

	/**
	* Sets the wage amount of this h r user history.
	*
	* @param wageAmount the wage amount of this h r user history
	*/
	public void setWageAmount(double wageAmount) {
		_hrUserHistory.setWageAmount(wageAmount);
	}

	/**
	* Gets the wage currency code of this h r user history.
	*
	* @return the wage currency code of this h r user history
	*/
	public java.lang.String getWageCurrencyCode() {
		return _hrUserHistory.getWageCurrencyCode();
	}

	/**
	* Sets the wage currency code of this h r user history.
	*
	* @param wageCurrencyCode the wage currency code of this h r user history
	*/
	public void setWageCurrencyCode(java.lang.String wageCurrencyCode) {
		_hrUserHistory.setWageCurrencyCode(wageCurrencyCode);
	}

	/**
	* Gets the benefits exempt of this h r user history.
	*
	* @return the benefits exempt of this h r user history
	*/
	public boolean getBenefitsExempt() {
		return _hrUserHistory.getBenefitsExempt();
	}

	/**
	* Determines if this h r user history is benefits exempt.
	*
	* @return <code>true</code> if this h r user history is benefits exempt; <code>false</code> otherwise
	*/
	public boolean isBenefitsExempt() {
		return _hrUserHistory.isBenefitsExempt();
	}

	/**
	* Sets whether this h r user history is benefits exempt.
	*
	* @param benefitsExempt the benefits exempt of this h r user history
	*/
	public void setBenefitsExempt(boolean benefitsExempt) {
		_hrUserHistory.setBenefitsExempt(benefitsExempt);
	}

	/**
	* Gets the overtime exempt of this h r user history.
	*
	* @return the overtime exempt of this h r user history
	*/
	public boolean getOvertimeExempt() {
		return _hrUserHistory.getOvertimeExempt();
	}

	/**
	* Determines if this h r user history is overtime exempt.
	*
	* @return <code>true</code> if this h r user history is overtime exempt; <code>false</code> otherwise
	*/
	public boolean isOvertimeExempt() {
		return _hrUserHistory.isOvertimeExempt();
	}

	/**
	* Sets whether this h r user history is overtime exempt.
	*
	* @param overtimeExempt the overtime exempt of this h r user history
	*/
	public void setOvertimeExempt(boolean overtimeExempt) {
		_hrUserHistory.setOvertimeExempt(overtimeExempt);
	}

	public boolean isNew() {
		return _hrUserHistory.isNew();
	}

	public void setNew(boolean n) {
		_hrUserHistory.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrUserHistory.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrUserHistory.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrUserHistory.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrUserHistory.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrUserHistory.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrUserHistory.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrUserHistory.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrUserHistory.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRUserHistoryWrapper((HRUserHistory)_hrUserHistory.clone());
	}

	public int compareTo(com.liferay.hr.model.HRUserHistory hrUserHistory) {
		return _hrUserHistory.compareTo(hrUserHistory);
	}

	public int hashCode() {
		return _hrUserHistory.hashCode();
	}

	public com.liferay.hr.model.HRUserHistory toEscapedModel() {
		return new HRUserHistoryWrapper(_hrUserHistory.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrUserHistory.toString();
	}

	public java.lang.String toXmlString() {
		return _hrUserHistory.toXmlString();
	}

	public HRUserHistory getWrappedHRUserHistory() {
		return _hrUserHistory;
	}

	public void resetOriginalValues() {
		_hrUserHistory.resetOriginalValues();
	}

	private HRUserHistory _hrUserHistory;
}