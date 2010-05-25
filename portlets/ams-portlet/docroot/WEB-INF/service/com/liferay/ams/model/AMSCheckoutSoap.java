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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="AMSCheckoutSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * {@link com.liferay.ams.service.http.AMSCheckoutServiceSoap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.ams.service.http.AMSCheckoutServiceSoap
 * @generated
 */
public class AMSCheckoutSoap implements Serializable {
	public static AMSCheckoutSoap toSoapModel(AMSCheckout model) {
		AMSCheckoutSoap soapModel = new AMSCheckoutSoap();

		soapModel.setAmsCheckoutId(model.getAmsCheckoutId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAmsAssetId(model.getAmsAssetId());
		soapModel.setCheckOutDate(model.getCheckOutDate());
		soapModel.setExpectedCheckInDate(model.getExpectedCheckInDate());
		soapModel.setActualCheckInDate(model.getActualCheckInDate());

		return soapModel;
	}

	public static AMSCheckoutSoap[] toSoapModels(AMSCheckout[] models) {
		AMSCheckoutSoap[] soapModels = new AMSCheckoutSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AMSCheckoutSoap[][] toSoapModels(AMSCheckout[][] models) {
		AMSCheckoutSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AMSCheckoutSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AMSCheckoutSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AMSCheckoutSoap[] toSoapModels(List<AMSCheckout> models) {
		List<AMSCheckoutSoap> soapModels = new ArrayList<AMSCheckoutSoap>(models.size());

		for (AMSCheckout model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AMSCheckoutSoap[soapModels.size()]);
	}

	public AMSCheckoutSoap() {
	}

	public long getPrimaryKey() {
		return _amsCheckoutId;
	}

	public void setPrimaryKey(long pk) {
		setAmsCheckoutId(pk);
	}

	public long getAmsCheckoutId() {
		return _amsCheckoutId;
	}

	public void setAmsCheckoutId(long amsCheckoutId) {
		_amsCheckoutId = amsCheckoutId;
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

	public long getAmsAssetId() {
		return _amsAssetId;
	}

	public void setAmsAssetId(long amsAssetId) {
		_amsAssetId = amsAssetId;
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

	private long _amsCheckoutId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _amsAssetId;
	private Date _checkOutDate;
	private Date _expectedCheckInDate;
	private Date _actualCheckInDate;
}