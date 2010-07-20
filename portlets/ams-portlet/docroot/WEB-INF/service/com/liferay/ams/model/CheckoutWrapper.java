/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

/**
 * <p>
 * This class is a wrapper for {@link Checkout}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Checkout
 * @generated
 */
public class CheckoutWrapper implements Checkout {
	public CheckoutWrapper(Checkout checkout) {
		_checkout = checkout;
	}

	public long getPrimaryKey() {
		return _checkout.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_checkout.setPrimaryKey(pk);
	}

	public long getCheckoutId() {
		return _checkout.getCheckoutId();
	}

	public void setCheckoutId(long checkoutId) {
		_checkout.setCheckoutId(checkoutId);
	}

	public long getCompanyId() {
		return _checkout.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_checkout.setCompanyId(companyId);
	}

	public long getUserId() {
		return _checkout.getUserId();
	}

	public void setUserId(long userId) {
		_checkout.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _checkout.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_checkout.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _checkout.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_checkout.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _checkout.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_checkout.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _checkout.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_checkout.setModifiedDate(modifiedDate);
	}

	public long getAssetId() {
		return _checkout.getAssetId();
	}

	public void setAssetId(long assetId) {
		_checkout.setAssetId(assetId);
	}

	public java.util.Date getCheckOutDate() {
		return _checkout.getCheckOutDate();
	}

	public void setCheckOutDate(java.util.Date checkOutDate) {
		_checkout.setCheckOutDate(checkOutDate);
	}

	public java.util.Date getExpectedCheckInDate() {
		return _checkout.getExpectedCheckInDate();
	}

	public void setExpectedCheckInDate(java.util.Date expectedCheckInDate) {
		_checkout.setExpectedCheckInDate(expectedCheckInDate);
	}

	public java.util.Date getActualCheckInDate() {
		return _checkout.getActualCheckInDate();
	}

	public void setActualCheckInDate(java.util.Date actualCheckInDate) {
		_checkout.setActualCheckInDate(actualCheckInDate);
	}

	public com.liferay.ams.model.Checkout toEscapedModel() {
		return _checkout.toEscapedModel();
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

	public void setEscapedModel(boolean escapedModel) {
		_checkout.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _checkout.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _checkout.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_checkout.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _checkout.clone();
	}

	public int compareTo(com.liferay.ams.model.Checkout checkout) {
		return _checkout.compareTo(checkout);
	}

	public int hashCode() {
		return _checkout.hashCode();
	}

	public java.lang.String toString() {
		return _checkout.toString();
	}

	public java.lang.String toXmlString() {
		return _checkout.toXmlString();
	}

	public Checkout getWrappedCheckout() {
		return _checkout;
	}

	private Checkout _checkout;
}