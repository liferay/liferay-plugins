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
 * This class is a wrapper for {@link HRUser}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRUser
 * @generated
 */
public class HRUserWrapper implements HRUser {
	public HRUserWrapper(HRUser hrUser) {
		_hrUser = hrUser;
	}

	public Class<?> getModelClass() {
		return HRUser.class;
	}

	public String getModelClassName() {
		return HRUser.class.getName();
	}

	/**
	* Gets the primary key of this h r user.
	*
	* @return the primary key of this h r user
	*/
	public long getPrimaryKey() {
		return _hrUser.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r user
	*
	* @param pk the primary key of this h r user
	*/
	public void setPrimaryKey(long pk) {
		_hrUser.setPrimaryKey(pk);
	}

	/**
	* Gets the hr user ID of this h r user.
	*
	* @return the hr user ID of this h r user
	*/
	public long getHrUserId() {
		return _hrUser.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r user.
	*
	* @param hrUserId the hr user ID of this h r user
	*/
	public void setHrUserId(long hrUserId) {
		_hrUser.setHrUserId(hrUserId);
	}

	/**
	* Gets the hr user uuid of this h r user.
	*
	* @return the hr user uuid of this h r user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUser.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r user.
	*
	* @param hrUserUuid the hr user uuid of this h r user
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrUser.setHrUserUuid(hrUserUuid);
	}

	/**
	* Gets the group ID of this h r user.
	*
	* @return the group ID of this h r user
	*/
	public long getGroupId() {
		return _hrUser.getGroupId();
	}

	/**
	* Sets the group ID of this h r user.
	*
	* @param groupId the group ID of this h r user
	*/
	public void setGroupId(long groupId) {
		_hrUser.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r user.
	*
	* @return the company ID of this h r user
	*/
	public long getCompanyId() {
		return _hrUser.getCompanyId();
	}

	/**
	* Sets the company ID of this h r user.
	*
	* @param companyId the company ID of this h r user
	*/
	public void setCompanyId(long companyId) {
		_hrUser.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r user.
	*
	* @return the user ID of this h r user
	*/
	public long getUserId() {
		return _hrUser.getUserId();
	}

	/**
	* Sets the user ID of this h r user.
	*
	* @param userId the user ID of this h r user
	*/
	public void setUserId(long userId) {
		_hrUser.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r user.
	*
	* @return the user uuid of this h r user
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrUser.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r user.
	*
	* @param userUuid the user uuid of this h r user
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrUser.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r user.
	*
	* @return the user name of this h r user
	*/
	public java.lang.String getUserName() {
		return _hrUser.getUserName();
	}

	/**
	* Sets the user name of this h r user.
	*
	* @param userName the user name of this h r user
	*/
	public void setUserName(java.lang.String userName) {
		_hrUser.setUserName(userName);
	}

	/**
	* Gets the create date of this h r user.
	*
	* @return the create date of this h r user
	*/
	public java.util.Date getCreateDate() {
		return _hrUser.getCreateDate();
	}

	/**
	* Sets the create date of this h r user.
	*
	* @param createDate the create date of this h r user
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrUser.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r user.
	*
	* @return the modified date of this h r user
	*/
	public java.util.Date getModifiedDate() {
		return _hrUser.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r user.
	*
	* @param modifiedDate the modified date of this h r user
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrUser.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr employment type ID of this h r user.
	*
	* @return the hr employment type ID of this h r user
	*/
	public long getHrEmploymentTypeId() {
		return _hrUser.getHrEmploymentTypeId();
	}

	/**
	* Sets the hr employment type ID of this h r user.
	*
	* @param hrEmploymentTypeId the hr employment type ID of this h r user
	*/
	public void setHrEmploymentTypeId(long hrEmploymentTypeId) {
		_hrUser.setHrEmploymentTypeId(hrEmploymentTypeId);
	}

	/**
	* Gets the hr job title ID of this h r user.
	*
	* @return the hr job title ID of this h r user
	*/
	public long getHrJobTitleId() {
		return _hrUser.getHrJobTitleId();
	}

	/**
	* Sets the hr job title ID of this h r user.
	*
	* @param hrJobTitleId the hr job title ID of this h r user
	*/
	public void setHrJobTitleId(long hrJobTitleId) {
		_hrUser.setHrJobTitleId(hrJobTitleId);
	}

	/**
	* Gets the hr office ID of this h r user.
	*
	* @return the hr office ID of this h r user
	*/
	public long getHrOfficeId() {
		return _hrUser.getHrOfficeId();
	}

	/**
	* Sets the hr office ID of this h r user.
	*
	* @param hrOfficeId the hr office ID of this h r user
	*/
	public void setHrOfficeId(long hrOfficeId) {
		_hrUser.setHrOfficeId(hrOfficeId);
	}

	/**
	* Gets the hr termination type ID of this h r user.
	*
	* @return the hr termination type ID of this h r user
	*/
	public long getHrTerminationTypeId() {
		return _hrUser.getHrTerminationTypeId();
	}

	/**
	* Sets the hr termination type ID of this h r user.
	*
	* @param hrTerminationTypeId the hr termination type ID of this h r user
	*/
	public void setHrTerminationTypeId(long hrTerminationTypeId) {
		_hrUser.setHrTerminationTypeId(hrTerminationTypeId);
	}

	/**
	* Gets the hr wage type ID of this h r user.
	*
	* @return the hr wage type ID of this h r user
	*/
	public long getHrWageTypeId() {
		return _hrUser.getHrWageTypeId();
	}

	/**
	* Sets the hr wage type ID of this h r user.
	*
	* @param hrWageTypeId the hr wage type ID of this h r user
	*/
	public void setHrWageTypeId(long hrWageTypeId) {
		_hrUser.setHrWageTypeId(hrWageTypeId);
	}

	/**
	* Gets the hire date of this h r user.
	*
	* @return the hire date of this h r user
	*/
	public java.util.Date getHireDate() {
		return _hrUser.getHireDate();
	}

	/**
	* Sets the hire date of this h r user.
	*
	* @param hireDate the hire date of this h r user
	*/
	public void setHireDate(java.util.Date hireDate) {
		_hrUser.setHireDate(hireDate);
	}

	/**
	* Gets the termination date of this h r user.
	*
	* @return the termination date of this h r user
	*/
	public java.util.Date getTerminationDate() {
		return _hrUser.getTerminationDate();
	}

	/**
	* Sets the termination date of this h r user.
	*
	* @param terminationDate the termination date of this h r user
	*/
	public void setTerminationDate(java.util.Date terminationDate) {
		_hrUser.setTerminationDate(terminationDate);
	}

	/**
	* Gets the wage amount of this h r user.
	*
	* @return the wage amount of this h r user
	*/
	public double getWageAmount() {
		return _hrUser.getWageAmount();
	}

	/**
	* Sets the wage amount of this h r user.
	*
	* @param wageAmount the wage amount of this h r user
	*/
	public void setWageAmount(double wageAmount) {
		_hrUser.setWageAmount(wageAmount);
	}

	/**
	* Gets the wage currency code of this h r user.
	*
	* @return the wage currency code of this h r user
	*/
	public java.lang.String getWageCurrencyCode() {
		return _hrUser.getWageCurrencyCode();
	}

	/**
	* Sets the wage currency code of this h r user.
	*
	* @param wageCurrencyCode the wage currency code of this h r user
	*/
	public void setWageCurrencyCode(java.lang.String wageCurrencyCode) {
		_hrUser.setWageCurrencyCode(wageCurrencyCode);
	}

	/**
	* Gets the benefits exempt of this h r user.
	*
	* @return the benefits exempt of this h r user
	*/
	public boolean getBenefitsExempt() {
		return _hrUser.getBenefitsExempt();
	}

	/**
	* Determines if this h r user is benefits exempt.
	*
	* @return <code>true</code> if this h r user is benefits exempt; <code>false</code> otherwise
	*/
	public boolean isBenefitsExempt() {
		return _hrUser.isBenefitsExempt();
	}

	/**
	* Sets whether this h r user is benefits exempt.
	*
	* @param benefitsExempt the benefits exempt of this h r user
	*/
	public void setBenefitsExempt(boolean benefitsExempt) {
		_hrUser.setBenefitsExempt(benefitsExempt);
	}

	/**
	* Gets the overtime exempt of this h r user.
	*
	* @return the overtime exempt of this h r user
	*/
	public boolean getOvertimeExempt() {
		return _hrUser.getOvertimeExempt();
	}

	/**
	* Determines if this h r user is overtime exempt.
	*
	* @return <code>true</code> if this h r user is overtime exempt; <code>false</code> otherwise
	*/
	public boolean isOvertimeExempt() {
		return _hrUser.isOvertimeExempt();
	}

	/**
	* Sets whether this h r user is overtime exempt.
	*
	* @param overtimeExempt the overtime exempt of this h r user
	*/
	public void setOvertimeExempt(boolean overtimeExempt) {
		_hrUser.setOvertimeExempt(overtimeExempt);
	}

	public boolean isNew() {
		return _hrUser.isNew();
	}

	public void setNew(boolean n) {
		_hrUser.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrUser.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrUser.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrUser.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrUser.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrUser.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrUser.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrUser.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRUserWrapper((HRUser)_hrUser.clone());
	}

	public int compareTo(com.liferay.hr.model.HRUser hrUser) {
		return _hrUser.compareTo(hrUser);
	}

	public int hashCode() {
		return _hrUser.hashCode();
	}

	public com.liferay.hr.model.HRUser toEscapedModel() {
		return new HRUserWrapper(_hrUser.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrUser.toString();
	}

	public java.lang.String toXmlString() {
		return _hrUser.toXmlString();
	}

	public HRUser getWrappedHRUser() {
		return _hrUser;
	}

	public void resetOriginalValues() {
		_hrUser.resetOriginalValues();
	}

	private HRUser _hrUser;
}