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
 * This class is a wrapper for {@link HRBillability}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRBillability
 * @generated
 */
public class HRBillabilityWrapper implements HRBillability {
	public HRBillabilityWrapper(HRBillability hrBillability) {
		_hrBillability = hrBillability;
	}

	public Class<?> getModelClass() {
		return HRBillability.class;
	}

	public String getModelClassName() {
		return HRBillability.class.getName();
	}

	/**
	* Gets the primary key of this h r billability.
	*
	* @return the primary key of this h r billability
	*/
	public long getPrimaryKey() {
		return _hrBillability.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r billability
	*
	* @param pk the primary key of this h r billability
	*/
	public void setPrimaryKey(long pk) {
		_hrBillability.setPrimaryKey(pk);
	}

	/**
	* Gets the hr billability ID of this h r billability.
	*
	* @return the hr billability ID of this h r billability
	*/
	public long getHrBillabilityId() {
		return _hrBillability.getHrBillabilityId();
	}

	/**
	* Sets the hr billability ID of this h r billability.
	*
	* @param hrBillabilityId the hr billability ID of this h r billability
	*/
	public void setHrBillabilityId(long hrBillabilityId) {
		_hrBillability.setHrBillabilityId(hrBillabilityId);
	}

	/**
	* Gets the group ID of this h r billability.
	*
	* @return the group ID of this h r billability
	*/
	public long getGroupId() {
		return _hrBillability.getGroupId();
	}

	/**
	* Sets the group ID of this h r billability.
	*
	* @param groupId the group ID of this h r billability
	*/
	public void setGroupId(long groupId) {
		_hrBillability.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r billability.
	*
	* @return the company ID of this h r billability
	*/
	public long getCompanyId() {
		return _hrBillability.getCompanyId();
	}

	/**
	* Sets the company ID of this h r billability.
	*
	* @param companyId the company ID of this h r billability
	*/
	public void setCompanyId(long companyId) {
		_hrBillability.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r billability.
	*
	* @return the user ID of this h r billability
	*/
	public long getUserId() {
		return _hrBillability.getUserId();
	}

	/**
	* Sets the user ID of this h r billability.
	*
	* @param userId the user ID of this h r billability
	*/
	public void setUserId(long userId) {
		_hrBillability.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r billability.
	*
	* @return the user uuid of this h r billability
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrBillability.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r billability.
	*
	* @param userUuid the user uuid of this h r billability
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrBillability.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r billability.
	*
	* @return the user name of this h r billability
	*/
	public java.lang.String getUserName() {
		return _hrBillability.getUserName();
	}

	/**
	* Sets the user name of this h r billability.
	*
	* @param userName the user name of this h r billability
	*/
	public void setUserName(java.lang.String userName) {
		_hrBillability.setUserName(userName);
	}

	/**
	* Gets the create date of this h r billability.
	*
	* @return the create date of this h r billability
	*/
	public java.util.Date getCreateDate() {
		return _hrBillability.getCreateDate();
	}

	/**
	* Sets the create date of this h r billability.
	*
	* @param createDate the create date of this h r billability
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrBillability.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r billability.
	*
	* @return the modified date of this h r billability
	*/
	public java.util.Date getModifiedDate() {
		return _hrBillability.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r billability.
	*
	* @param modifiedDate the modified date of this h r billability
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrBillability.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the code of this h r billability.
	*
	* @return the code of this h r billability
	*/
	public java.lang.String getCode() {
		return _hrBillability.getCode();
	}

	/**
	* Sets the code of this h r billability.
	*
	* @param code the code of this h r billability
	*/
	public void setCode(java.lang.String code) {
		_hrBillability.setCode(code);
	}

	/**
	* Gets the name of this h r billability.
	*
	* @return the name of this h r billability
	*/
	public java.lang.String getName() {
		return _hrBillability.getName();
	}

	/**
	* Sets the name of this h r billability.
	*
	* @param name the name of this h r billability
	*/
	public void setName(java.lang.String name) {
		_hrBillability.setName(name);
	}

	/**
	* Gets the description of this h r billability.
	*
	* @return the description of this h r billability
	*/
	public java.lang.String getDescription() {
		return _hrBillability.getDescription();
	}

	/**
	* Sets the description of this h r billability.
	*
	* @param description the description of this h r billability
	*/
	public void setDescription(java.lang.String description) {
		_hrBillability.setDescription(description);
	}

	public boolean isNew() {
		return _hrBillability.isNew();
	}

	public void setNew(boolean n) {
		_hrBillability.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrBillability.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrBillability.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrBillability.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrBillability.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrBillability.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrBillability.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrBillability.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRBillabilityWrapper((HRBillability)_hrBillability.clone());
	}

	public int compareTo(com.liferay.hr.model.HRBillability hrBillability) {
		return _hrBillability.compareTo(hrBillability);
	}

	public int hashCode() {
		return _hrBillability.hashCode();
	}

	public com.liferay.hr.model.HRBillability toEscapedModel() {
		return new HRBillabilityWrapper(_hrBillability.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrBillability.toString();
	}

	public java.lang.String toXmlString() {
		return _hrBillability.toXmlString();
	}

	public HRBillability getWrappedHRBillability() {
		return _hrBillability;
	}

	public void resetOriginalValues() {
		_hrBillability.resetOriginalValues();
	}

	private HRBillability _hrBillability;
}