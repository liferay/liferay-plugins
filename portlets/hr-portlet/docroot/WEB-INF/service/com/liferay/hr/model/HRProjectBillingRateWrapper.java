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
 * This class is a wrapper for {@link HRProjectBillingRate}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRProjectBillingRate
 * @generated
 */
public class HRProjectBillingRateWrapper implements HRProjectBillingRate {
	public HRProjectBillingRateWrapper(
		HRProjectBillingRate hrProjectBillingRate) {
		_hrProjectBillingRate = hrProjectBillingRate;
	}

	public Class<?> getModelClass() {
		return HRProjectBillingRate.class;
	}

	public String getModelClassName() {
		return HRProjectBillingRate.class.getName();
	}

	/**
	* Gets the primary key of this h r project billing rate.
	*
	* @return the primary key of this h r project billing rate
	*/
	public long getPrimaryKey() {
		return _hrProjectBillingRate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r project billing rate
	*
	* @param primaryKey the primary key of this h r project billing rate
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrProjectBillingRate.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the hr project billing rate ID of this h r project billing rate.
	*
	* @return the hr project billing rate ID of this h r project billing rate
	*/
	public long getHrProjectBillingRateId() {
		return _hrProjectBillingRate.getHrProjectBillingRateId();
	}

	/**
	* Sets the hr project billing rate ID of this h r project billing rate.
	*
	* @param hrProjectBillingRateId the hr project billing rate ID of this h r project billing rate
	*/
	public void setHrProjectBillingRateId(long hrProjectBillingRateId) {
		_hrProjectBillingRate.setHrProjectBillingRateId(hrProjectBillingRateId);
	}

	/**
	* Gets the group ID of this h r project billing rate.
	*
	* @return the group ID of this h r project billing rate
	*/
	public long getGroupId() {
		return _hrProjectBillingRate.getGroupId();
	}

	/**
	* Sets the group ID of this h r project billing rate.
	*
	* @param groupId the group ID of this h r project billing rate
	*/
	public void setGroupId(long groupId) {
		_hrProjectBillingRate.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r project billing rate.
	*
	* @return the company ID of this h r project billing rate
	*/
	public long getCompanyId() {
		return _hrProjectBillingRate.getCompanyId();
	}

	/**
	* Sets the company ID of this h r project billing rate.
	*
	* @param companyId the company ID of this h r project billing rate
	*/
	public void setCompanyId(long companyId) {
		_hrProjectBillingRate.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r project billing rate.
	*
	* @return the user ID of this h r project billing rate
	*/
	public long getUserId() {
		return _hrProjectBillingRate.getUserId();
	}

	/**
	* Sets the user ID of this h r project billing rate.
	*
	* @param userId the user ID of this h r project billing rate
	*/
	public void setUserId(long userId) {
		_hrProjectBillingRate.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r project billing rate.
	*
	* @return the user uuid of this h r project billing rate
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrProjectBillingRate.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r project billing rate.
	*
	* @param userUuid the user uuid of this h r project billing rate
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrProjectBillingRate.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r project billing rate.
	*
	* @return the user name of this h r project billing rate
	*/
	public java.lang.String getUserName() {
		return _hrProjectBillingRate.getUserName();
	}

	/**
	* Sets the user name of this h r project billing rate.
	*
	* @param userName the user name of this h r project billing rate
	*/
	public void setUserName(java.lang.String userName) {
		_hrProjectBillingRate.setUserName(userName);
	}

	/**
	* Gets the create date of this h r project billing rate.
	*
	* @return the create date of this h r project billing rate
	*/
	public java.util.Date getCreateDate() {
		return _hrProjectBillingRate.getCreateDate();
	}

	/**
	* Sets the create date of this h r project billing rate.
	*
	* @param createDate the create date of this h r project billing rate
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrProjectBillingRate.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r project billing rate.
	*
	* @return the modified date of this h r project billing rate
	*/
	public java.util.Date getModifiedDate() {
		return _hrProjectBillingRate.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r project billing rate.
	*
	* @param modifiedDate the modified date of this h r project billing rate
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrProjectBillingRate.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the hr project ID of this h r project billing rate.
	*
	* @return the hr project ID of this h r project billing rate
	*/
	public long getHrProjectId() {
		return _hrProjectBillingRate.getHrProjectId();
	}

	/**
	* Sets the hr project ID of this h r project billing rate.
	*
	* @param hrProjectId the hr project ID of this h r project billing rate
	*/
	public void setHrProjectId(long hrProjectId) {
		_hrProjectBillingRate.setHrProjectId(hrProjectId);
	}

	/**
	* Gets the hr project role ID of this h r project billing rate.
	*
	* @return the hr project role ID of this h r project billing rate
	*/
	public long getHrProjectRoleId() {
		return _hrProjectBillingRate.getHrProjectRoleId();
	}

	/**
	* Sets the hr project role ID of this h r project billing rate.
	*
	* @param hrProjectRoleId the hr project role ID of this h r project billing rate
	*/
	public void setHrProjectRoleId(long hrProjectRoleId) {
		_hrProjectBillingRate.setHrProjectRoleId(hrProjectRoleId);
	}

	/**
	* Gets the rate of this h r project billing rate.
	*
	* @return the rate of this h r project billing rate
	*/
	public double getRate() {
		return _hrProjectBillingRate.getRate();
	}

	/**
	* Sets the rate of this h r project billing rate.
	*
	* @param rate the rate of this h r project billing rate
	*/
	public void setRate(double rate) {
		_hrProjectBillingRate.setRate(rate);
	}

	public boolean isNew() {
		return _hrProjectBillingRate.isNew();
	}

	public void setNew(boolean n) {
		_hrProjectBillingRate.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrProjectBillingRate.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrProjectBillingRate.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrProjectBillingRate.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrProjectBillingRate.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrProjectBillingRate.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrProjectBillingRate.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrProjectBillingRate.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrProjectBillingRate.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRProjectBillingRateWrapper((HRProjectBillingRate)_hrProjectBillingRate.clone());
	}

	public int compareTo(
		com.liferay.hr.model.HRProjectBillingRate hrProjectBillingRate) {
		return _hrProjectBillingRate.compareTo(hrProjectBillingRate);
	}

	public int hashCode() {
		return _hrProjectBillingRate.hashCode();
	}

	public com.liferay.hr.model.HRProjectBillingRate toEscapedModel() {
		return new HRProjectBillingRateWrapper(_hrProjectBillingRate.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrProjectBillingRate.toString();
	}

	public java.lang.String toXmlString() {
		return _hrProjectBillingRate.toXmlString();
	}

	public HRProjectBillingRate getWrappedHRProjectBillingRate() {
		return _hrProjectBillingRate;
	}

	public void resetOriginalValues() {
		_hrProjectBillingRate.resetOriginalValues();
	}

	private HRProjectBillingRate _hrProjectBillingRate;
}