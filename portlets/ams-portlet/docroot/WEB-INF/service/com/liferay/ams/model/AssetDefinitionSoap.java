/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.ams.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="AssetDefinitionSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class AssetDefinitionSoap implements Serializable {
	public static AssetDefinitionSoap toSoapModel(AssetDefinition model) {
		AssetDefinitionSoap soapModel = new AssetDefinitionSoap();

		soapModel.setAssetDefinitionId(model.getAssetDefinitionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAssetTypeId(model.getAssetTypeId());
		soapModel.setManufacturer(model.getManufacturer());
		soapModel.setModel(model.getModel());
		soapModel.setOrderDate(model.getOrderDate());
		soapModel.setQuantity(model.getQuantity());
		soapModel.setPrice(model.getPrice());

		return soapModel;
	}

	public static AssetDefinitionSoap[] toSoapModels(
		List<AssetDefinition> models) {
		List<AssetDefinitionSoap> soapModels = new ArrayList<AssetDefinitionSoap>(models.size());

		for (AssetDefinition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetDefinitionSoap[soapModels.size()]);
	}

	public AssetDefinitionSoap() {
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

	public long getAssetTypeId() {
		return _assetTypeId;
	}

	public void setAssetTypeId(long assetTypeId) {
		_assetTypeId = assetTypeId;
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
	private long _assetTypeId;
	private String _manufacturer;
	private String _model;
	private Date _orderDate;
	private int _quantity;
	private double _price;
}