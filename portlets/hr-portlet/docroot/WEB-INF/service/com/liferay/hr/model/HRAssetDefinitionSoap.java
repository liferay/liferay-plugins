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
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class HRAssetDefinitionSoap implements Serializable {
	public static HRAssetDefinitionSoap toSoapModel(HRAssetDefinition model) {
		HRAssetDefinitionSoap soapModel = new HRAssetDefinitionSoap();

		soapModel.setHrAssetDefinitionId(model.getHrAssetDefinitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setHrAssetTypeId(model.getHrAssetTypeId());
		soapModel.setHrAssetModelId(model.getHrAssetModelId());
		soapModel.setHrAssetVendorId(model.getHrAssetVendorId());
		soapModel.setDefinitionNumber(model.getDefinitionNumber());
		soapModel.setOrderId(model.getOrderId());
		soapModel.setOrderDate(model.getOrderDate());
		soapModel.setQuantity(model.getQuantity());
		soapModel.setIndividualPrice(model.getIndividualPrice());

		return soapModel;
	}

	public static HRAssetDefinitionSoap[] toSoapModels(
		HRAssetDefinition[] models) {
		HRAssetDefinitionSoap[] soapModels = new HRAssetDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static HRAssetDefinitionSoap[][] toSoapModels(
		HRAssetDefinition[][] models) {
		HRAssetDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new HRAssetDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new HRAssetDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static HRAssetDefinitionSoap[] toSoapModels(
		List<HRAssetDefinition> models) {
		List<HRAssetDefinitionSoap> soapModels = new ArrayList<HRAssetDefinitionSoap>(models.size());

		for (HRAssetDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new HRAssetDefinitionSoap[soapModels.size()]);
	}

	public HRAssetDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _hrAssetDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setHrAssetDefinitionId(pk);
	}

	public long getHrAssetDefinitionId() {
		return _hrAssetDefinitionId;
	}

	public void setHrAssetDefinitionId(long hrAssetDefinitionId) {
		_hrAssetDefinitionId = hrAssetDefinitionId;
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

	public long getHrAssetTypeId() {
		return _hrAssetTypeId;
	}

	public void setHrAssetTypeId(long hrAssetTypeId) {
		_hrAssetTypeId = hrAssetTypeId;
	}

	public String getHrAssetModelId() {
		return _hrAssetModelId;
	}

	public void setHrAssetModelId(String hrAssetModelId) {
		_hrAssetModelId = hrAssetModelId;
	}

	public long getHrAssetVendorId() {
		return _hrAssetVendorId;
	}

	public void setHrAssetVendorId(long hrAssetVendorId) {
		_hrAssetVendorId = hrAssetVendorId;
	}

	public String getDefinitionNumber() {
		return _definitionNumber;
	}

	public void setDefinitionNumber(String definitionNumber) {
		_definitionNumber = definitionNumber;
	}

	public Date getOrderId() {
		return _orderId;
	}

	public void setOrderId(Date orderId) {
		_orderId = orderId;
	}

	public Date getOrderDate() {
		return _orderDate;
	}

	public void setOrderDate(Date orderDate) {
		_orderDate = orderDate;
	}

	public int getQuantity() {
		return _quantity;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public double getIndividualPrice() {
		return _individualPrice;
	}

	public void setIndividualPrice(double individualPrice) {
		_individualPrice = individualPrice;
	}

	private long _hrAssetDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _hrAssetTypeId;
	private String _hrAssetModelId;
	private long _hrAssetVendorId;
	private String _definitionNumber;
	private Date _orderId;
	private Date _orderDate;
	private int _quantity;
	private double _individualPrice;
}