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
 * This class is a wrapper for {@link HRTimeOffPolicy}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRTimeOffPolicy
 * @generated
 */
public class HRTimeOffPolicyWrapper implements HRTimeOffPolicy {
	public HRTimeOffPolicyWrapper(HRTimeOffPolicy hrTimeOffPolicy) {
		_hrTimeOffPolicy = hrTimeOffPolicy;
	}

	public Class<?> getModelClass() {
		return HRTimeOffPolicy.class;
	}

	public String getModelClassName() {
		return HRTimeOffPolicy.class.getName();
	}

	/**
	* Returns the primary key of this h r time off policy.
	*
	* @return the primary key of this h r time off policy
	*/
	public long getPrimaryKey() {
		return _hrTimeOffPolicy.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r time off policy.
	*
	* @param primaryKey the primary key of this h r time off policy
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrTimeOffPolicy.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr time off policy ID of this h r time off policy.
	*
	* @return the hr time off policy ID of this h r time off policy
	*/
	public long getHrTimeOffPolicyId() {
		return _hrTimeOffPolicy.getHrTimeOffPolicyId();
	}

	/**
	* Sets the hr time off policy ID of this h r time off policy.
	*
	* @param hrTimeOffPolicyId the hr time off policy ID of this h r time off policy
	*/
	public void setHrTimeOffPolicyId(long hrTimeOffPolicyId) {
		_hrTimeOffPolicy.setHrTimeOffPolicyId(hrTimeOffPolicyId);
	}

	/**
	* Returns the group ID of this h r time off policy.
	*
	* @return the group ID of this h r time off policy
	*/
	public long getGroupId() {
		return _hrTimeOffPolicy.getGroupId();
	}

	/**
	* Sets the group ID of this h r time off policy.
	*
	* @param groupId the group ID of this h r time off policy
	*/
	public void setGroupId(long groupId) {
		_hrTimeOffPolicy.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r time off policy.
	*
	* @return the company ID of this h r time off policy
	*/
	public long getCompanyId() {
		return _hrTimeOffPolicy.getCompanyId();
	}

	/**
	* Sets the company ID of this h r time off policy.
	*
	* @param companyId the company ID of this h r time off policy
	*/
	public void setCompanyId(long companyId) {
		_hrTimeOffPolicy.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r time off policy.
	*
	* @return the user ID of this h r time off policy
	*/
	public long getUserId() {
		return _hrTimeOffPolicy.getUserId();
	}

	/**
	* Sets the user ID of this h r time off policy.
	*
	* @param userId the user ID of this h r time off policy
	*/
	public void setUserId(long userId) {
		_hrTimeOffPolicy.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r time off policy.
	*
	* @return the user uuid of this h r time off policy
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeOffPolicy.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r time off policy.
	*
	* @param userUuid the user uuid of this h r time off policy
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrTimeOffPolicy.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r time off policy.
	*
	* @return the user name of this h r time off policy
	*/
	public java.lang.String getUserName() {
		return _hrTimeOffPolicy.getUserName();
	}

	/**
	* Sets the user name of this h r time off policy.
	*
	* @param userName the user name of this h r time off policy
	*/
	public void setUserName(java.lang.String userName) {
		_hrTimeOffPolicy.setUserName(userName);
	}

	/**
	* Returns the create date of this h r time off policy.
	*
	* @return the create date of this h r time off policy
	*/
	public java.util.Date getCreateDate() {
		return _hrTimeOffPolicy.getCreateDate();
	}

	/**
	* Sets the create date of this h r time off policy.
	*
	* @param createDate the create date of this h r time off policy
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrTimeOffPolicy.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r time off policy.
	*
	* @return the modified date of this h r time off policy
	*/
	public java.util.Date getModifiedDate() {
		return _hrTimeOffPolicy.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r time off policy.
	*
	* @param modifiedDate the modified date of this h r time off policy
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrTimeOffPolicy.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the hr time off type ID of this h r time off policy.
	*
	* @return the hr time off type ID of this h r time off policy
	*/
	public long getHrTimeOffTypeId() {
		return _hrTimeOffPolicy.getHrTimeOffTypeId();
	}

	/**
	* Sets the hr time off type ID of this h r time off policy.
	*
	* @param hrTimeOffTypeId the hr time off type ID of this h r time off policy
	*/
	public void setHrTimeOffTypeId(long hrTimeOffTypeId) {
		_hrTimeOffPolicy.setHrTimeOffTypeId(hrTimeOffTypeId);
	}

	/**
	* Returns the hr user ID of this h r time off policy.
	*
	* @return the hr user ID of this h r time off policy
	*/
	public long getHrUserId() {
		return _hrTimeOffPolicy.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r time off policy.
	*
	* @param hrUserId the hr user ID of this h r time off policy
	*/
	public void setHrUserId(long hrUserId) {
		_hrTimeOffPolicy.setHrUserId(hrUserId);
	}

	/**
	* Returns the hr user uuid of this h r time off policy.
	*
	* @return the hr user uuid of this h r time off policy
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrTimeOffPolicy.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r time off policy.
	*
	* @param hrUserUuid the hr user uuid of this h r time off policy
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrTimeOffPolicy.setHrUserUuid(hrUserUuid);
	}

	/**
	* Returns the accrue h r time off frequency type ID of this h r time off policy.
	*
	* @return the accrue h r time off frequency type ID of this h r time off policy
	*/
	public long getAccrueHRTimeOffFrequencyTypeId() {
		return _hrTimeOffPolicy.getAccrueHRTimeOffFrequencyTypeId();
	}

	/**
	* Sets the accrue h r time off frequency type ID of this h r time off policy.
	*
	* @param accrueHRTimeOffFrequencyTypeId the accrue h r time off frequency type ID of this h r time off policy
	*/
	public void setAccrueHRTimeOffFrequencyTypeId(
		long accrueHRTimeOffFrequencyTypeId) {
		_hrTimeOffPolicy.setAccrueHRTimeOffFrequencyTypeId(accrueHRTimeOffFrequencyTypeId);
	}

	/**
	* Returns the reset h r time off frequency type ID of this h r time off policy.
	*
	* @return the reset h r time off frequency type ID of this h r time off policy
	*/
	public long getResetHRTimeOffFrequencyTypeId() {
		return _hrTimeOffPolicy.getResetHRTimeOffFrequencyTypeId();
	}

	/**
	* Sets the reset h r time off frequency type ID of this h r time off policy.
	*
	* @param resetHRTimeOffFrequencyTypeId the reset h r time off frequency type ID of this h r time off policy
	*/
	public void setResetHRTimeOffFrequencyTypeId(
		long resetHRTimeOffFrequencyTypeId) {
		_hrTimeOffPolicy.setResetHRTimeOffFrequencyTypeId(resetHRTimeOffFrequencyTypeId);
	}

	/**
	* Returns the effective date of this h r time off policy.
	*
	* @return the effective date of this h r time off policy
	*/
	public java.util.Date getEffectiveDate() {
		return _hrTimeOffPolicy.getEffectiveDate();
	}

	/**
	* Sets the effective date of this h r time off policy.
	*
	* @param effectiveDate the effective date of this h r time off policy
	*/
	public void setEffectiveDate(java.util.Date effectiveDate) {
		_hrTimeOffPolicy.setEffectiveDate(effectiveDate);
	}

	/**
	* Returns the inactive of this h r time off policy.
	*
	* @return the inactive of this h r time off policy
	*/
	public boolean getInactive() {
		return _hrTimeOffPolicy.getInactive();
	}

	/**
	* Returns <code>true</code> if this h r time off policy is inactive.
	*
	* @return <code>true</code> if this h r time off policy is inactive; <code>false</code> otherwise
	*/
	public boolean isInactive() {
		return _hrTimeOffPolicy.isInactive();
	}

	/**
	* Sets whether this h r time off policy is inactive.
	*
	* @param inactive the inactive of this h r time off policy
	*/
	public void setInactive(boolean inactive) {
		_hrTimeOffPolicy.setInactive(inactive);
	}

	/**
	* Returns the hours allowed of this h r time off policy.
	*
	* @return the hours allowed of this h r time off policy
	*/
	public double getHoursAllowed() {
		return _hrTimeOffPolicy.getHoursAllowed();
	}

	/**
	* Sets the hours allowed of this h r time off policy.
	*
	* @param hoursAllowed the hours allowed of this h r time off policy
	*/
	public void setHoursAllowed(double hoursAllowed) {
		_hrTimeOffPolicy.setHoursAllowed(hoursAllowed);
	}

	/**
	* Returns the hours base amount of this h r time off policy.
	*
	* @return the hours base amount of this h r time off policy
	*/
	public double getHoursBaseAmount() {
		return _hrTimeOffPolicy.getHoursBaseAmount();
	}

	/**
	* Sets the hours base amount of this h r time off policy.
	*
	* @param hoursBaseAmount the hours base amount of this h r time off policy
	*/
	public void setHoursBaseAmount(double hoursBaseAmount) {
		_hrTimeOffPolicy.setHoursBaseAmount(hoursBaseAmount);
	}

	/**
	* Returns the hours to accrue of this h r time off policy.
	*
	* @return the hours to accrue of this h r time off policy
	*/
	public double getHoursToAccrue() {
		return _hrTimeOffPolicy.getHoursToAccrue();
	}

	/**
	* Sets the hours to accrue of this h r time off policy.
	*
	* @param hoursToAccrue the hours to accrue of this h r time off policy
	*/
	public void setHoursToAccrue(double hoursToAccrue) {
		_hrTimeOffPolicy.setHoursToAccrue(hoursToAccrue);
	}

	/**
	* Returns the carry over hours allowed of this h r time off policy.
	*
	* @return the carry over hours allowed of this h r time off policy
	*/
	public double getCarryOverHoursAllowed() {
		return _hrTimeOffPolicy.getCarryOverHoursAllowed();
	}

	/**
	* Sets the carry over hours allowed of this h r time off policy.
	*
	* @param carryOverHoursAllowed the carry over hours allowed of this h r time off policy
	*/
	public void setCarryOverHoursAllowed(double carryOverHoursAllowed) {
		_hrTimeOffPolicy.setCarryOverHoursAllowed(carryOverHoursAllowed);
	}

	public boolean isNew() {
		return _hrTimeOffPolicy.isNew();
	}

	public void setNew(boolean n) {
		_hrTimeOffPolicy.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrTimeOffPolicy.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrTimeOffPolicy.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrTimeOffPolicy.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrTimeOffPolicy.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrTimeOffPolicy.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrTimeOffPolicy.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrTimeOffPolicy.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrTimeOffPolicy.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRTimeOffPolicyWrapper((HRTimeOffPolicy)_hrTimeOffPolicy.clone());
	}

	public int compareTo(com.liferay.hr.model.HRTimeOffPolicy hrTimeOffPolicy) {
		return _hrTimeOffPolicy.compareTo(hrTimeOffPolicy);
	}

	@Override
	public int hashCode() {
		return _hrTimeOffPolicy.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRTimeOffPolicy> toCacheModel() {
		return _hrTimeOffPolicy.toCacheModel();
	}

	public com.liferay.hr.model.HRTimeOffPolicy toEscapedModel() {
		return new HRTimeOffPolicyWrapper(_hrTimeOffPolicy.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrTimeOffPolicy.toString();
	}

	public java.lang.String toXmlString() {
		return _hrTimeOffPolicy.toXmlString();
	}

	public HRTimeOffPolicy getWrappedHRTimeOffPolicy() {
		return _hrTimeOffPolicy;
	}

	public void resetOriginalValues() {
		_hrTimeOffPolicy.resetOriginalValues();
	}

	private HRTimeOffPolicy _hrTimeOffPolicy;
}