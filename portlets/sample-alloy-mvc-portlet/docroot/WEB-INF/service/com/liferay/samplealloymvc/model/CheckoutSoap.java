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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class CheckoutSoap implements Serializable {
	public static CheckoutSoap toSoapModel(Checkout model) {
		CheckoutSoap soapModel = new CheckoutSoap();

		soapModel.setCheckoutId(model.getCheckoutId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetId(model.getAssetId());
		soapModel.setCheckOutDate(model.getCheckOutDate());
		soapModel.setExpectedCheckInDate(model.getExpectedCheckInDate());
		soapModel.setActualCheckInDate(model.getActualCheckInDate());

		return soapModel;
	}

	public static CheckoutSoap[] toSoapModels(Checkout[] models) {
		CheckoutSoap[] soapModels = new CheckoutSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CheckoutSoap[][] toSoapModels(Checkout[][] models) {
		CheckoutSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CheckoutSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CheckoutSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CheckoutSoap[] toSoapModels(List<Checkout> models) {
		List<CheckoutSoap> soapModels = new ArrayList<CheckoutSoap>(models.size());

		for (Checkout model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CheckoutSoap[soapModels.size()]);
	}

	public CheckoutSoap() {
	}

	public long getPrimaryKey() {
		return _checkoutId;
	}

	public void setPrimaryKey(long pk) {
		setCheckoutId(pk);
	}

	public long getCheckoutId() {
		return _checkoutId;
	}

	public void setCheckoutId(long checkoutId) {
		_checkoutId = checkoutId;
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

	public long getAssetId() {
		return _assetId;
	}

	public void setAssetId(long assetId) {
		_assetId = assetId;
	}

	public Date getCheckOutDate() {
		return _checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		_checkOutDate = checkOutDate;
	}

	public Date getExpectedCheckInDate() {
		return _expectedCheckInDate;
	}

	public void setExpectedCheckInDate(Date expectedCheckInDate) {
		_expectedCheckInDate = expectedCheckInDate;
	}

	public Date getActualCheckInDate() {
		return _actualCheckInDate;
	}

	public void setActualCheckInDate(Date actualCheckInDate) {
		_actualCheckInDate = actualCheckInDate;
	}

	private long _checkoutId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _assetId;
	private Date _checkOutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
}