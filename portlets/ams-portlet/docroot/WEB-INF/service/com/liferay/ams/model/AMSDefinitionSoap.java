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
 * <a href="AMSDefinitionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is used by
 * {@link com.liferay.ams.service.http.AMSDefinitionServiceSoap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.ams.service.http.AMSDefinitionServiceSoap
 * @generated
 */
public class AMSDefinitionSoap implements Serializable {
	public static AMSDefinitionSoap toSoapModel(AMSDefinition model) {
		AMSDefinitionSoap soapModel = new AMSDefinitionSoap();

		soapModel.setAssetDefinitionId(model.getAssetDefinitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAmsTypeId(model.getAmsTypeId());
		soapModel.setManufacturer(model.getManufacturer());
		soapModel.setModel(model.getModel());
		soapModel.setOrderDate(model.getOrderDate());
		soapModel.setQuantity(model.getQuantity());
		soapModel.setPrice(model.getPrice());

		return soapModel;
	}

	public static AMSDefinitionSoap[] toSoapModels(AMSDefinition[] models) {
		AMSDefinitionSoap[] soapModels = new AMSDefinitionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AMSDefinitionSoap[][] toSoapModels(AMSDefinition[][] models) {
		AMSDefinitionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AMSDefinitionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AMSDefinitionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AMSDefinitionSoap[] toSoapModels(List<AMSDefinition> models) {
		List<AMSDefinitionSoap> soapModels = new ArrayList<AMSDefinitionSoap>(models.size());

		for (AMSDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AMSDefinitionSoap[soapModels.size()]);
	}

	public AMSDefinitionSoap() {
	}

	public long getPrimaryKey() {
		return _assetDefinitionId;
	}

	public void setPrimaryKey(long pk) {
		setAssetDefinitionId(pk);
	}

	public long getAssetDefinitionId() {
		return _assetDefinitionId;
	}

	public void setAssetDefinitionId(long assetDefinitionId) {
		_assetDefinitionId = assetDefinitionId;
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

	public long getAmsTypeId() {
		return _amsTypeId;
	}

	public void setAmsTypeId(long amsTypeId) {
		_amsTypeId = amsTypeId;
	}

	public String getManufacturer() {
		return _manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		_manufacturer = manufacturer;
	}

	public String getModel() {
		return _model;
	}

	public void setModel(String model) {
		_model = model;
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

	public double getPrice() {
		return _price;
	}

	public void setPrice(double price) {
		_price = price;
	}

	private long _assetDefinitionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _amsTypeId;
	private String _manufacturer;
	private String _model;
	private Date _orderDate;
	private int _quantity;
	private double _price;
}