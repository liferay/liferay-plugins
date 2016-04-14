/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.samplealloymvc.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Checkout}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Checkout
 * @generated
 */
@ProviderType
public class CheckoutWrapper implements Checkout, ModelWrapper<Checkout> {
	public CheckoutWrapper(Checkout checkout) {
		_checkout = checkout;
	}

	@Override
	public Class<?> getModelClass() {
		return Checkout.class;
	}

	@Override
	public String getModelClassName() {
		return Checkout.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("checkoutId", getCheckoutId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("assetId", getAssetId());
		attributes.put("checkOutDate", getCheckOutDate());
		attributes.put("expectedCheckInDate", getExpectedCheckInDate());
		attributes.put("actualCheckInDate", getActualCheckInDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long checkoutId = (Long)attributes.get("checkoutId");

		if (checkoutId != null) {
			setCheckoutId(checkoutId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long assetId = (Long)attributes.get("assetId");

		if (assetId != null) {
			setAssetId(assetId);
		}

		Date checkOutDate = (Date)attributes.get("checkOutDate");

		if (checkOutDate != null) {
			setCheckOutDate(checkOutDate);
		}

		Date expectedCheckInDate = (Date)attributes.get("expectedCheckInDate");

		if (expectedCheckInDate != null) {
			setExpectedCheckInDate(expectedCheckInDate);
		}

		Date actualCheckInDate = (Date)attributes.get("actualCheckInDate");

		if (actualCheckInDate != null) {
			setActualCheckInDate(actualCheckInDate);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _checkout.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _checkout.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _checkout.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _checkout.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<com.liferay.samplealloymvc.model.Checkout> toCacheModel() {
		return _checkout.toCacheModel();
	}

	@Override
	public com.liferay.samplealloymvc.model.Checkout toEscapedModel() {
		return new CheckoutWrapper(_checkout.toEscapedModel());
	}

	@Override
	public com.liferay.samplealloymvc.model.Checkout toUnescapedModel() {
		return new CheckoutWrapper(_checkout.toUnescapedModel());
	}

	@Override
	public int compareTo(com.liferay.samplealloymvc.model.Checkout checkout) {
		return _checkout.compareTo(checkout);
	}

	@Override
	public int hashCode() {
		return _checkout.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _checkout.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new CheckoutWrapper((Checkout)_checkout.clone());
	}

	/**
	* Returns the user name of this checkout.
	*
	* @return the user name of this checkout
	*/
	@Override
	public java.lang.String getUserName() {
		return _checkout.getUserName();
	}

	/**
	* Returns the user uuid of this checkout.
	*
	* @return the user uuid of this checkout
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _checkout.getUserUuid();
	}

	@Override
	public java.lang.String toString() {
		return _checkout.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _checkout.toXmlString();
	}

	/**
	* Returns the actual check in date of this checkout.
	*
	* @return the actual check in date of this checkout
	*/
	@Override
	public Date getActualCheckInDate() {
		return _checkout.getActualCheckInDate();
	}

	/**
	* Returns the check out date of this checkout.
	*
	* @return the check out date of this checkout
	*/
	@Override
	public Date getCheckOutDate() {
		return _checkout.getCheckOutDate();
	}

	/**
	* Returns the create date of this checkout.
	*
	* @return the create date of this checkout
	*/
	@Override
	public Date getCreateDate() {
		return _checkout.getCreateDate();
	}

	/**
	* Returns the expected check in date of this checkout.
	*
	* @return the expected check in date of this checkout
	*/
	@Override
	public Date getExpectedCheckInDate() {
		return _checkout.getExpectedCheckInDate();
	}

	/**
	* Returns the modified date of this checkout.
	*
	* @return the modified date of this checkout
	*/
	@Override
	public Date getModifiedDate() {
		return _checkout.getModifiedDate();
	}

	/**
	* Returns the asset ID of this checkout.
	*
	* @return the asset ID of this checkout
	*/
	@Override
	public long getAssetId() {
		return _checkout.getAssetId();
	}

	/**
	* Returns the checkout ID of this checkout.
	*
	* @return the checkout ID of this checkout
	*/
	@Override
	public long getCheckoutId() {
		return _checkout.getCheckoutId();
	}

	/**
	* Returns the company ID of this checkout.
	*
	* @return the company ID of this checkout
	*/
	@Override
	public long getCompanyId() {
		return _checkout.getCompanyId();
	}

	/**
	* Returns the primary key of this checkout.
	*
	* @return the primary key of this checkout
	*/
	@Override
	public long getPrimaryKey() {
		return _checkout.getPrimaryKey();
	}

	/**
	* Returns the user ID of this checkout.
	*
	* @return the user ID of this checkout
	*/
	@Override
	public long getUserId() {
		return _checkout.getUserId();
	}

	@Override
	public void persist() {
		_checkout.persist();
	}

	/**
	* Sets the actual check in date of this checkout.
	*
	* @param actualCheckInDate the actual check in date of this checkout
	*/
	@Override
	public void setActualCheckInDate(Date actualCheckInDate) {
		_checkout.setActualCheckInDate(actualCheckInDate);
	}

	/**
	* Sets the asset ID of this checkout.
	*
	* @param assetId the asset ID of this checkout
	*/
	@Override
	public void setAssetId(long assetId) {
		_checkout.setAssetId(assetId);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_checkout.setCachedModel(cachedModel);
	}

	/**
	* Sets the check out date of this checkout.
	*
	* @param checkOutDate the check out date of this checkout
	*/
	@Override
	public void setCheckOutDate(Date checkOutDate) {
		_checkout.setCheckOutDate(checkOutDate);
	}

	/**
	* Sets the checkout ID of this checkout.
	*
	* @param checkoutId the checkout ID of this checkout
	*/
	@Override
	public void setCheckoutId(long checkoutId) {
		_checkout.setCheckoutId(checkoutId);
	}

	/**
	* Sets the company ID of this checkout.
	*
	* @param companyId the company ID of this checkout
	*/
	@Override
	public void setCompanyId(long companyId) {
		_checkout.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this checkout.
	*
	* @param createDate the create date of this checkout
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_checkout.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_checkout.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_checkout.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_checkout.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expected check in date of this checkout.
	*
	* @param expectedCheckInDate the expected check in date of this checkout
	*/
	@Override
	public void setExpectedCheckInDate(Date expectedCheckInDate) {
		_checkout.setExpectedCheckInDate(expectedCheckInDate);
	}

	/**
	* Sets the modified date of this checkout.
	*
	* @param modifiedDate the modified date of this checkout
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_checkout.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_checkout.setNew(n);
	}

	/**
	* Sets the primary key of this checkout.
	*
	* @param primaryKey the primary key of this checkout
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_checkout.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_checkout.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this checkout.
	*
	* @param userId the user ID of this checkout
	*/
	@Override
	public void setUserId(long userId) {
		_checkout.setUserId(userId);
	}

	/**
	* Sets the user name of this checkout.
	*
	* @param userName the user name of this checkout
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_checkout.setUserName(userName);
	}

	/**
	* Sets the user uuid of this checkout.
	*
	* @param userUuid the user uuid of this checkout
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_checkout.setUserUuid(userUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CheckoutWrapper)) {
			return false;
		}

		CheckoutWrapper checkoutWrapper = (CheckoutWrapper)obj;

		if (Validator.equals(_checkout, checkoutWrapper._checkout)) {
			return true;
		}

		return false;
	}

	@Override
	public Checkout getWrappedModel() {
		return _checkout;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _checkout.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _checkout.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_checkout.resetOriginalValues();
	}

	private final Checkout _checkout;
}