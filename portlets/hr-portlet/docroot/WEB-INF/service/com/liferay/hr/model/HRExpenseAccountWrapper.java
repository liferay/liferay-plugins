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
 * This class is a wrapper for {@link HRExpenseAccount}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRExpenseAccount
 * @generated
 */
public class HRExpenseAccountWrapper implements HRExpenseAccount {
	public HRExpenseAccountWrapper(HRExpenseAccount hrExpenseAccount) {
		_hrExpenseAccount = hrExpenseAccount;
	}

	public Class<?> getModelClass() {
		return HRExpenseAccount.class;
	}

	public String getModelClassName() {
		return HRExpenseAccount.class.getName();
	}

	/**
	* Gets the primary key of this h r expense account.
	*
	* @return the primary key of this h r expense account
	*/
	public long getPrimaryKey() {
		return _hrExpenseAccount.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r expense account
	*
	* @param pk the primary key of this h r expense account
	*/
	public void setPrimaryKey(long pk) {
		_hrExpenseAccount.setPrimaryKey(pk);
	}

	/**
	* Gets the hr expense account ID of this h r expense account.
	*
	* @return the hr expense account ID of this h r expense account
	*/
	public long getHrExpenseAccountId() {
		return _hrExpenseAccount.getHrExpenseAccountId();
	}

	/**
	* Sets the hr expense account ID of this h r expense account.
	*
	* @param hrExpenseAccountId the hr expense account ID of this h r expense account
	*/
	public void setHrExpenseAccountId(long hrExpenseAccountId) {
		_hrExpenseAccount.setHrExpenseAccountId(hrExpenseAccountId);
	}

	/**
	* Gets the group ID of this h r expense account.
	*
	* @return the group ID of this h r expense account
	*/
	public long getGroupId() {
		return _hrExpenseAccount.getGroupId();
	}

	/**
	* Sets the group ID of this h r expense account.
	*
	* @param groupId the group ID of this h r expense account
	*/
	public void setGroupId(long groupId) {
		_hrExpenseAccount.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r expense account.
	*
	* @return the company ID of this h r expense account
	*/
	public long getCompanyId() {
		return _hrExpenseAccount.getCompanyId();
	}

	/**
	* Sets the company ID of this h r expense account.
	*
	* @param companyId the company ID of this h r expense account
	*/
	public void setCompanyId(long companyId) {
		_hrExpenseAccount.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r expense account.
	*
	* @return the user ID of this h r expense account
	*/
	public long getUserId() {
		return _hrExpenseAccount.getUserId();
	}

	/**
	* Sets the user ID of this h r expense account.
	*
	* @param userId the user ID of this h r expense account
	*/
	public void setUserId(long userId) {
		_hrExpenseAccount.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r expense account.
	*
	* @return the user uuid of this h r expense account
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseAccount.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r expense account.
	*
	* @param userUuid the user uuid of this h r expense account
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrExpenseAccount.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r expense account.
	*
	* @return the user name of this h r expense account
	*/
	public java.lang.String getUserName() {
		return _hrExpenseAccount.getUserName();
	}

	/**
	* Sets the user name of this h r expense account.
	*
	* @param userName the user name of this h r expense account
	*/
	public void setUserName(java.lang.String userName) {
		_hrExpenseAccount.setUserName(userName);
	}

	/**
	* Gets the create date of this h r expense account.
	*
	* @return the create date of this h r expense account
	*/
	public java.util.Date getCreateDate() {
		return _hrExpenseAccount.getCreateDate();
	}

	/**
	* Sets the create date of this h r expense account.
	*
	* @param createDate the create date of this h r expense account
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrExpenseAccount.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r expense account.
	*
	* @return the modified date of this h r expense account
	*/
	public java.util.Date getModifiedDate() {
		return _hrExpenseAccount.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r expense account.
	*
	* @param modifiedDate the modified date of this h r expense account
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrExpenseAccount.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the name of this h r expense account.
	*
	* @return the name of this h r expense account
	*/
	public java.lang.String getName() {
		return _hrExpenseAccount.getName();
	}

	/**
	* Sets the name of this h r expense account.
	*
	* @param name the name of this h r expense account
	*/
	public void setName(java.lang.String name) {
		_hrExpenseAccount.setName(name);
	}

	/**
	* Gets the description of this h r expense account.
	*
	* @return the description of this h r expense account
	*/
	public java.lang.String getDescription() {
		return _hrExpenseAccount.getDescription();
	}

	/**
	* Sets the description of this h r expense account.
	*
	* @param description the description of this h r expense account
	*/
	public void setDescription(java.lang.String description) {
		_hrExpenseAccount.setDescription(description);
	}

	public boolean isNew() {
		return _hrExpenseAccount.isNew();
	}

	public void setNew(boolean n) {
		_hrExpenseAccount.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrExpenseAccount.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrExpenseAccount.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrExpenseAccount.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrExpenseAccount.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrExpenseAccount.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrExpenseAccount.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrExpenseAccount.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRExpenseAccountWrapper((HRExpenseAccount)_hrExpenseAccount.clone());
	}

	public int compareTo(com.liferay.hr.model.HRExpenseAccount hrExpenseAccount) {
		return _hrExpenseAccount.compareTo(hrExpenseAccount);
	}

	public int hashCode() {
		return _hrExpenseAccount.hashCode();
	}

	public com.liferay.hr.model.HRExpenseAccount toEscapedModel() {
		return new HRExpenseAccountWrapper(_hrExpenseAccount.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrExpenseAccount.toString();
	}

	public java.lang.String toXmlString() {
		return _hrExpenseAccount.toXmlString();
	}

	public HRExpenseAccount getWrappedHRExpenseAccount() {
		return _hrExpenseAccount;
	}

	public void resetOriginalValues() {
		_hrExpenseAccount.resetOriginalValues();
	}

	private HRExpenseAccount _hrExpenseAccount;
}