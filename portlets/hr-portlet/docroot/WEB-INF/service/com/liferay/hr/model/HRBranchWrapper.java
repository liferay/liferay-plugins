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
 * This class is a wrapper for {@link HRBranch}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRBranch
 * @generated
 */
public class HRBranchWrapper implements HRBranch {
	public HRBranchWrapper(HRBranch hrBranch) {
		_hrBranch = hrBranch;
	}

	public Class<?> getModelClass() {
		return HRBranch.class;
	}

	public String getModelClassName() {
		return HRBranch.class.getName();
	}

	/**
	* Gets the primary key of this h r branch.
	*
	* @return the primary key of this h r branch
	*/
	public long getPrimaryKey() {
		return _hrBranch.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r branch
	*
	* @param pk the primary key of this h r branch
	*/
	public void setPrimaryKey(long pk) {
		_hrBranch.setPrimaryKey(pk);
	}

	/**
	* Gets the hr branch ID of this h r branch.
	*
	* @return the hr branch ID of this h r branch
	*/
	public long getHrBranchId() {
		return _hrBranch.getHrBranchId();
	}

	/**
	* Sets the hr branch ID of this h r branch.
	*
	* @param hrBranchId the hr branch ID of this h r branch
	*/
	public void setHrBranchId(long hrBranchId) {
		_hrBranch.setHrBranchId(hrBranchId);
	}

	/**
	* Gets the group ID of this h r branch.
	*
	* @return the group ID of this h r branch
	*/
	public long getGroupId() {
		return _hrBranch.getGroupId();
	}

	/**
	* Sets the group ID of this h r branch.
	*
	* @param groupId the group ID of this h r branch
	*/
	public void setGroupId(long groupId) {
		_hrBranch.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r branch.
	*
	* @return the company ID of this h r branch
	*/
	public long getCompanyId() {
		return _hrBranch.getCompanyId();
	}

	/**
	* Sets the company ID of this h r branch.
	*
	* @param companyId the company ID of this h r branch
	*/
	public void setCompanyId(long companyId) {
		_hrBranch.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r branch.
	*
	* @return the user ID of this h r branch
	*/
	public long getUserId() {
		return _hrBranch.getUserId();
	}

	/**
	* Sets the user ID of this h r branch.
	*
	* @param userId the user ID of this h r branch
	*/
	public void setUserId(long userId) {
		_hrBranch.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r branch.
	*
	* @return the user uuid of this h r branch
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrBranch.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r branch.
	*
	* @param userUuid the user uuid of this h r branch
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrBranch.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r branch.
	*
	* @return the user name of this h r branch
	*/
	public java.lang.String getUserName() {
		return _hrBranch.getUserName();
	}

	/**
	* Sets the user name of this h r branch.
	*
	* @param userName the user name of this h r branch
	*/
	public void setUserName(java.lang.String userName) {
		_hrBranch.setUserName(userName);
	}

	/**
	* Gets the create date of this h r branch.
	*
	* @return the create date of this h r branch
	*/
	public java.util.Date getCreateDate() {
		return _hrBranch.getCreateDate();
	}

	/**
	* Sets the create date of this h r branch.
	*
	* @param createDate the create date of this h r branch
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrBranch.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r branch.
	*
	* @return the modified date of this h r branch
	*/
	public java.util.Date getModifiedDate() {
		return _hrBranch.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r branch.
	*
	* @param modifiedDate the modified date of this h r branch
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrBranch.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the organization ID of this h r branch.
	*
	* @return the organization ID of this h r branch
	*/
	public long getOrganizationId() {
		return _hrBranch.getOrganizationId();
	}

	/**
	* Sets the organization ID of this h r branch.
	*
	* @param organizationId the organization ID of this h r branch
	*/
	public void setOrganizationId(long organizationId) {
		_hrBranch.setOrganizationId(organizationId);
	}

	public boolean isNew() {
		return _hrBranch.isNew();
	}

	public void setNew(boolean n) {
		_hrBranch.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrBranch.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrBranch.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrBranch.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrBranch.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrBranch.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrBranch.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrBranch.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRBranchWrapper((HRBranch)_hrBranch.clone());
	}

	public int compareTo(com.liferay.hr.model.HRBranch hrBranch) {
		return _hrBranch.compareTo(hrBranch);
	}

	public int hashCode() {
		return _hrBranch.hashCode();
	}

	public com.liferay.hr.model.HRBranch toEscapedModel() {
		return new HRBranchWrapper(_hrBranch.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrBranch.toString();
	}

	public java.lang.String toXmlString() {
		return _hrBranch.toXmlString();
	}

	public HRBranch getWrappedHRBranch() {
		return _hrBranch;
	}

	public void resetOriginalValues() {
		_hrBranch.resetOriginalValues();
	}

	private HRBranch _hrBranch;
}