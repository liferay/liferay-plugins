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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Mate Thurzo
 * @generated
 */
@ProviderType
public class SampleLARBookingSoap implements Serializable {
	public static SampleLARBookingSoap toSoapModel(SampleLARBooking model) {
		SampleLARBookingSoap soapModel = new SampleLARBookingSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setSampleLARBookingId(model.getSampleLARBookingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setBookingNumber(model.getBookingNumber());

		return soapModel;
	}

	public static SampleLARBookingSoap[] toSoapModels(SampleLARBooking[] models) {
		SampleLARBookingSoap[] soapModels = new SampleLARBookingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SampleLARBookingSoap[][] toSoapModels(
		SampleLARBooking[][] models) {
		SampleLARBookingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SampleLARBookingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SampleLARBookingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SampleLARBookingSoap[] toSoapModels(
		List<SampleLARBooking> models) {
		List<SampleLARBookingSoap> soapModels = new ArrayList<SampleLARBookingSoap>(models.size());

		for (SampleLARBooking model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SampleLARBookingSoap[soapModels.size()]);
	}

	public SampleLARBookingSoap() {
	}

	public long getPrimaryKey() {
		return _sampleLARBookingId;
	}

	public void setPrimaryKey(long pk) {
		setSampleLARBookingId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getSampleLARBookingId() {
		return _sampleLARBookingId;
	}

	public void setSampleLARBookingId(long sampleLARBookingId) {
		_sampleLARBookingId = sampleLARBookingId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getBookingNumber() {
		return _bookingNumber;
	}

	public void setBookingNumber(String bookingNumber) {
		_bookingNumber = bookingNumber;
	}

	private String _uuid;
	private long _sampleLARBookingId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _bookingNumber;
}