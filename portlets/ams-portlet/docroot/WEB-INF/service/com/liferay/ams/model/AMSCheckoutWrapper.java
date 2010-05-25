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
 * <a href="AMSCheckoutSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link AMSCheckout}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSCheckout
 * @generated
 */
public class AMSCheckoutWrapper implements AMSCheckout {
	public AMSCheckoutWrapper(AMSCheckout amsCheckout) {
		_amsCheckout = amsCheckout;
	}

	public long getPrimaryKey() {
		return _amsCheckout.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_amsCheckout.setPrimaryKey(pk);
	}

	public long getAmsCheckoutId() {
		return _amsCheckout.getAmsCheckoutId();
	}

	public void setAmsCheckoutId(long amsCheckoutId) {
		_amsCheckout.setAmsCheckoutId(amsCheckoutId);
	}

	public long getCompanyId() {
		return _amsCheckout.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_amsCheckout.setCompanyId(companyId);
	}

	public long getUserId() {
		return _amsCheckout.getUserId();
	}

	public void setUserId(long userId) {
		_amsCheckout.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _amsCheckout.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_amsCheckout.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _amsCheckout.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_amsCheckout.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _amsCheckout.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_amsCheckout.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _amsCheckout.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_amsCheckout.setModifiedDate(modifiedDate);
	}

	public long getAmsAssetId() {
		return _amsCheckout.getAmsAssetId();
	}

	public void setAmsAssetId(long amsAssetId) {
		_amsCheckout.setAmsAssetId(amsAssetId);
	}

	public java.util.Date getCheckOutDate() {
		return _amsCheckout.getCheckOutDate();
	}

	public void setCheckOutDate(java.util.Date checkOutDate) {
		_amsCheckout.setCheckOutDate(checkOutDate);
	}

	public java.util.Date getExpectedCheckInDate() {
		return _amsCheckout.getExpectedCheckInDate();
	}

	public void setExpectedCheckInDate(java.util.Date expectedCheckInDate) {
		_amsCheckout.setExpectedCheckInDate(expectedCheckInDate);
	}

	public java.util.Date getActualCheckInDate() {
		return _amsCheckout.getActualCheckInDate();
	}

	public void setActualCheckInDate(java.util.Date actualCheckInDate) {
		_amsCheckout.setActualCheckInDate(actualCheckInDate);
	}

	public AMSCheckout toEscapedModel() {
		return _amsCheckout.toEscapedModel();
	}

	public boolean isNew() {
		return _amsCheckout.isNew();
	}

	public boolean setNew(boolean n) {
		return _amsCheckout.setNew(n);
	}

	public boolean isCachedModel() {
		return _amsCheckout.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_amsCheckout.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _amsCheckout.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_amsCheckout.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _amsCheckout.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _amsCheckout.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_amsCheckout.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _amsCheckout.clone();
	}

	public int compareTo(AMSCheckout amsCheckout) {
		return _amsCheckout.compareTo(amsCheckout);
	}

	public int hashCode() {
		return _amsCheckout.hashCode();
	}

	public java.lang.String toString() {
		return _amsCheckout.toString();
	}

	public java.lang.String toXmlString() {
		return _amsCheckout.toXmlString();
	}

	public AMSCheckout getWrappedAMSCheckout() {
		return _amsCheckout;
	}

	private AMSCheckout _amsCheckout;
}