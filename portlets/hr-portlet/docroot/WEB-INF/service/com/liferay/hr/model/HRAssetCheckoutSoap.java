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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Wesley Gong
 * @generated
 */
public class HRAssetCheckoutSoap implements Serializable {
	public static HRAssetCheckoutSoap toSoapModel(HRAssetCheckout model) {
		HRAssetCheckoutSoap soapModel = new HRAssetCheckoutSoap();

		soapModel.setHrAssetCheckoutId(model.getHrAssetCheckoutId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrAssetId(model.getHrAssetId());
		soapModel.setHrUserId(model.getHrUserId());
		soapModel.setCheckoutDate(model.getCheckoutDate());
		soapModel.setExpectedCheckInDate(model.getExpectedCheckInDate());
		soapModel.setActualCheckInDate(model.getActualCheckInDate());

		return soapModel;
	}

	public static HRAssetCheckoutSoap[] toSoapModels(HRAssetCheckout[] models) {
		HRAssetCheckoutSoap[] soapModels = new HRAssetCheckoutSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRAssetCheckoutSoap[][] toSoapModels(
		HRAssetCheckout[][] models) {
		HRAssetCheckoutSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRAssetCheckoutSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRAssetCheckoutSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRAssetCheckoutSoap[] toSoapModels(
		List<HRAssetCheckout> models) {
		List<HRAssetCheckoutSoap> soapModels = new ArrayList<HRAssetCheckoutSoap>(models.size());

		for (HRAssetCheckout model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRAssetCheckoutSoap[soapModels.size()]);
	}

	public HRAssetCheckoutSoap() {
	}

	public long getPrimaryKey() {
		return _hrAssetCheckoutId;
	}

	public void setPrimaryKey(long pk) {
		setHrAssetCheckoutId(pk);
	}

	public long getHrAssetCheckoutId() {
		return _hrAssetCheckoutId;
	}

	public void setHrAssetCheckoutId(long hrAssetCheckoutId) {
		_hrAssetCheckoutId = hrAssetCheckoutId;
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

	public long getHrAssetId() {
		return _hrAssetId;
	}

	public void setHrAssetId(long hrAssetId) {
		_hrAssetId = hrAssetId;
	}

	public long getHrUserId() {
		return _hrUserId;
	}

	public void setHrUserId(long hrUserId) {
		_hrUserId = hrUserId;
	}

	public Date getCheckoutDate() {
		return _checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		_checkoutDate = checkoutDate;
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

	private long _hrAssetCheckoutId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrAssetId;
	private long _hrUserId;
	private Date _checkoutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
}