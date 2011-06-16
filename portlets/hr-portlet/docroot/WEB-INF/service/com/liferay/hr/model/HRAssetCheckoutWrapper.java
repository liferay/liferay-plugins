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
 * This class is a wrapper for {@link HRAssetCheckout}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRAssetCheckout
 * @generated
 */
public class HRAssetCheckoutWrapper implements HRAssetCheckout {
	public HRAssetCheckoutWrapper(HRAssetCheckout hrAssetCheckout) {
		_hrAssetCheckout = hrAssetCheckout;
	}

	public Class<?> getModelClass() {
		return HRAssetCheckout.class;
	}

	public String getModelClassName() {
		return HRAssetCheckout.class.getName();
	}

	/**
	* Returns the primary key of this h r asset checkout.
	*
	* @return the primary key of this h r asset checkout
	*/
	public long getPrimaryKey() {
		return _hrAssetCheckout.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r asset checkout.
	*
	* @param primaryKey the primary key of this h r asset checkout
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrAssetCheckout.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr asset checkout ID of this h r asset checkout.
	*
	* @return the hr asset checkout ID of this h r asset checkout
	*/
	public long getHrAssetCheckoutId() {
		return _hrAssetCheckout.getHrAssetCheckoutId();
	}

	/**
	* Sets the hr asset checkout ID of this h r asset checkout.
	*
	* @param hrAssetCheckoutId the hr asset checkout ID of this h r asset checkout
	*/
	public void setHrAssetCheckoutId(long hrAssetCheckoutId) {
		_hrAssetCheckout.setHrAssetCheckoutId(hrAssetCheckoutId);
	}

	/**
	* Returns the group ID of this h r asset checkout.
	*
	* @return the group ID of this h r asset checkout
	*/
	public long getGroupId() {
		return _hrAssetCheckout.getGroupId();
	}

	/**
	* Sets the group ID of this h r asset checkout.
	*
	* @param groupId the group ID of this h r asset checkout
	*/
	public void setGroupId(long groupId) {
		_hrAssetCheckout.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r asset checkout.
	*
	* @return the company ID of this h r asset checkout
	*/
	public long getCompanyId() {
		return _hrAssetCheckout.getCompanyId();
	}

	/**
	* Sets the company ID of this h r asset checkout.
	*
	* @param companyId the company ID of this h r asset checkout
	*/
	public void setCompanyId(long companyId) {
		_hrAssetCheckout.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r asset checkout.
	*
	* @return the user ID of this h r asset checkout
	*/
	public long getUserId() {
		return _hrAssetCheckout.getUserId();
	}

	/**
	* Sets the user ID of this h r asset checkout.
	*
	* @param userId the user ID of this h r asset checkout
	*/
	public void setUserId(long userId) {
		_hrAssetCheckout.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r asset checkout.
	*
	* @return the user uuid of this h r asset checkout
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetCheckout.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r asset checkout.
	*
	* @param userUuid the user uuid of this h r asset checkout
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrAssetCheckout.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r asset checkout.
	*
	* @return the user name of this h r asset checkout
	*/
	public java.lang.String getUserName() {
		return _hrAssetCheckout.getUserName();
	}

	/**
	* Sets the user name of this h r asset checkout.
	*
	* @param userName the user name of this h r asset checkout
	*/
	public void setUserName(java.lang.String userName) {
		_hrAssetCheckout.setUserName(userName);
	}

	/**
	* Returns the create date of this h r asset checkout.
	*
	* @return the create date of this h r asset checkout
	*/
	public java.util.Date getCreateDate() {
		return _hrAssetCheckout.getCreateDate();
	}

	/**
	* Sets the create date of this h r asset checkout.
	*
	* @param createDate the create date of this h r asset checkout
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrAssetCheckout.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r asset checkout.
	*
	* @return the modified date of this h r asset checkout
	*/
	public java.util.Date getModifiedDate() {
		return _hrAssetCheckout.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r asset checkout.
	*
	* @param modifiedDate the modified date of this h r asset checkout
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrAssetCheckout.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the hr asset ID of this h r asset checkout.
	*
	* @return the hr asset ID of this h r asset checkout
	*/
	public long getHrAssetId() {
		return _hrAssetCheckout.getHrAssetId();
	}

	/**
	* Sets the hr asset ID of this h r asset checkout.
	*
	* @param hrAssetId the hr asset ID of this h r asset checkout
	*/
	public void setHrAssetId(long hrAssetId) {
		_hrAssetCheckout.setHrAssetId(hrAssetId);
	}

	/**
	* Returns the hr user ID of this h r asset checkout.
	*
	* @return the hr user ID of this h r asset checkout
	*/
	public long getHrUserId() {
		return _hrAssetCheckout.getHrUserId();
	}

	/**
	* Sets the hr user ID of this h r asset checkout.
	*
	* @param hrUserId the hr user ID of this h r asset checkout
	*/
	public void setHrUserId(long hrUserId) {
		_hrAssetCheckout.setHrUserId(hrUserId);
	}

	/**
	* Returns the hr user uuid of this h r asset checkout.
	*
	* @return the hr user uuid of this h r asset checkout
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getHrUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrAssetCheckout.getHrUserUuid();
	}

	/**
	* Sets the hr user uuid of this h r asset checkout.
	*
	* @param hrUserUuid the hr user uuid of this h r asset checkout
	*/
	public void setHrUserUuid(java.lang.String hrUserUuid) {
		_hrAssetCheckout.setHrUserUuid(hrUserUuid);
	}

	/**
	* Returns the checkout date of this h r asset checkout.
	*
	* @return the checkout date of this h r asset checkout
	*/
	public java.util.Date getCheckoutDate() {
		return _hrAssetCheckout.getCheckoutDate();
	}

	/**
	* Sets the checkout date of this h r asset checkout.
	*
	* @param checkoutDate the checkout date of this h r asset checkout
	*/
	public void setCheckoutDate(java.util.Date checkoutDate) {
		_hrAssetCheckout.setCheckoutDate(checkoutDate);
	}

	/**
	* Returns the expected check in date of this h r asset checkout.
	*
	* @return the expected check in date of this h r asset checkout
	*/
	public java.util.Date getExpectedCheckInDate() {
		return _hrAssetCheckout.getExpectedCheckInDate();
	}

	/**
	* Sets the expected check in date of this h r asset checkout.
	*
	* @param expectedCheckInDate the expected check in date of this h r asset checkout
	*/
	public void setExpectedCheckInDate(java.util.Date expectedCheckInDate) {
		_hrAssetCheckout.setExpectedCheckInDate(expectedCheckInDate);
	}

	/**
	* Returns the actual check in date of this h r asset checkout.
	*
	* @return the actual check in date of this h r asset checkout
	*/
	public java.util.Date getActualCheckInDate() {
		return _hrAssetCheckout.getActualCheckInDate();
	}

	/**
	* Sets the actual check in date of this h r asset checkout.
	*
	* @param actualCheckInDate the actual check in date of this h r asset checkout
	*/
	public void setActualCheckInDate(java.util.Date actualCheckInDate) {
		_hrAssetCheckout.setActualCheckInDate(actualCheckInDate);
	}

	public boolean isNew() {
		return _hrAssetCheckout.isNew();
	}

	public void setNew(boolean n) {
		_hrAssetCheckout.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrAssetCheckout.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrAssetCheckout.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrAssetCheckout.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrAssetCheckout.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrAssetCheckout.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrAssetCheckout.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrAssetCheckout.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrAssetCheckout.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRAssetCheckoutWrapper((HRAssetCheckout)_hrAssetCheckout.clone());
	}

	public int compareTo(com.liferay.hr.model.HRAssetCheckout hrAssetCheckout) {
		return _hrAssetCheckout.compareTo(hrAssetCheckout);
	}

	@Override
	public int hashCode() {
		return _hrAssetCheckout.hashCode();
	}

	public com.liferay.hr.model.HRAssetCheckout toEscapedModel() {
		return new HRAssetCheckoutWrapper(_hrAssetCheckout.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrAssetCheckout.toString();
	}

	public java.lang.String toXmlString() {
		return _hrAssetCheckout.toXmlString();
	}

	public HRAssetCheckout getWrappedHRAssetCheckout() {
		return _hrAssetCheckout;
	}

	public void resetOriginalValues() {
		_hrAssetCheckout.resetOriginalValues();
	}

	private HRAssetCheckout _hrAssetCheckout;
}