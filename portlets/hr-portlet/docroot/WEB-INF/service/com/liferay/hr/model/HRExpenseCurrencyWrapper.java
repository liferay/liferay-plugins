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
 * This class is a wrapper for {@link HRExpenseCurrency}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       HRExpenseCurrency
 * @generated
 */
public class HRExpenseCurrencyWrapper implements HRExpenseCurrency {
	public HRExpenseCurrencyWrapper(HRExpenseCurrency hrExpenseCurrency) {
		_hrExpenseCurrency = hrExpenseCurrency;
	}

	public Class<?> getModelClass() {
		return HRExpenseCurrency.class;
	}

	public String getModelClassName() {
		return HRExpenseCurrency.class.getName();
	}

	/**
	* Gets the primary key of this h r expense currency.
	*
	* @return the primary key of this h r expense currency
	*/
	public long getPrimaryKey() {
		return _hrExpenseCurrency.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r expense currency
	*
	* @param pk the primary key of this h r expense currency
	*/
	public void setPrimaryKey(long pk) {
		_hrExpenseCurrency.setPrimaryKey(pk);
	}

	/**
	* Gets the hr expense currency ID of this h r expense currency.
	*
	* @return the hr expense currency ID of this h r expense currency
	*/
	public long getHrExpenseCurrencyId() {
		return _hrExpenseCurrency.getHrExpenseCurrencyId();
	}

	/**
	* Sets the hr expense currency ID of this h r expense currency.
	*
	* @param hrExpenseCurrencyId the hr expense currency ID of this h r expense currency
	*/
	public void setHrExpenseCurrencyId(long hrExpenseCurrencyId) {
		_hrExpenseCurrency.setHrExpenseCurrencyId(hrExpenseCurrencyId);
	}

	/**
	* Gets the group ID of this h r expense currency.
	*
	* @return the group ID of this h r expense currency
	*/
	public long getGroupId() {
		return _hrExpenseCurrency.getGroupId();
	}

	/**
	* Sets the group ID of this h r expense currency.
	*
	* @param groupId the group ID of this h r expense currency
	*/
	public void setGroupId(long groupId) {
		_hrExpenseCurrency.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this h r expense currency.
	*
	* @return the company ID of this h r expense currency
	*/
	public long getCompanyId() {
		return _hrExpenseCurrency.getCompanyId();
	}

	/**
	* Sets the company ID of this h r expense currency.
	*
	* @param companyId the company ID of this h r expense currency
	*/
	public void setCompanyId(long companyId) {
		_hrExpenseCurrency.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this h r expense currency.
	*
	* @return the user ID of this h r expense currency
	*/
	public long getUserId() {
		return _hrExpenseCurrency.getUserId();
	}

	/**
	* Sets the user ID of this h r expense currency.
	*
	* @param userId the user ID of this h r expense currency
	*/
	public void setUserId(long userId) {
		_hrExpenseCurrency.setUserId(userId);
	}

	/**
	* Gets the user uuid of this h r expense currency.
	*
	* @return the user uuid of this h r expense currency
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrency.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r expense currency.
	*
	* @param userUuid the user uuid of this h r expense currency
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrExpenseCurrency.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this h r expense currency.
	*
	* @return the user name of this h r expense currency
	*/
	public java.lang.String getUserName() {
		return _hrExpenseCurrency.getUserName();
	}

	/**
	* Sets the user name of this h r expense currency.
	*
	* @param userName the user name of this h r expense currency
	*/
	public void setUserName(java.lang.String userName) {
		_hrExpenseCurrency.setUserName(userName);
	}

	/**
	* Gets the create date of this h r expense currency.
	*
	* @return the create date of this h r expense currency
	*/
	public java.util.Date getCreateDate() {
		return _hrExpenseCurrency.getCreateDate();
	}

	/**
	* Sets the create date of this h r expense currency.
	*
	* @param createDate the create date of this h r expense currency
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrExpenseCurrency.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this h r expense currency.
	*
	* @return the modified date of this h r expense currency
	*/
	public java.util.Date getModifiedDate() {
		return _hrExpenseCurrency.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r expense currency.
	*
	* @param modifiedDate the modified date of this h r expense currency
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrExpenseCurrency.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the from currency code of this h r expense currency.
	*
	* @return the from currency code of this h r expense currency
	*/
	public java.lang.String getFromCurrencyCode() {
		return _hrExpenseCurrency.getFromCurrencyCode();
	}

	/**
	* Sets the from currency code of this h r expense currency.
	*
	* @param fromCurrencyCode the from currency code of this h r expense currency
	*/
	public void setFromCurrencyCode(java.lang.String fromCurrencyCode) {
		_hrExpenseCurrency.setFromCurrencyCode(fromCurrencyCode);
	}

	/**
	* Gets the to currency code of this h r expense currency.
	*
	* @return the to currency code of this h r expense currency
	*/
	public java.lang.String getToCurrencyCode() {
		return _hrExpenseCurrency.getToCurrencyCode();
	}

	/**
	* Sets the to currency code of this h r expense currency.
	*
	* @param toCurrencyCode the to currency code of this h r expense currency
	*/
	public void setToCurrencyCode(java.lang.String toCurrencyCode) {
		_hrExpenseCurrency.setToCurrencyCode(toCurrencyCode);
	}

	/**
	* Gets the conversion date of this h r expense currency.
	*
	* @return the conversion date of this h r expense currency
	*/
	public java.util.Date getConversionDate() {
		return _hrExpenseCurrency.getConversionDate();
	}

	/**
	* Sets the conversion date of this h r expense currency.
	*
	* @param conversionDate the conversion date of this h r expense currency
	*/
	public void setConversionDate(java.util.Date conversionDate) {
		_hrExpenseCurrency.setConversionDate(conversionDate);
	}

	/**
	* Gets the conversion value of this h r expense currency.
	*
	* @return the conversion value of this h r expense currency
	*/
	public double getConversionValue() {
		return _hrExpenseCurrency.getConversionValue();
	}

	/**
	* Sets the conversion value of this h r expense currency.
	*
	* @param conversionValue the conversion value of this h r expense currency
	*/
	public void setConversionValue(double conversionValue) {
		_hrExpenseCurrency.setConversionValue(conversionValue);
	}

	public boolean isNew() {
		return _hrExpenseCurrency.isNew();
	}

	public void setNew(boolean n) {
		_hrExpenseCurrency.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrExpenseCurrency.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrExpenseCurrency.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrExpenseCurrency.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrExpenseCurrency.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrExpenseCurrency.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrExpenseCurrency.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrExpenseCurrency.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new HRExpenseCurrencyWrapper((HRExpenseCurrency)_hrExpenseCurrency.clone());
	}

	public int compareTo(
		com.liferay.hr.model.HRExpenseCurrency hrExpenseCurrency) {
		return _hrExpenseCurrency.compareTo(hrExpenseCurrency);
	}

	public int hashCode() {
		return _hrExpenseCurrency.hashCode();
	}

	public com.liferay.hr.model.HRExpenseCurrency toEscapedModel() {
		return new HRExpenseCurrencyWrapper(_hrExpenseCurrency.toEscapedModel());
	}

	public java.lang.String toString() {
		return _hrExpenseCurrency.toString();
	}

	public java.lang.String toXmlString() {
		return _hrExpenseCurrency.toXmlString();
	}

	public HRExpenseCurrency getWrappedHRExpenseCurrency() {
		return _hrExpenseCurrency;
	}

	public void resetOriginalValues() {
		_hrExpenseCurrency.resetOriginalValues();
	}

	private HRExpenseCurrency _hrExpenseCurrency;
}