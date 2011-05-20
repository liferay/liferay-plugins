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
public class HRExpenseCurrencyConversionSoap implements Serializable {
	public static HRExpenseCurrencyConversionSoap toSoapModel(
		HRExpenseCurrencyConversion model) {
		HRExpenseCurrencyConversionSoap soapModel = new HRExpenseCurrencyConversionSoap();

		soapModel.setHrExpenseCurrencyConversionId(model.getHrExpenseCurrencyConversionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFromHRExpenseCurrencyId(model.getFromHRExpenseCurrencyId());
		soapModel.setToHRExpenseCurrencyId(model.getToHRExpenseCurrencyId());
		soapModel.setConversionDate(model.getConversionDate());
		soapModel.setConversionValue(model.getConversionValue());

		return soapModel;
	}

	public static HRExpenseCurrencyConversionSoap[] toSoapModels(
		HRExpenseCurrencyConversion[] models) {
		HRExpenseCurrencyConversionSoap[] soapModels = new HRExpenseCurrencyConversionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRExpenseCurrencyConversionSoap[][] toSoapModels(
		HRExpenseCurrencyConversion[][] models) {
		HRExpenseCurrencyConversionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRExpenseCurrencyConversionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRExpenseCurrencyConversionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRExpenseCurrencyConversionSoap[] toSoapModels(
		List<HRExpenseCurrencyConversion> models) {
		List<HRExpenseCurrencyConversionSoap> soapModels = new ArrayList<HRExpenseCurrencyConversionSoap>(models.size());

		for (HRExpenseCurrencyConversion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRExpenseCurrencyConversionSoap[soapModels.size()]);
	}

	public HRExpenseCurrencyConversionSoap() {
	}

	public long getPrimaryKey() {
		return _hrExpenseCurrencyConversionId;
	}

	public void setPrimaryKey(long pk) {
		setHrExpenseCurrencyConversionId(pk);
	}

	public long getHrExpenseCurrencyConversionId() {
		return _hrExpenseCurrencyConversionId;
	}

	public void setHrExpenseCurrencyConversionId(
		long hrExpenseCurrencyConversionId) {
		_hrExpenseCurrencyConversionId = hrExpenseCurrencyConversionId;
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

	public long getFromHRExpenseCurrencyId() {
		return _fromHRExpenseCurrencyId;
	}

	public void setFromHRExpenseCurrencyId(long fromHRExpenseCurrencyId) {
		_fromHRExpenseCurrencyId = fromHRExpenseCurrencyId;
	}

	public long getToHRExpenseCurrencyId() {
		return _toHRExpenseCurrencyId;
	}

	public void setToHRExpenseCurrencyId(long toHRExpenseCurrencyId) {
		_toHRExpenseCurrencyId = toHRExpenseCurrencyId;
	}

	public Date getConversionDate() {
		return _conversionDate;
	}

	public void setConversionDate(Date conversionDate) {
		_conversionDate = conversionDate;
	}

	public double getConversionValue() {
		return _conversionValue;
	}

	public void setConversionValue(double conversionValue) {
		_conversionValue = conversionValue;
	}

	private long _hrExpenseCurrencyConversionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _fromHRExpenseCurrencyId;
	private long _toHRExpenseCurrencyId;
	private Date _conversionDate;
	private double _conversionValue;
}