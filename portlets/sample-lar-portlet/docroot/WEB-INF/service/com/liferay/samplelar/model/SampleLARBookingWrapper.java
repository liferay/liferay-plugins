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

package com.liferay.samplelar.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import com.liferay.portlet.exportimport.lar.StagedModelType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SampleLARBooking}.
 * </p>
 *
 * @author Mate Thurzo
 * @see SampleLARBooking
 * @generated
 */
@ProviderType
public class SampleLARBookingWrapper implements SampleLARBooking,
	ModelWrapper<SampleLARBooking> {
	public SampleLARBookingWrapper(SampleLARBooking sampleLARBooking) {
		_sampleLARBooking = sampleLARBooking;
	}

	@Override
	public Class<?> getModelClass() {
		return SampleLARBooking.class;
	}

	@Override
	public String getModelClassName() {
		return SampleLARBooking.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sampleLARBookingId", getSampleLARBookingId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("bookingNumber", getBookingNumber());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long sampleLARBookingId = (Long)attributes.get("sampleLARBookingId");

		if (sampleLARBookingId != null) {
			setSampleLARBookingId(sampleLARBookingId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String bookingNumber = (String)attributes.get("bookingNumber");

		if (bookingNumber != null) {
			setBookingNumber(bookingNumber);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SampleLARBookingWrapper((SampleLARBooking)_sampleLARBooking.clone());
	}

	@Override
	public int compareTo(
		com.liferay.samplelar.model.SampleLARBooking sampleLARBooking) {
		return _sampleLARBooking.compareTo(sampleLARBooking);
	}

	/**
	* Returns the booking number of this sample l a r booking.
	*
	* @return the booking number of this sample l a r booking
	*/
	@Override
	public java.lang.String getBookingNumber() {
		return _sampleLARBooking.getBookingNumber();
	}

	/**
	* Returns the company ID of this sample l a r booking.
	*
	* @return the company ID of this sample l a r booking
	*/
	@Override
	public long getCompanyId() {
		return _sampleLARBooking.getCompanyId();
	}

	/**
	* Returns the create date of this sample l a r booking.
	*
	* @return the create date of this sample l a r booking
	*/
	@Override
	public Date getCreateDate() {
		return _sampleLARBooking.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _sampleLARBooking.getExpandoBridge();
	}

	/**
	* Returns the group ID of this sample l a r booking.
	*
	* @return the group ID of this sample l a r booking
	*/
	@Override
	public long getGroupId() {
		return _sampleLARBooking.getGroupId();
	}

	/**
	* Returns the modified date of this sample l a r booking.
	*
	* @return the modified date of this sample l a r booking
	*/
	@Override
	public Date getModifiedDate() {
		return _sampleLARBooking.getModifiedDate();
	}

	/**
	* Returns the primary key of this sample l a r booking.
	*
	* @return the primary key of this sample l a r booking
	*/
	@Override
	public long getPrimaryKey() {
		return _sampleLARBooking.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _sampleLARBooking.getPrimaryKeyObj();
	}

	/**
	* Returns the sample l a r booking ID of this sample l a r booking.
	*
	* @return the sample l a r booking ID of this sample l a r booking
	*/
	@Override
	public long getSampleLARBookingId() {
		return _sampleLARBooking.getSampleLARBookingId();
	}

	/**
	* Returns the user ID of this sample l a r booking.
	*
	* @return the user ID of this sample l a r booking
	*/
	@Override
	public long getUserId() {
		return _sampleLARBooking.getUserId();
	}

	/**
	* Returns the user name of this sample l a r booking.
	*
	* @return the user name of this sample l a r booking
	*/
	@Override
	public java.lang.String getUserName() {
		return _sampleLARBooking.getUserName();
	}

	/**
	* Returns the user uuid of this sample l a r booking.
	*
	* @return the user uuid of this sample l a r booking
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _sampleLARBooking.getUserUuid();
	}

	/**
	* Returns the uuid of this sample l a r booking.
	*
	* @return the uuid of this sample l a r booking
	*/
	@Override
	public java.lang.String getUuid() {
		return _sampleLARBooking.getUuid();
	}

	@Override
	public int hashCode() {
		return _sampleLARBooking.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _sampleLARBooking.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _sampleLARBooking.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _sampleLARBooking.isNew();
	}

	@Override
	public void persist() {
		_sampleLARBooking.persist();
	}

	/**
	* Sets the booking number of this sample l a r booking.
	*
	* @param bookingNumber the booking number of this sample l a r booking
	*/
	@Override
	public void setBookingNumber(java.lang.String bookingNumber) {
		_sampleLARBooking.setBookingNumber(bookingNumber);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_sampleLARBooking.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this sample l a r booking.
	*
	* @param companyId the company ID of this sample l a r booking
	*/
	@Override
	public void setCompanyId(long companyId) {
		_sampleLARBooking.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this sample l a r booking.
	*
	* @param createDate the create date of this sample l a r booking
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_sampleLARBooking.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_sampleLARBooking.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_sampleLARBooking.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_sampleLARBooking.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this sample l a r booking.
	*
	* @param groupId the group ID of this sample l a r booking
	*/
	@Override
	public void setGroupId(long groupId) {
		_sampleLARBooking.setGroupId(groupId);
	}

	/**
	* Sets the modified date of this sample l a r booking.
	*
	* @param modifiedDate the modified date of this sample l a r booking
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_sampleLARBooking.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_sampleLARBooking.setNew(n);
	}

	/**
	* Sets the primary key of this sample l a r booking.
	*
	* @param primaryKey the primary key of this sample l a r booking
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_sampleLARBooking.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_sampleLARBooking.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sample l a r booking ID of this sample l a r booking.
	*
	* @param sampleLARBookingId the sample l a r booking ID of this sample l a r booking
	*/
	@Override
	public void setSampleLARBookingId(long sampleLARBookingId) {
		_sampleLARBooking.setSampleLARBookingId(sampleLARBookingId);
	}

	/**
	* Sets the user ID of this sample l a r booking.
	*
	* @param userId the user ID of this sample l a r booking
	*/
	@Override
	public void setUserId(long userId) {
		_sampleLARBooking.setUserId(userId);
	}

	/**
	* Sets the user name of this sample l a r booking.
	*
	* @param userName the user name of this sample l a r booking
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_sampleLARBooking.setUserName(userName);
	}

	/**
	* Sets the user uuid of this sample l a r booking.
	*
	* @param userUuid the user uuid of this sample l a r booking
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_sampleLARBooking.setUserUuid(userUuid);
	}

	/**
	* Sets the uuid of this sample l a r booking.
	*
	* @param uuid the uuid of this sample l a r booking
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_sampleLARBooking.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.samplelar.model.SampleLARBooking> toCacheModel() {
		return _sampleLARBooking.toCacheModel();
	}

	@Override
	public com.liferay.samplelar.model.SampleLARBooking toEscapedModel() {
		return new SampleLARBookingWrapper(_sampleLARBooking.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _sampleLARBooking.toString();
	}

	@Override
	public com.liferay.samplelar.model.SampleLARBooking toUnescapedModel() {
		return new SampleLARBookingWrapper(_sampleLARBooking.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _sampleLARBooking.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SampleLARBookingWrapper)) {
			return false;
		}

		SampleLARBookingWrapper sampleLARBookingWrapper = (SampleLARBookingWrapper)obj;

		if (Validator.equals(_sampleLARBooking,
					sampleLARBookingWrapper._sampleLARBooking)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _sampleLARBooking.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public SampleLARBooking getWrappedSampleLARBooking() {
		return _sampleLARBooking;
	}

	@Override
	public SampleLARBooking getWrappedModel() {
		return _sampleLARBooking;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _sampleLARBooking.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _sampleLARBooking.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_sampleLARBooking.resetOriginalValues();
	}

	private final SampleLARBooking _sampleLARBooking;
}