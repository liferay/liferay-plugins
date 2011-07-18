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
 * This class is a wrapper for {@link HRExpenseCurrencyConversion}.
 * </p>
 *
 * @author    Wesley Gong
 * @see       HRExpenseCurrencyConversion
 * @generated
 */
public class HRExpenseCurrencyConversionWrapper
	implements HRExpenseCurrencyConversion {
	public HRExpenseCurrencyConversionWrapper(
		HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		_hrExpenseCurrencyConversion = hrExpenseCurrencyConversion;
	}

	public Class<?> getModelClass() {
		return HRExpenseCurrencyConversion.class;
	}

	public String getModelClassName() {
		return HRExpenseCurrencyConversion.class.getName();
	}

	/**
	* Returns the primary key of this h r expense currency conversion.
	*
	* @return the primary key of this h r expense currency conversion
	*/
	public long getPrimaryKey() {
		return _hrExpenseCurrencyConversion.getPrimaryKey();
	}

	/**
	* Sets the primary key of this h r expense currency conversion.
	*
	* @param primaryKey the primary key of this h r expense currency conversion
	*/
	public void setPrimaryKey(long primaryKey) {
		_hrExpenseCurrencyConversion.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the hr expense currency conversion ID of this h r expense currency conversion.
	*
	* @return the hr expense currency conversion ID of this h r expense currency conversion
	*/
	public long getHrExpenseCurrencyConversionId() {
		return _hrExpenseCurrencyConversion.getHrExpenseCurrencyConversionId();
	}

	/**
	* Sets the hr expense currency conversion ID of this h r expense currency conversion.
	*
	* @param hrExpenseCurrencyConversionId the hr expense currency conversion ID of this h r expense currency conversion
	*/
	public void setHrExpenseCurrencyConversionId(
		long hrExpenseCurrencyConversionId) {
		_hrExpenseCurrencyConversion.setHrExpenseCurrencyConversionId(hrExpenseCurrencyConversionId);
	}

	/**
	* Returns the group ID of this h r expense currency conversion.
	*
	* @return the group ID of this h r expense currency conversion
	*/
	public long getGroupId() {
		return _hrExpenseCurrencyConversion.getGroupId();
	}

	/**
	* Sets the group ID of this h r expense currency conversion.
	*
	* @param groupId the group ID of this h r expense currency conversion
	*/
	public void setGroupId(long groupId) {
		_hrExpenseCurrencyConversion.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this h r expense currency conversion.
	*
	* @return the company ID of this h r expense currency conversion
	*/
	public long getCompanyId() {
		return _hrExpenseCurrencyConversion.getCompanyId();
	}

	/**
	* Sets the company ID of this h r expense currency conversion.
	*
	* @param companyId the company ID of this h r expense currency conversion
	*/
	public void setCompanyId(long companyId) {
		_hrExpenseCurrencyConversion.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this h r expense currency conversion.
	*
	* @return the user ID of this h r expense currency conversion
	*/
	public long getUserId() {
		return _hrExpenseCurrencyConversion.getUserId();
	}

	/**
	* Sets the user ID of this h r expense currency conversion.
	*
	* @param userId the user ID of this h r expense currency conversion
	*/
	public void setUserId(long userId) {
		_hrExpenseCurrencyConversion.setUserId(userId);
	}

	/**
	* Returns the user uuid of this h r expense currency conversion.
	*
	* @return the user uuid of this h r expense currency conversion
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _hrExpenseCurrencyConversion.getUserUuid();
	}

	/**
	* Sets the user uuid of this h r expense currency conversion.
	*
	* @param userUuid the user uuid of this h r expense currency conversion
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_hrExpenseCurrencyConversion.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this h r expense currency conversion.
	*
	* @return the user name of this h r expense currency conversion
	*/
	public java.lang.String getUserName() {
		return _hrExpenseCurrencyConversion.getUserName();
	}

	/**
	* Sets the user name of this h r expense currency conversion.
	*
	* @param userName the user name of this h r expense currency conversion
	*/
	public void setUserName(java.lang.String userName) {
		_hrExpenseCurrencyConversion.setUserName(userName);
	}

	/**
	* Returns the create date of this h r expense currency conversion.
	*
	* @return the create date of this h r expense currency conversion
	*/
	public java.util.Date getCreateDate() {
		return _hrExpenseCurrencyConversion.getCreateDate();
	}

	/**
	* Sets the create date of this h r expense currency conversion.
	*
	* @param createDate the create date of this h r expense currency conversion
	*/
	public void setCreateDate(java.util.Date createDate) {
		_hrExpenseCurrencyConversion.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this h r expense currency conversion.
	*
	* @return the modified date of this h r expense currency conversion
	*/
	public java.util.Date getModifiedDate() {
		return _hrExpenseCurrencyConversion.getModifiedDate();
	}

	/**
	* Sets the modified date of this h r expense currency conversion.
	*
	* @param modifiedDate the modified date of this h r expense currency conversion
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_hrExpenseCurrencyConversion.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the from h r expense currency ID of this h r expense currency conversion.
	*
	* @return the from h r expense currency ID of this h r expense currency conversion
	*/
	public long getFromHRExpenseCurrencyId() {
		return _hrExpenseCurrencyConversion.getFromHRExpenseCurrencyId();
	}

	/**
	* Sets the from h r expense currency ID of this h r expense currency conversion.
	*
	* @param fromHRExpenseCurrencyId the from h r expense currency ID of this h r expense currency conversion
	*/
	public void setFromHRExpenseCurrencyId(long fromHRExpenseCurrencyId) {
		_hrExpenseCurrencyConversion.setFromHRExpenseCurrencyId(fromHRExpenseCurrencyId);
	}

	/**
	* Returns the to h r expense currency ID of this h r expense currency conversion.
	*
	* @return the to h r expense currency ID of this h r expense currency conversion
	*/
	public long getToHRExpenseCurrencyId() {
		return _hrExpenseCurrencyConversion.getToHRExpenseCurrencyId();
	}

	/**
	* Sets the to h r expense currency ID of this h r expense currency conversion.
	*
	* @param toHRExpenseCurrencyId the to h r expense currency ID of this h r expense currency conversion
	*/
	public void setToHRExpenseCurrencyId(long toHRExpenseCurrencyId) {
		_hrExpenseCurrencyConversion.setToHRExpenseCurrencyId(toHRExpenseCurrencyId);
	}

	/**
	* Returns the conversion date of this h r expense currency conversion.
	*
	* @return the conversion date of this h r expense currency conversion
	*/
	public java.util.Date getConversionDate() {
		return _hrExpenseCurrencyConversion.getConversionDate();
	}

	/**
	* Sets the conversion date of this h r expense currency conversion.
	*
	* @param conversionDate the conversion date of this h r expense currency conversion
	*/
	public void setConversionDate(java.util.Date conversionDate) {
		_hrExpenseCurrencyConversion.setConversionDate(conversionDate);
	}

	/**
	* Returns the conversion value of this h r expense currency conversion.
	*
	* @return the conversion value of this h r expense currency conversion
	*/
	public double getConversionValue() {
		return _hrExpenseCurrencyConversion.getConversionValue();
	}

	/**
	* Sets the conversion value of this h r expense currency conversion.
	*
	* @param conversionValue the conversion value of this h r expense currency conversion
	*/
	public void setConversionValue(double conversionValue) {
		_hrExpenseCurrencyConversion.setConversionValue(conversionValue);
	}

	public boolean isNew() {
		return _hrExpenseCurrencyConversion.isNew();
	}

	public void setNew(boolean n) {
		_hrExpenseCurrencyConversion.setNew(n);
	}

	public boolean isCachedModel() {
		return _hrExpenseCurrencyConversion.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_hrExpenseCurrencyConversion.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _hrExpenseCurrencyConversion.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_hrExpenseCurrencyConversion.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _hrExpenseCurrencyConversion.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_hrExpenseCurrencyConversion.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _hrExpenseCurrencyConversion.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_hrExpenseCurrencyConversion.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new HRExpenseCurrencyConversionWrapper((HRExpenseCurrencyConversion)_hrExpenseCurrencyConversion.clone());
	}

	public int compareTo(
		com.liferay.hr.model.HRExpenseCurrencyConversion hrExpenseCurrencyConversion) {
		return _hrExpenseCurrencyConversion.compareTo(hrExpenseCurrencyConversion);
	}

	@Override
	public int hashCode() {
		return _hrExpenseCurrencyConversion.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.hr.model.HRExpenseCurrencyConversion> toCacheModel() {
		return _hrExpenseCurrencyConversion.toCacheModel();
	}

	public com.liferay.hr.model.HRExpenseCurrencyConversion toEscapedModel() {
		return new HRExpenseCurrencyConversionWrapper(_hrExpenseCurrencyConversion.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _hrExpenseCurrencyConversion.toString();
	}

	public java.lang.String toXmlString() {
		return _hrExpenseCurrencyConversion.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_hrExpenseCurrencyConversion.persist();
	}

	public HRExpenseCurrencyConversion getWrappedHRExpenseCurrencyConversion() {
		return _hrExpenseCurrencyConversion;
	}

	public void resetOriginalValues() {
		_hrExpenseCurrencyConversion.resetOriginalValues();
	}

	private HRExpenseCurrencyConversion _hrExpenseCurrencyConversion;
}