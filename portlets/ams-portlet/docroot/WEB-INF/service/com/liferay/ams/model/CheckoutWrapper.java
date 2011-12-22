/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.ams.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Checkout}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Checkout
 * @generated
 */
public class CheckoutWrapper implements Checkout, ModelWrapper<Checkout> {
	public CheckoutWrapper(Checkout checkout) {
		_checkout = checkout;
	}

	public Class<?> getModelClass() {
		return Checkout.class;
	}

	public String getModelClassName() {
		return Checkout.class.getName();
	}

	/**
	* Returns the primary key of this checkout.
	*
	* @return the primary key of this checkout
	*/
	public long getPrimaryKey() {
		return _checkout.getPrimaryKey();
	}

	/**
	* Sets the primary key of this checkout.
	*
	* @param primaryKey the primary key of this checkout
	*/
	public void setPrimaryKey(long primaryKey) {
		_checkout.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the checkout ID of this checkout.
	*
	* @return the checkout ID of this checkout
	*/
	public long getCheckoutId() {
		return _checkout.getCheckoutId();
	}

	/**
	* Sets the checkout ID of this checkout.
	*
	* @param checkoutId the checkout ID of this checkout
	*/
	public void setCheckoutId(long checkoutId) {
		_checkout.setCheckoutId(checkoutId);
	}

	/**
	* Returns the company ID of this checkout.
	*
	* @return the company ID of this checkout
	*/
	public long getCompanyId() {
		return _checkout.getCompanyId();
	}

	/**
	* Sets the company ID of this checkout.
	*
	* @param companyId the company ID of this checkout
	*/
	public void setCompanyId(long companyId) {
		_checkout.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this checkout.
	*
	* @return the user ID of this checkout
	*/
	public long getUserId() {
		return _checkout.getUserId();
	}

	/**
	* Sets the user ID of this checkout.
	*
	* @param userId the user ID of this checkout
	*/
	public void setUserId(long userId) {
		_checkout.setUserId(userId);
	}

	/**
	* Returns the user uuid of this checkout.
	*
	* @return the user uuid of this checkout
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkout.getUserUuid();
	}

	/**
	* Sets the user uuid of this checkout.
	*
	* @param userUuid the user uuid of this checkout
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_checkout.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this checkout.
	*
	* @return the user name of this checkout
	*/
	public java.lang.String getUserName() {
		return _checkout.getUserName();
	}

	/**
	* Sets the user name of this checkout.
	*
	* @param userName the user name of this checkout
	*/
	public void setUserName(java.lang.String userName) {
		_checkout.setUserName(userName);
	}

	/**
	* Returns the create date of this checkout.
	*
	* @return the create date of this checkout
	*/
	public java.util.Date getCreateDate() {
		return _checkout.getCreateDate();
	}

	/**
	* Sets the create date of this checkout.
	*
	* @param createDate the create date of this checkout
	*/
	public void setCreateDate(java.util.Date createDate) {
		_checkout.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this checkout.
	*
	* @return the modified date of this checkout
	*/
	public java.util.Date getModifiedDate() {
		return _checkout.getModifiedDate();
	}

	/**
	* Sets the modified date of this checkout.
	*
	* @param modifiedDate the modified date of this checkout
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_checkout.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the asset ID of this checkout.
	*
	* @return the asset ID of this checkout
	*/
	public long getAssetId() {
		return _checkout.getAssetId();
	}

	/**
	* Sets the asset ID of this checkout.
	*
	* @param assetId the asset ID of this checkout
	*/
	public void setAssetId(long assetId) {
		_checkout.setAssetId(assetId);
	}

	/**
	* Returns the check out date of this checkout.
	*
	* @return the check out date of this checkout
	*/
	public java.util.Date getCheckOutDate() {
		return _checkout.getCheckOutDate();
	}

	/**
	* Sets the check out date of this checkout.
	*
	* @param checkOutDate the check out date of this checkout
	*/
	public void setCheckOutDate(java.util.Date checkOutDate) {
		_checkout.setCheckOutDate(checkOutDate);
	}

	/**
	* Returns the expected check in date of this checkout.
	*
	* @return the expected check in date of this checkout
	*/
	public java.util.Date getExpectedCheckInDate() {
		return _checkout.getExpectedCheckInDate();
	}

	/**
	* Sets the expected check in date of this checkout.
	*
	* @param expectedCheckInDate the expected check in date of this checkout
	*/
	public void setExpectedCheckInDate(java.util.Date expectedCheckInDate) {
		_checkout.setExpectedCheckInDate(expectedCheckInDate);
	}

	/**
	* Returns the actual check in date of this checkout.
	*
	* @return the actual check in date of this checkout
	*/
	public java.util.Date getActualCheckInDate() {
		return _checkout.getActualCheckInDate();
	}

	/**
	* Sets the actual check in date of this checkout.
	*
	* @param actualCheckInDate the actual check in date of this checkout
	*/
	public void setActualCheckInDate(java.util.Date actualCheckInDate) {
		_checkout.setActualCheckInDate(actualCheckInDate);
	}

	public boolean isNew() {
		return _checkout.isNew();
	}

	public void setNew(boolean n) {
		_checkout.setNew(n);
	}

	public boolean isCachedModel() {
		return _checkout.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_checkout.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _checkout.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _checkout.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_checkout.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _checkout.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_checkout.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CheckoutWrapper((Checkout)_checkout.clone());
	}

	public int compareTo(com.liferay.ams.model.Checkout checkout) {
		return _checkout.compareTo(checkout);
	}

	@Override
	public int hashCode() {
		return _checkout.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.ams.model.Checkout> toCacheModel() {
		return _checkout.toCacheModel();
	}

	public com.liferay.ams.model.Checkout toEscapedModel() {
		return new CheckoutWrapper(_checkout.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _checkout.toString();
	}

	public java.lang.String toXmlString() {
		return _checkout.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_checkout.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Checkout getWrappedCheckout() {
		return _checkout;
	}

	public Checkout getWrappedModel() {
		return _checkout;
	}

	public void resetOriginalValues() {
		_checkout.resetOriginalValues();
	}

	private Checkout _checkout;
}